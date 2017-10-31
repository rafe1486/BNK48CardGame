package com.ip.service;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ip.component.MD5Encoder;
import com.ip.entity.User;
import com.ip.form.Profileform;
import com.ip.form.RegisterForm;
import com.ip.repository.UserRepository;

@Service
public class UserService {
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

	public User findOne(int id) {
		return userRepository.findOne(id);
	}

	public void deleteUser(int id) {
		userRepository.delete(id);
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
	
	public Profileform setUserEditForm(int id) {
		User user = userRepository.findOne(id);
		Profileform profileEditForm = new Profileform();
		profileEditForm.setId(user.getId());
		profileEditForm.setPassword(user.getPassword());
		profileEditForm.setName(user.getName());
		profileEditForm.setEmail(user.getEmail());
		profileEditForm.setProfile(user.getProfile());
		System.out.println(profileEditForm.getPassword());
		return profileEditForm;
	}

	public void updateUser(Profileform profileEditForm) throws NoSuchAlgorithmException {
		User user = userRepository.findOne(profileEditForm.getId());
		user.setName(profileEditForm.getName());
		if(profileEditForm.getPassword().compareTo("") == 0){
			
		}else {
			user.setPassword(MD5Encoder.hashMD5(profileEditForm.getPassword()));
		}
		user.setProfile(profileEditForm.getProfile());
		userRepository.save(user);
	}

	public void promoteUser(int id) {
		User user = userRepository.findOne(id);
		if(user.getRole().compareTo("Admin") == 0) {
			user.setRole("User");
		}else if (user.getRole().compareTo("User") == 0) {
		user.setRole("Admin");
		}
		userRepository.save(user);
	}

}
