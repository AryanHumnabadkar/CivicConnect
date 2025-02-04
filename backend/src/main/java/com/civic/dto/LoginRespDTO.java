package com.civic.dto;

import com.civic.pojos.User;
import com.civic.pojos.UserRoles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class LoginRespDTO {
	//UserDetails and JWT token
	private Long id;
	private String email;
	private String name;
	private UserRoles role;
	private String token;
	private String message;

	
	public LoginRespDTO(String mesg) {
		this.message = mesg;
	}
}
