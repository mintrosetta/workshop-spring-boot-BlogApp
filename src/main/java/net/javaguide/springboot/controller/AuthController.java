package net.javaguide.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import net.javaguide.springboot.dto.RegisterDto;
import net.javaguide.springboot.entity.User;
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
	public String registerUser(
			@Valid @ModelAttribute("user") RegisterDto user, 
			BindingResult result,
			Model model) {	
		User userExiting = null;
		if (!user.getEmail().isEmpty()) {
			userExiting = this.userService.findByEmail(user.getEmail());
		}
		
		if (userExiting != null) {
			result.rejectValue("email", null, "User email is exiting");
		}
		
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			
			return "auth/register";
		}
		
		this.userService.saveUser(user);
		
		return "auth/register";
	}
}
