package com.civic.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateTrashReqDTO {
	
	@NotBlank
	private String description;
		
	@NotNull
	private long userId;
	

	public CreateTrashReqDTO(String description, long userId) {
		super();
		this.description = description;
		this.userId = userId;
	}
	
	
}
