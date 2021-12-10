package tn.esprit.spring;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Aspect
@Slf4j
@Component

public class LoggingAspect {
	
	  @Pointcut("execution(* tn.esprit.spring.service.StockServiceImpl.*(..))")
	    public void loggingPointCut(){}

	    @Before("loggingPointCut()")
	    public void before( JoinPoint joinPoint ){
	        log.info("Before method invoked::"+joinPoint.getSignature());
	    }
	
	
	    @After("loggingPointCut()")
	    public void after( JoinPoint joinPoint ){
	        log.info("After method invoked::"+joinPoint.getSignature());
	    }
	    
	    @AfterReturning("loggingPointCut()")
	    public void AfterReturning( JoinPoint joinPoint ){
	        log.info("AfterReturning method invoked::"+joinPoint.getSignature());
	    }
	
	    @AfterThrowing("loggingPointCut()")
	    public void AfterThrowing( JoinPoint joinPoint ){
	        log.info("After method invoked::"+joinPoint.getSignature());
	    }
	
	    @Around("loggingPointCut()")
	    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
	    long start = System.currentTimeMillis();
	    Object obj = pjp.proceed();
	    long elapsedTime = System.currentTimeMillis() - start;
	    log.info("Method execution time: " + elapsedTime + " milliseconds.");
	    return obj;
	    }
	
	
	
	
	
	
	
	

}
