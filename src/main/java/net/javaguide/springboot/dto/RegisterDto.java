package net.javaguide.springboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
	private Long id;
	
	@NotEmpty(message = "Email should not be empty or null")
	@Email
	private String email;
	
	@NotEmpty(message = "Password should not be empty")
	private String password;

	@NotEmpty
	private String firstName;
	
	@NotEmpty
	private String lastName;
}
