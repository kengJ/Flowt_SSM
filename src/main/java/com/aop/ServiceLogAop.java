package com.aop;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * service层基础处理
 * @author heyanzhu
 *
 */
@Order(0)
@Component
@Aspect
public class ServiceLogAop {

	private static final  Logger log = Logger.getLogger(ServiceLogAop.class);
	
	/**
	 * 拦截service下所有的函数
	 */
	
	@Pointcut("execution(* com.service.*Service.Find*(..))")
	public void FindFuncExpression() {}
	
	/**
	 * 环绕通知
	 * 针对所有service层的Find函数进行处理
	 * 1.输出日志
	 * 2.返回空值
	 * @param joinPoint
	 * @param ex
	 * @throws Throwable 
	 */
	@Around("FindFuncExpression()")
	public Object ServiceExctionForFind(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String MethodName = proceedingJoinPoint.getSignature().getName();
		List<Object> Args = Arrays.asList(proceedingJoinPoint.getArgs());
		Object Result = null;
		Result = proceedingJoinPoint.proceed();
//		try {
//			Result = proceedingJoinPoint.proceed();
//		} catch (Throwable e) {
//			log.error("Method "+MethodName+" and args is "+ Args +" have a expection is "+e.toString());
//		}
		return Result;
	}
}
