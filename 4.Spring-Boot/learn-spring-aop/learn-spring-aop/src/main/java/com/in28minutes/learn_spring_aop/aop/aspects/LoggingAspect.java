package com.in28minutes.learn_spring_aop.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());
    //com/in28minutes/learn_spring_aop/aop/business
    @Before("execution(* com.in28minutes.learn_spring_aop.aop.business.*.*(..)")
    public void methodCall(JoinPoint joinPoint){
        logger.info("Before Aspect - method is called - {}",joinPoint);
    }
}
