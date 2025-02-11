package com.civic.dto;

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
	private Long userId;
	private String token;
	private String role;

}
