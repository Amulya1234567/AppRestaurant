package com.ivoyant.restaurantapp.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LoggingAspect {

    @Before("execution(* com.ivoyant.restaurantapp.service.*.*(..))")
    public void logBefore(){
        log.info("Method starts its execution");
    }

    @Before("execution(* com.ivoyant.restaurantapp.service.RestService.getFood(..))")
    public void logBeforefood(){
        log.info("Method starts its execution ");
    }

    @After("execution(* com.ivoyant.restaurantapp.service.RestService.getFood(..))")
    public void logAfter(){
        log.info("Method executed");
    }

    @After("execution(* com.ivoyant.restaurantapp.service.RestService.food(..))")
    public void logAfterfood(){
        log.info("Method executed after post");
    }

    @Around("execution(* com.ivoyant.restaurantapp.service.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("Aspect log called");

        Object result=joinPoint.proceed();
        log.info("Aspect after log called");
        return result;
    }

}
