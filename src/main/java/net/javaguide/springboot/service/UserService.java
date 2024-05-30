package net.javaguide.springboot.service;

import net.javaguide.springboot.dto.RegisterDto;

public interface UserService {
	void saveUser(RegisterDto registerDto);
}
