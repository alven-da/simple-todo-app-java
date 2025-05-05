package com.mytodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@Autowired
	private Environment environment;
	
	@GetMapping("/")
	public String showHomePage() {
		String url = environment.getProperty("spring.datasource.username");
		System.out.println(url);
		return "index";
	}
	
	@GetMapping("/health")
	public ResponseEntity<String> healthCheck() {
		return ResponseEntity.status(HttpStatus.OK).body("OK!");
	}
	
	@GetMapping("/error-page")
    public String handleError() {
        return "error";
    }
}
