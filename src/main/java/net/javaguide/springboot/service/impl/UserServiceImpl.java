package net.javaguide.springboot.service.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import net.javaguide.springboot.dto.RegisterDto;
import net.javaguide.springboot.entity.Role;
import net.javaguide.springboot.entity.User;
import net.javaguide.springboot.repository.RoleRepository;
import net.javaguide.springboot.repository.UserRepository;
import net.javaguide.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService  {
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}



	@Override
	public void saveUser(RegisterDto registerDto) {
		Role role = this.roleRepository.findByName("ROLE_GUEST");
		
		User user = new User();
		user.setUsername(registerDto.getFirstName() + " " + registerDto.getLastName());
		user.setEmail(registerDto.getEmail());
		user.setPassword(registerDto.getPassword()); // use spring security to encrypt password
		user.setRoles(Arrays.asList(role));
		
		this.userRepository.save(user);
	} 

}
