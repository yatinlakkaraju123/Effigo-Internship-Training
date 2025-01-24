package com.in28minutes.learn_spring_aop.aop.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcutConfig {
    @Pointcut("execution(* com.in28minutes.learn_spring_aop.aop.*.*.*(..))")
    public void businessPackageConfig(){

    }
}
