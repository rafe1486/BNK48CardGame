package com.ip.form;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginForm {
	@NotEmpty
	private String email;
	@NotEmpty
	private String password;
}
