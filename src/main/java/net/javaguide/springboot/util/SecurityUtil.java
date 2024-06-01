package net.javaguide.springboot.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SecurityUtil {
	public static User getCurrentUser() {
		// get current user is logged in
		// principal contains (username, password, role)
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof User) {
			return (User) principal;
		}
		
		return null;
	}
}
