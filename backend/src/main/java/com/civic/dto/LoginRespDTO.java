package com.civic.dto;

import com.civic.pojos.User;
import com.civic.pojos.UserRoles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoginRespDTO {
	//UserDetails and JWT token
	private Long userId;
	private String email;
	private String name;
	private UserRoles role;
	private String token;
	private String message;


	public LoginRespDTO(User user, String token) {
		this.userId = user.getId();
	    this.email = user.getEmail();
	    this.name = user.getName();
	    this.role = user.getRole();
	    this.token = token;
	}
	
	public LoginRespDTO(String mesg) {
		this.message = mesg;
	}
}
