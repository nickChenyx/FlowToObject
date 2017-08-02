package com.nickchen.core;

import com.alibaba.fastjson.JSON;
import com.nickchen.annotation.ServiceInterface;
import com.nickchen.annotation.ServiceMethod;
import com.xiaoleilu.hutool.http.HttpRequest;
import com.xiaoleilu.hutool.http.HttpResponse;
import com.xiaoleilu.hutool.http.HttpUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.MessageFormat;

/**
 * @author nickChen
 * @create 2017-08-02 13:19.
 */
public class ServiceInterfaceInvocationHanlder implements InvocationHandler {

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
        String url = serviceArgs.getProtocol() + "://www.jianshu.com" + serviceArgs.getBaseUrl() + serviceArgs.getUrl();
        HttpRequest request = HttpUtil.createRequest(com.xiaoleilu.hutool.http.Method.GET, url);
        HttpResponse response = request.execute();
        String json = "{name:'nickchen'}";
        return JSON.parseObject(json, method.getReturnType());
    }

    public static <T> T newInstance(Class<T> innerInterface) {
        ClassLoader classLoader = innerInterface.getClassLoader();
        Class[] interfaces = new Class[] { innerInterface };
        ServiceInterfaceInvocationHanlder hanlder = new ServiceInterfaceInvocationHanlder();
        return (T) Proxy.newProxyInstance(classLoader, interfaces, hanlder);
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
