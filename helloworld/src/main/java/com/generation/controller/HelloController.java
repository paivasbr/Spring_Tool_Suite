package com.generation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

	@GetMapping
	public String Hello() {
		return "Primeiro String";
	}
	
	@GetMapping("/mentalidade")
	public String Mentalidade() {
		return "Persistência";
	}
	
	@GetMapping("/objetivo")
	public String Objetivo() {
		return "Trabalhar atenção aos detalhes e a mentalidade de crescimento com a turma 41";
	}
}
