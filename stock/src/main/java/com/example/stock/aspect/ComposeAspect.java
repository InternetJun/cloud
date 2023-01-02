package com.example.stock.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author lejun
 */
@Aspect
@Component
public class ComposeAspect {
    @Pointcut("@annotation(com.example.stock.annotations.Compose)")
    public void checkout() {

    }

    @Around("checkout()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        return null;
    }

}
