package com.ip.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ip.component.LoginCheck;
import com.ip.form.LoginForm;
import com.ip.form.RegisterForm;
import com.ip.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	LoginCheck logincheck;
	
	@Autowired
	UserService userservice;
	
	@RequestMapping("/")
	public ModelAndView home(ModelAndView modelAndView, Principal principal) {
		Integer userid = userservice.getUserId(principal);
		if(userid != null ) {
			modelAndView.setViewName("screens/main");
		}else {
			modelAndView.addObject("RegisterForm", new RegisterForm());
			modelAndView.addObject("LoginForm", new LoginForm());
			modelAndView.setViewName("screens/index");
		}
		return modelAndView;
	}
	
	@RequestMapping("/main")
	public ModelAndView main(ModelAndView modelAndView, Principal principal) {
		modelAndView.setViewName("screens/main");
		return modelAndView;
	}
	
	
}
