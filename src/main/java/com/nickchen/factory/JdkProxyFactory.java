package com.nickchen.factory;

import com.nickchen.core.ServiceInterfaceInvocationHandler;
import org.springframework.util.Assert;

import java.text.MessageFormat;

/**
 * @author nickChen
 * @create 2017-08-02 17:06.
 */
public class JdkProxyFactory implements ProxyFactory {
    @Override
    public Object create(Class clazz) {
        Assert.isTrue(clazz.isInterface(), MessageFormat.format("[ {0} ] is not an Interface, please check out or use CGlibProxy", clazz.getName()));
        return ServiceInterfaceInvocationHandler.newInstance(clazz);
    }

}
