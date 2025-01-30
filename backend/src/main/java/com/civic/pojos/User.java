package com.civic.pojos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
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
