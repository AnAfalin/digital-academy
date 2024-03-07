package ru.t1academy.aop.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
//@Slf4j
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private static final Logger log = LogManager.getLogger(LoggingAspect.class.getName());

    @Before("execution(* ru.t1academy.aop.service.impl.*.*(..))")
    public void logExecuteServiceMethod(JoinPoint point) {
        log.debug("Execute method - {}", point.getSignature());
    }

}
