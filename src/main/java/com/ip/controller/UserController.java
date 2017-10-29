package com.ip.controller;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ip.component.LoginCheck;
import com.ip.entity.User;
import com.ip.form.LoginForm;
import com.ip.form.Profileform;
import com.ip.form.RegisterForm;
import com.ip.service.UserService;
import com.ip.component.PageWrapper;

@Controller
public class UserController {
	
	@Autowired
	LoginCheck logincheck;
	
	@Autowired
	UserService userservice;
	
	@RequestMapping("/edit")
	public ModelAndView edit(ModelAndView modelAndView, Principal principal) {
		Authentication auth = (Authentication) principal;
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		User user = userservice.find(principal);
		modelAndView.addObject("userEditForm", userservice.setUserEditForm(user.getId()));
		modelAndView.setViewName("screens/editProfile");
		return modelAndView;
	}

	@PreAuthorize("hasAuthority('Admin')")
	@RequestMapping(value = "/edit/{id}")
	public ModelAndView edit(@PathVariable int id, ModelAndView modelAndView, Principal principal) {
		modelAndView.addObject("user", userservice.findOne(id));
		modelAndView.addObject("userEditForm", userservice.setUserEditForm(id));
		modelAndView.setViewName("screens/editProfile");
		return modelAndView;
	}
	
	@RequestMapping("/update")
	public Object update(@Validated Profileform profileForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, ModelAndView modelAndView, Principal principal)
			throws NoSuchAlgorithmException {
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("user", userservice.findOne(profileForm.getId()));
			modelAndView.setViewName("screens/editProfile");
			return modelAndView;
		}
		userservice.updateUser(profileForm);
		return "redirect:/";
	}

	@PreAuthorize("hasAuthority('Admin')")
	@RequestMapping("/list")
	public ModelAndView list(ModelAndView modelAndView, Principal principal, Pageable pageable) {
		Page<User> userPage = userservice.findAllUser(pageable);
		PageWrapper<User> page = new PageWrapper<>(userPage, "/list");
		modelAndView.addObject("list", page.getContent());
		modelAndView.addObject("page", page);
		modelAndView.addObject("id", userservice.getUserId(principal));
		modelAndView.setViewName("screens/list");
		return modelAndView;
	}

	@PreAuthorize("hasAuthority('Admin')")
	@RequestMapping("/delete/{id}")
	public String deleteProfile(@PathVariable int id,ModelAndView modelAndView, Principal principal,RedirectAttributes redirectAttributes) {
		userservice.deleteUser(id);
		redirectAttributes.addFlashAttribute("msg", "deleted");
		return "redirect:/list";
	}
	
	@RequestMapping("/register")
	public Object Register(@ModelAttribute("RegisterForm") @Validated RegisterForm registerForm, BindingResult bindingResult,
			RedirectAttributes attributes, ModelAndView modelAndView) throws NoSuchAlgorithmException {
		if (bindingResult.hasErrors()) {
			return "/";
		}
		userservice.createUser(registerForm);
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/fail")
	public ModelAndView Loginfail(ModelAndView modelAndView) {
		modelAndView.addObject("RegisterForm", new RegisterForm());
		modelAndView.addObject("LoginForm", new LoginForm());
		modelAndView.addObject("LoginFail", true);
		modelAndView.setViewName("screens/index");
		return modelAndView;
	}
}
