package com.nickchen.annotation;

import java.lang.annotation.*;

/**
 * @author nickChen
 * @create 2017-08-02 13:25.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceInterface {
    String protocol() default "http";
    String baseUrl();
}
