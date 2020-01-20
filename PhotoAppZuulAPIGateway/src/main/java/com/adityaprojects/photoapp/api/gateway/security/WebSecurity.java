package com.adityaprojects.photoapp.api.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import com.adityaprojects.photoapp.api.gateway.security.AuthorizationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	public final Environment environment;
	
	@Autowired
	public WebSecurity(Environment environment) {
		this.environment = environment;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// Need to access property files for Env variables
		http.csrf().disable();
		http.headers().frameOptions().disable();
		// Remove need for authorization header for Sign up and Login
		http.authorizeRequests()
		.antMatchers(environment.getProperty("api.h2console.url.path")).permitAll()
		.antMatchers(HttpMethod.POST, environment.getProperty("api.registration.url.path")).permitAll()
		.antMatchers(HttpMethod.POST, environment.getProperty("api.login.url.path")).permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilter(new AuthorizationFilter(environment, authenticationManager()));
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
}
