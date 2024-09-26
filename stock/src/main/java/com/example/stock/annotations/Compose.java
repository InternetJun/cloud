package com.example.stock.annotations;

import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * @author lejun
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Order(1)
public @interface Compose {
    String value() default "";
}
