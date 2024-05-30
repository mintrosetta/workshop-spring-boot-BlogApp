package net.javaguide.springboot.service;

import net.javaguide.springboot.dto.RegisterDto;
import net.javaguide.springboot.entity.User;

public interface UserService {
	void saveUser(RegisterDto registerDto);

	User findByEmail(String email);
}
