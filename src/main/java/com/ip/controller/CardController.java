package com.ip.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ip.component.LoginCheck;
import com.ip.component.PageWrapper;
import com.ip.entity.User;
import com.ip.service.UserService;

@Controller
public class CardController {
	
	@Autowired
	LoginCheck logincheck;

	@Autowired
	UserService userservice;
	
	@RequestMapping("/openCard")
	public ModelAndView openCard(ModelAndView modelAndView, Principal principal) {
		modelAndView.setViewName("screens/opencard");
		return modelAndView;
	}
	
	@RequestMapping("/tradeCard")
	public ModelAndView tradeCard(ModelAndView modelAndView, Principal principal, Pageable pageable) {
		Page<User> userPage = userservice.findAllUser(pageable);
		PageWrapper<User> page = new PageWrapper<>(userPage, "/tradeCard");
		modelAndView.addObject("list", page.getContent());
		modelAndView.addObject("page", page);
		modelAndView.addObject("id", userservice.getUserId(principal));
		modelAndView.setViewName("screens/tradeCard");
		return modelAndView;
	}
	
	@RequestMapping("/tradeCard/{cardid}/{userid}")
	public ModelAndView tradeCardRequest(ModelAndView modelAndView, Principal principal) {
		modelAndView.setViewName("screens/tradeCard");
		return modelAndView;
	}
	
	@RequestMapping("/tradeCard/{id}/accept")
	public ModelAndView tradeCardAccept(ModelAndView modelAndView, Principal principal) {
		modelAndView.setViewName("screens/tradeCard");
		return modelAndView;
	}
	
	@RequestMapping("/tradeCard/{id}/cancle")
	public ModelAndView tradeCardCancle(ModelAndView modelAndView, Principal principal) {
		modelAndView.setViewName("screens/tradeCard");
		return modelAndView;
	}
}
