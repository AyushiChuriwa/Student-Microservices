package com.institution.classroom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/")
	public String LetsBegin() {
		return "Welcome to your Classroom! You can manage your classroom here!";
	}
}
