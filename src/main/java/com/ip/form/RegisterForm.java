package com.ip.form;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class RegisterForm {
	@NotEmpty
	private String name;
	@NotEmpty
	private String email;
	@NotEmpty
	private String password;
	
}
