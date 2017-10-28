package com.ip.controller;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ip.form.LoginForm;
import com.ip.form.RegisterForm;
import com.ip.service.MainService;

@Controller
public class MainController {
	
	@Autowired
	MainService mainservice;
	
	@RequestMapping("/")
	public ModelAndView home(ModelAndView modelAndView, Principal principal) {
		Integer userid = mainservice.getUserId(principal);
		if(userid != null ) {
			modelAndView.setViewName("screens/main");
		}else {
			modelAndView.addObject("RegisterForm", new RegisterForm());
			modelAndView.addObject("LoginForm", new LoginForm());
			modelAndView.setViewName("screens/index");
		}
		return modelAndView;
	}
	
	@RequestMapping("/register")
	public Object Register(@ModelAttribute("RegisterForm") @Validated RegisterForm registerForm, BindingResult bindingResult,
			RedirectAttributes attributes, ModelAndView modelAndView) throws NoSuchAlgorithmException {
		if (bindingResult.hasErrors()) {
			return "/";
		}
		mainservice.createUser(registerForm);
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/fail", method = RequestMethod.GET)
	public ModelAndView Loginfail(ModelAndView model) {
		model.addObject("msg", "Invalid name or password");
		model.addObject("loginFail", true);
		model.setViewName("screens/index");
		return model;
	}
	
	@RequestMapping("/main")
	public ModelAndView main(ModelAndView modelAndView, Principal principal) {
		modelAndView.setViewName("screens/main");
		return modelAndView;
	}
	
	
}
