package com.nickchen.annotation;
import java.lang.annotation.*;
/**
 * @author nickChen
 * @create 2017-08-02 13:29.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceMethod {
    String value();  // url
    String method() default "get";
}
