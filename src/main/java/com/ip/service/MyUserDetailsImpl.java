package com.ip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ip.entity.User;
import com.ip.repository.UserRepository;

import java.util.Arrays;

@Service
public class MyUserDetailsImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findOneByEmail(email);
		GrantedAuthority auth = new SimpleGrantedAuthority("bnk48cardgame");
		boolean isEnabled = true;
		boolean isAccountNonExpired = true;
		boolean isCredentialsNonExpired = true;
		boolean isAccountNonLocked = true;
		UserDetails  LoginDetail = (UserDetails) new org.springframework.security.core.userdetails.User(user.getEmail(),
				user.getPassword(), isEnabled, isAccountNonExpired, isCredentialsNonExpired, isAccountNonLocked,
				Arrays.asList(auth));
		return LoginDetail;
	}
}