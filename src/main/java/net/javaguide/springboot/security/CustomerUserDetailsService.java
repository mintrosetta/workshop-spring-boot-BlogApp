package net.javaguide.springboot.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.javaguide.springboot.entity.User;
import net.javaguide.springboot.repository.UserRepository;

// create instance of for create in memory object
// this object use for manage user 
@Service
public class CustomerUserDetailsService implements UserDetailsService {
	private UserRepository userRepository;

	public CustomerUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByEmail(username);
		
		if (user != null) {
			org.springframework.security.core.userdetails.User userAuthenticated =
					new org.springframework.security.core.userdetails.User(
							user.getEmail(), 
							user.getPassword(), 
							user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
					);
		
			return userAuthenticated;
		} else {
			throw new UsernameNotFoundException("Invalid username and password");
		}
	}
	
}
