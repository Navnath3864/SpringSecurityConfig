package com.spring.security.test.springsecuritytest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@GetMapping("/normal")
	public ResponseEntity<String> normalUser() {

		return ResponseEntity.ok("YES, i am normal user");
	}
	@GetMapping("/admin")
	public ResponseEntity<String> adminUser() {

		return ResponseEntity.ok("YES, i am admin user");
	}
	@GetMapping("/public")
	public ResponseEntity<String> publicUser() {

		return ResponseEntity.ok("YES, i am public user");
	}

}
