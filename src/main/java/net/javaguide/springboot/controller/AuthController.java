package net.javaguide.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguide.springboot.dto.RegisterDto;

@Controller
@RequestMapping("auth")
public class AuthController {
	
	@GetMapping("register")
	public String showRegisterForm(Model model) {
		RegisterDto user = new RegisterDto();
		
		model.addAttribute("user", user);
		
		return "auth/register";
	}
}
