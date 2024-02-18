package com.example.stock.annotations;

import java.lang.annotation.*;

/**
 * @author lejun
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Test {
    String name() default "";
}
