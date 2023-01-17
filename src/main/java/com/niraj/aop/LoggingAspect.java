package com.niraj.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.niraj.entity.Department;

@Aspect
@Component
public class LoggingAspect {

	@Pointcut("execution(* com.niraj.controller.DepartmentController.getDepartmentByCode(..))")
	public void afterReturningDepartmentPC() {
	}

	@Before("execution(* com.niraj.controller.DepartmentController.getAllDepartments(..))")
	public void loggingDepartment() {
		System.out.println("Before Logging Advice called ==> Full Data Requested");
	}

	@After("execution( * com.niraj.controller.DepartmentController.deleteDepartmentById(..))")
	public void afterLogger() {
		System.out.println("Delete Department Advice called ==> ");
	}

	@Before("execution(* com.niraj.controller.DepartmentController.getDepartmentByCode(..))")
	public void loggingDepartmentByCode(JoinPoint jp) {
		System.out.println("|| Before => Data coming in Request ==> " + jp.getArgs()[0]);
	}

	@AfterReturning(pointcut = "afterReturningDepartmentPC()", returning = "dept")
	public void afterReturnDepartmentDataToClient(Department dept) {
		System.out.println("Data returned after Successful Request ==> " + dept);
	}

}
