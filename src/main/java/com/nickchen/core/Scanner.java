package com.nickchen.core;

import com.nickchen.annotation.ServiceInterface;
import com.nickchen.factory.AutoProxyFactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

/**
 * @author nickChen
 * @create 2017-08-02 17:23.
 */
public final class Scanner extends ClassPathBeanDefinitionScanner {

    public Scanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public void registerDefaultFilters() {
        this.addIncludeFilter(new AnnotationTypeFilter(ServiceInterface.class));
    }

    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        for (BeanDefinitionHolder holder : beanDefinitions) {
            GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
            definition.getPropertyValues().add("innerClassName", definition.getBeanClassName());
            definition.setBeanClass(AutoProxyFactoryBean.class);
        }
        return beanDefinitions;
    }

    public boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata()
                .hasAnnotation(ServiceInterface.class.getName());
    }
}
