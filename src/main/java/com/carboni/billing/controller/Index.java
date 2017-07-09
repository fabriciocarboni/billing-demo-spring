package com.carboni.billing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Index {
	
	@GetMapping("/")
	private String Index() {
		
		return("redirect:/titulos");
		
	}

}
