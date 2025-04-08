package com.mytodo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {
	
	@GetMapping("/")
	public String showHomePage() {
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
