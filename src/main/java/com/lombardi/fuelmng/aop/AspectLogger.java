package com.lombardi.fuelmng.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect
@Component
@EnableAspectJAutoProxy

/**
 * In order to handle AOP logs
 */
public class AspectLogger {

    private static final Logger logger = LoggerFactory.getLogger(AspectLogger.class);


    @Pointcut("execution(* com.lombardi.fuelmng.rest..*(..))")
    protected void restLogger() {
    }


    @Before("restLogger()")
    public void restLogBefore(JoinPoint joinPoint) {
        logger.info("**************");
        logger.info(joinPoint.getSignature().getName() + " entered");
        logger.info("REQUEST ==>>>{}",Arrays.asList(joinPoint.getArgs()).toString());
    }


    @After("restLogger()")
    public void restLogAfter(JoinPoint joinPoint) {
        logger.info(joinPoint.getSignature().getName() + " finished");
    }


    @AfterReturning(
            pointcut = "execution(* com.lombardi.fuelmng.rest..*(..))",
            returning = "result")
    public void restAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("RETURNED ==>>>{}",result.toString());
        logger.info("**************");
    }


    @Around("execution(* com.lombardi.fuelmng.rest..*.*(..))")
    public Object restLogTimeMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object retVal = joinPoint.proceed();
        stopWatch.stop();
        logger.info(joinPoint.getSignature().getName() + " execution time: " + stopWatch.getTotalTimeMillis() + " ms");
        return retVal;
    }

}