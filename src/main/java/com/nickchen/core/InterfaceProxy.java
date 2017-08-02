package com.nickchen.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author nickChen
 * @create 2017-08-02 17:17.
 */
public class InterfaceProxy implements InvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("ObjectProxy execute:" + method.getName());
        return method.invoke(proxy, args);
    }

    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<T> innerInterface) {
        ClassLoader classLoader = innerInterface.getClassLoader();
        Class[] interfaces = new Class[] { innerInterface };
        InterfaceProxy proxy = new InterfaceProxy();
        return (T) Proxy.newProxyInstance(classLoader, interfaces, proxy);
    }
}
