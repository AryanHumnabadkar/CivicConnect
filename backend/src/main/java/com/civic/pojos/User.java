package com.civic.pojos;

import jakarta.persistence.*;

@Entity
public class User extends BaseEntity{
	
	private String name;
	private String email;
	private String password;
	@Enumerated
	private UserRoles role;
	@Transient
	private String confirmPassword;
	
	@OneToOne
	private Address address;
	
	

}
