package com.ip.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ip.component.LoginCheck;

@Controller
public class CardController {
	
	@Autowired
	LoginCheck logincheck;
	
	@RequestMapping("/openCard")
	public ModelAndView openCard(ModelAndView modelAndView, Principal principal) {
		modelAndView.setViewName("screens/opencard");
		return modelAndView;
	}
	
	@RequestMapping("/tradeCard")
	public ModelAndView tradeCard(ModelAndView modelAndView, Principal principal) {
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
