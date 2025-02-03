package com.civic.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginReqDTO {
	//email n pass
	
	@NotBlank(message = "Email must be supplied!")
	@Email(message = "Not valid email format!")
	private String email;
	
	@NotNull
	private String password;
	
	
}
