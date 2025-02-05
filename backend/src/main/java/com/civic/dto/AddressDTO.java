package com.civic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class AddressDTO {
	
	@NotBlank
	private String street;
	
	@NotBlank
    private String city;
	
	@NotBlank
    private String state;
	
	@NotBlank
	@NotNull
    private String sector;
}
