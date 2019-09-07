package org.balu.learn.common.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * Aspect for logging using lombok log4j2
 */
@Component
@ConditionalOnProperty("common.log.enable")
@Aspect
@Log4j2
public class LoggingAspect {
	/**
	 * Logs every method in org.balu.learn package or sub-packages
	 * 
	 * @param call
	 *            - {@link ProceedingJoinPoint}
	 * @return Object, which is the the return value for the method executing as per
	 *         pointcut expression
	 * @throws Throwable
	 */
	@Around("execution(* org.balu.learn..*.*(..))")
	public Object log(ProceedingJoinPoint call) throws Throwable {
		StringBuilder argumentsInfo = new StringBuilder();

		// Getting all the args passed and appending to the string builder.
		Arrays.stream(call.getArgs()).forEach(arg -> argumentsInfo.append(arg));

		// Logging before the method call
		log.info(call.getSignature() + "{");
		if (argumentsInfo.length() > 0)
			log.info("Arguments passed: " + argumentsInfo.toString());

		// Method execution
		Object point = call.proceed();

		// Logging after the method call
		log.info("Return value:" + point);
		log.info("} //EOM - " + call.getSignature());
		
		//Returning back the return value
		return point;
	}

}
