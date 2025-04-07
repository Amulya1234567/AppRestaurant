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
    private final static Logger logger= (Logger) LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.ivoyant.restaurantapp.service.*.*(..))")
    public void logBefore(){
        logger.info("Method starts its execution");
    }

    @Before("execution(* com.ivoyant.restaurantapp.service.RestService.getFood(..))")
    public void logBeforefood(){
        logger.info("Method starts its execution ");
    }

    @After("execution(* com.ivoyant.restaurantapp.service.RestService.getFood(..))")
    public void logAfter(){
        logger.info("Method executed");
    }

    @After("execution(* com.ivoyant.restaurantapp.service.RestService.food(..))")
    public void logAfterfood(){
        logger.info("Method executed after post");
    }

    @Around("execution(* com.ivoyant.restaurantapp.service.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
        logger.info("Aspect log called");

        Object result=joinPoint.proceed();
        logger.info("Aspect after log called");
        return result;
    }

}
