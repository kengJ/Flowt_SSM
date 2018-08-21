package com.aop;

import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//@Order(10)
//@Aspect
//@Component
public class LogAop {

	Logger log = Logger.getLogger(LogAop.class);
	
	//切入点表达式
	@Pointcut("execution(* com.controller.*Controller.*(..))")
	public void ControllerLogExpression() {}
	
	//前置通知
	@Before("ControllerLogExpression()")
	public void BeforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		log.info("controller method"+methodName+" args is "+args);
	}
	
	//后置通知无法获取返回值
	//@After("ControllerLogExpression()")
	public void AfterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		//List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("the method "+methodName+" ends");
	}
	
	//ֵ返回通知，可以获取返回值
	@AfterReturning(value="ControllerLogExpression()",returning="result")
	public void AfterReturn(JoinPoint joinPoint,Object result) {
		String methodName = joinPoint.getSignature().getName();
		//System.out.println("controller method "+methodName+" result is"+result);
		log.info("controller method "+methodName+" result is"+result);
	}
	
	//异常通知
	//@AfterThrowing(value="ControllerLogExpression()",throwing="ex")
	public void AfterThrowing(JoinPoint joinPoint,Exception ex) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("the method "+methodName+" with throwing is"+ex);
	}
	
	/**
	 * 环绕通知需要携带ProceedingJoinPoint 作为参数 
	 * 	环绕通知类似于动态代理全过程，ProceedingJoinPoint可以决定是否执行目标方法
	 * @param proceedingJoinPoint
	 */
	//@Around("ControllerLogExpression()")
	public Object Around(ProceedingJoinPoint proceedingJoinPoint) {
		
		Object result = null;
		
		try {
			System.out.println("the method "+proceedingJoinPoint.getSignature().getName()+"begin with "+Arrays.asList(proceedingJoinPoint.getArgs()));
			result = proceedingJoinPoint.proceed();
			System.out.println("the method end with result is "+result	);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("the method throwing is"+e);
		}//执行目标方法
		System.out.println("the method is end");
		return result;
	}
}
