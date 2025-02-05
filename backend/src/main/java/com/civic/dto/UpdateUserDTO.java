package com.civic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDTO {
	
	@NotBlank
	private String name;
	
	@NotNull
	private String email;
	
	private AddressDTO address;
	
}
