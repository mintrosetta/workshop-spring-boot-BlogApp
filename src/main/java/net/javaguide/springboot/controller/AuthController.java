package net.javaguide.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguide.springboot.dto.RegisterDto;
import net.javaguide.springboot.service.UserService;

@Controller
@RequestMapping("auth")
public class AuthController {
	
	private UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("register")
	public String showRegisterForm(Model model) {
		RegisterDto user = new RegisterDto();
		
		model.addAttribute("user", user);
		
		return "auth/register";
	}
	
	@PostMapping("register")
	public String register(@ModelAttribute("user") RegisterDto user, Model model) {
		this.userService.saveUser(user);
		
		return "redirect:/auth/register?success";
	}
}
