package com.civic.dto;

import com.civic.pojos.UserRoles;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RegisterUserDTO {
	//user fields
	
	@NotBlank
	private String name;
	
	@NotNull
	@Email
    private String email;
	
	@NotNull //also add regex
    private String password;
	
	@NotNull
    private String confirmPassword;
    
	@NotNull
	@Enumerated
    private UserRoles role;

	public RegisterUserDTO(@NotBlank String name, @NotNull @Email String email, @NotNull String password,
			@NotNull String confirmPassword, @NotNull String role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.role = UserRoles.valueOf(role);
	}
	
	

    
    
}	
