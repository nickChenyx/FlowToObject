package com.nickchen.core;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author nickChen
 * @create 2017-08-02 17:19.
 */

public class MethodInterceptorImpl implements MethodInterceptor {

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("MethodInterceptorImpl:" + method.getName());
        return methodProxy.invokeSuper(o, objects);
    }
}
