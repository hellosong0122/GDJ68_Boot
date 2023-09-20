package com.winter.app.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class Card {
	
	//advice
	//@Before("execution(* com.winter.app.aop.Transfer.*())")
	//@After("execution(* com.winter.app.aop.Transfer.*())")
	@Around("execution(* com.winter.app.aop.Transfer.*())")
	public Object CardCheck(ProceedingJoinPoint joinPoint)throws Throwable{
		
		log.info("--------------");
		log.info("card check 타기");
		
		Object [] args = joinPoint.getArgs();
		log.info("Args : {}", args);
		
		Object obj = joinPoint.proceed();
		
		log.info("card check 내리기");
		log.info("--------------");
		
		return obj;
	}
}
