package com.ip.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ip.service.MyUserDetailsImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Config extends WebSecurityConfigurerAdapter {
	@Autowired
	MyUserDetailsImpl auth;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authbuild) throws Exception {
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		authbuild.userDetailsService(auth).passwordEncoder(encoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().and().authorizeRequests()
				.antMatchers("/js/**", "/img/**", "/card/**", "/**/favicon.ico", "/fonts/**",
						"/css/**","/bootstrap/**")
				.permitAll().and().authorizeRequests()
				.antMatchers("/","/register").permitAll()
				.anyRequest().authenticated().and().csrf().disable().formLogin().loginPage("/")
				.defaultSuccessUrl("/main", true).failureUrl("/").usernameParameter("email")
				.passwordParameter("password").permitAll().and().logout().logoutUrl("/logout")
				.logoutSuccessUrl("/").logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
}