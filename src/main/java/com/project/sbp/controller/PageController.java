package com.project.sbp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("index");
		return model;
	}
	
	@GetMapping("/dashboard")
	public ModelAndView dashboard() {
		ModelAndView model = new ModelAndView("dashboard");
		return model;
	}
	
	@GetMapping("/inferensi")
	public ModelAndView inferensi() {
		ModelAndView model = new ModelAndView("inferensi");
		return model;
	}
	
	@GetMapping("/acquisition")
	public ModelAndView acquisition() {
		ModelAndView model = new ModelAndView("knowledge_acquisition");
		return model;
	}
	
	@GetMapping("/representation")
	public ModelAndView representation() {
		ModelAndView model = new ModelAndView("knowledge_representation");
		return model;
	}
}
