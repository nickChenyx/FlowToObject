package com.nickchen.factory;

import com.nickchen.core.MethodInterceptorImpl;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author nickChen
 * @create 2017-08-02 17:10.
 */
public class CglibProxyFactory implements ProxyFactory {
    @Override
    public Object create(Class clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
        enhancer.setCallback(new MethodInterceptorImpl());
        return enhancer.create();
    }
}
