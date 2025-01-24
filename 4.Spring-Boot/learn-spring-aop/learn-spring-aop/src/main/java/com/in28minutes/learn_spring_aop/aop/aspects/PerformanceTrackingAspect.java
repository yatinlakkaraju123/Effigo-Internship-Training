package com.in28minutes.learn_spring_aop.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;


@Aspect
@Configuration
public class PerformanceTrackingAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Around("execution(* com.in28minutes.learn_spring_aop.aop.*.*.*(..))")
    public Object findExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTimeInMillis = System.currentTimeMillis();

        Object proceed = proceedingJoinPoint.proceed();
        long stopTimeInMillis = System.currentTimeMillis();
        long executionTime = stopTimeInMillis - startTimeInMillis;
        logger.info("the time for execution of method {} is {}:",executionTime,proceedingJoinPoint);
        return proceed;
    }
}
