package com.nickchen.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nickchen.annotation.ServiceInterface;
import com.nickchen.annotation.ServiceMethod;
import com.xiaoleilu.hutool.http.*;
import com.xiaoleilu.hutool.util.TypeUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.lang.reflect.*;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.*;

/**
 * @author nickChen
 * @create 2017-08-02 13:19.
 */
public class ServiceInterfaceInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ServiceInterface serviceInterface = (ServiceInterface) method.getDeclaringClass().getAnnotation(ServiceInterface.class);
        Assert.notNull(serviceInterface, MessageFormat.format("Class [ {0} ] should be annotated @ServiceInterface", method.getDeclaringClass().getName()));
        ServiceMethod serviceMethod = method.getAnnotation(ServiceMethod.class);
        Assert.notNull(serviceMethod, MessageFormat.format("Class [ {0}.{1} ] should be annotated @ServiceMethod", method.getDeclaringClass().getName(), method.getName()));

        ServiceArgs serviceArgs = new ServiceArgs(serviceInterface.protocol(),
                                                    serviceInterface.baseUrl(),
                                                    serviceMethod.value(),
                                                    serviceMethod.method());

        String body = null;
        if (null == args) {
            body = doHttpRequest(serviceArgs);
        }else if (args[0] instanceof Map){
            body = doHttpRequest(serviceArgs, (Map)args[0]);
        }else {
            throw new IllegalArgumentException(MessageFormat.format("Method [ {0}.{1} ] should has a Map<String, Object> argument on the first index.", method.getDeclaringClass().getName(), method.getName()));
        }
        return JSON.parseObject(body, method.getReturnType());
    }

    private String doHttpRequest(ServiceArgs args) {
        return doHttpRequest(args, null);
    }

    private String doHttpRequest(ServiceArgs args, Map<String, Object> map) {
        String url = args.getProtocol() + "://" +args.getBaseUrl() + args.getUrl();
        if ("GET".equalsIgnoreCase(args.getMethod())) {
            if (null == map){
                return HttpUtil.get(url);
            }
            return HttpUtil.get(url, map);
        }else if ("POST".equalsIgnoreCase(args.getMethod())) {
            if (null == map){
                return HttpUtil.post(url, "");
            }
            return HttpUtil.post(url, map);
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<T> innerInterface) {
        ClassLoader classLoader = innerInterface.getClassLoader();
        Class[] interfaces = new Class[] { innerInterface };
        ServiceInterfaceInvocationHandler handler = new ServiceInterfaceInvocationHandler();
        return (T) Proxy.newProxyInstance(classLoader, interfaces, handler);
    }

    public Object getProxy(Class[] interfaces) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                interfaces, this);
    }

    @AllArgsConstructor
    private class ServiceArgs {
        @Getter @Setter private String protocol;
        @Getter @Setter private String baseUrl;
        @Getter @Setter private String url;
        @Getter @Setter private String method;
    }

}
