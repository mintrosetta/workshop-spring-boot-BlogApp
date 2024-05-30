package net.javaguide.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguide.springboot.dto.RegisterDto;

@Controller
@RequestMapping("auth")
public class AuthController {
	public String showRegisterForm(Model model) {
		RegisterDto registerDto = new RegisterDto();
		
		model.addAttribute("register", registerDto);
		
		return "auth/register";
	}
}
