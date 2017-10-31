package com.ip.form;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class Profileform {
	private int id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String email;
	private String password;
	@NotEmpty
	private String profile;
	
}
