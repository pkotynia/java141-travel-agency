package com.sda.travelagency.aspect;

import com.sda.travelagency.util.Username;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.sda.travelagency.controller..*(..))")
    public void logAfterAnyControllerMethod(JoinPoint joinPoint){
        logger.info(String.format("User: %s called method: %s from: %s at: %s"
                ,Username.getActive()
                ,joinPoint.getSignature().getName()
                ,joinPoint.getSignature().getDeclaringType().getSimpleName()
                ,LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.uuuu"))));
    }

    @AfterThrowing(value = "execution(* com.sda.travelagency.controller..*(..))", throwing = "error")
    public void logAfterAnyException(Throwable error){
        logger.warn(String.format("Exception: %s, message: %s"
                ,error.getClass().getSimpleName()
                ,error.getMessage()));
    }
}
