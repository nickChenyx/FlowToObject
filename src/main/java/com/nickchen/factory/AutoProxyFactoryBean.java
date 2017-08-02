package com.nickchen.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author nickChen
 * @create 2017-08-02 17:22.
 */
public class AutoProxyFactoryBean<T> implements InitializingBean, FactoryBean<T> {

    private String innerClassName;

    public void setInnerClassName(String innerClassName) {
        this.innerClassName = innerClassName;
    }

    public T getObject() throws Exception {
        Class innerClass = Class.forName(innerClassName);
        if (innerClass.isInterface()) {
            return (T) new JdkProxyFactory().create(innerClass);
        } else {
            return (T) new CglibProxyFactory().create(innerClass);
        }
    }

    public Class<?> getObjectType() {
        try {
            return Class.forName(innerClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws Exception { /*ignore*/ }
}