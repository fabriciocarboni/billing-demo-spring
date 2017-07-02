package com.carboni.billing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Index {

	@GetMapping("/")
	public String home(){
		
		return "redirect:/titulos";
	}
	
}
