package com.niraj.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AdminCallAspect {

	@Pointcut("within( com.niraj.controller..*)")
	public void sendEmailToAdmin() {
	}

	@Before("sendEmailToAdmin()")
	public void callingAdminOnSpecialRequestData() {
		System.out.println("Sending Email to niraj@gmail.com");
	}

}
