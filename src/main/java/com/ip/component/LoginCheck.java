package com.ip.component;

import java.security.Principal;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.ip.service.UserService;

@Component
@Aspect
public class LoginCheck {
	
	@Autowired
	UserService userservice;
	
	@Before("execution(* com.ip.controller.*Controller.*(..))")
	public void invokeBefore(JoinPoint joinPoint) {
		Principal principal = null;
		ModelAndView modelAndView = null;
		for (Object object : joinPoint.getArgs()) {
			if (object instanceof Principal) {
				principal = (Principal) object;
			} else if (object instanceof ModelAndView) {
				modelAndView = (ModelAndView) object;
			}
		}
		if (principal != null && modelAndView != null) {
			setLogin(modelAndView, principal);
		}
	}
	
	private void setLogin(ModelAndView modelAndView, Principal principal) {
		Authentication auth = (Authentication) principal;
		if (auth != null && auth.isAuthenticated()) {
			modelAndView.addObject("Login", true);
			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			modelAndView.addObject("LoginInfo", userDetails);
			modelAndView.addObject("LoginDetail", userservice.find(principal));
		} else {
			modelAndView.addObject("Login", false);
		}
	}
}
