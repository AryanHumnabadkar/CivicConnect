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
@ToString(callSuper = true)
public class User extends BaseEntity{
	
	private String name;
	private String email;
	private String password;
	
	@Enumerated
	@Column(nullable = false)
	private UserRoles role;
	
	@OneToOne
	private Address address;

}
