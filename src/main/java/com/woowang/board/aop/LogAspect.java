package com.woowang.board.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* com.woowang.board..*(..))")
    private void inPackage(){}

    @Pointcut("execution(* *..*Service.*(..))")
    public void serviceAll(){}
    @Pointcut("execution(* *..*Controller.*(..))")
    public void controllerAll(){}
    @Pointcut("execution(* *..*Repository.*(..))")
    public void repositoryAll(){}
    @Pointcut("inPackage() && (repositoryAll() || controllerAll() || serviceAll())")
    public void all(){}

    @Before("all()")
    public void doBefore(JoinPoint joinPoint){
        log.info("[before] {}",joinPoint.getSignature().toShortString());
    }

    @AfterReturning("all()")
    public void doAfterReturning(JoinPoint joinPoint){
        log.info("[returning] {}",joinPoint.getSignature().toShortString());
    }
    @AfterThrowing(value = "all()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint,Exception ex){
        log.info("[throwing] {}  err = {}",joinPoint.getSignature().toShortString(),ex.getMessage());
    }
}
