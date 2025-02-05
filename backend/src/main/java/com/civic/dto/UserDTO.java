package com.civic.dto;

import com.civic.pojos.UserRoles;

import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	private String name;
	private String email;
	private String password;
	
	@Enumerated
	private UserRoles role;
	
	private AddressDTO address;
}
