package com.nickchen.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.stereotype.Component;

/**
 * @author nickChen
 * @create 2017-08-02 17:24.
 */

@Component
public class BeanScannerConfigurer implements BeanFactoryPostProcessor{

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Scanner scanner = new Scanner((BeanDefinitionRegistry) beanFactory);
        scanner.scan("com.nickchen.service");
    }
}