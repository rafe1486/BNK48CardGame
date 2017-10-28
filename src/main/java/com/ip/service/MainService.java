package com.ip.service;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ip.component.MD5Encoder;
import com.ip.entity.User;
import com.ip.form.RegisterForm;
import com.ip.repository.UserRepository;

@Component
public class MainService {
	@Autowired
	UserRepository userRepository;
	
	public Integer getUserId(Principal principal) {
		if (principal == null)
			return null;
		else {
			Authentication auth = (Authentication) principal;
			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			User user = userRepository.findOneByEmail(userDetails.getUsername());
			if (user == null)
				return null;
			else
				return user.getId();
		}
	}
	
	@Autowired
	MD5Encoder MD5Encoder;

	public void createUser(RegisterForm registerForm) throws NoSuchAlgorithmException {
		User user = new User();
		user.setName(registerForm.getName());
		user.setEmail(registerForm.getEmail());
		user.setPassword(MD5Encoder.hashMD5(registerForm.getPassword()));
		userRepository.save(user);
	}

	public List<User> findAllUser() {
		return userRepository.findAll();
	}

	public Page<User> findAllUser(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public User findOne(int userId) {
		return userRepository.findOne(userId);
	}

	public void deleteUser(int userId) {
		userRepository.delete(userId);
	}
	
	public User find(Principal principal) {
		if (principal == null) {
			return null;
		} else {
			Authentication auth = (Authentication) principal;
			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			return userRepository.findOneByEmail(userDetails.getUsername());
		}
	}

}
