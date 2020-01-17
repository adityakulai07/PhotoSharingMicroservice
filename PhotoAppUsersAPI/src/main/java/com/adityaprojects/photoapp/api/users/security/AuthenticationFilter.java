package com.adityaprojects.photoapp.api.users.security;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.adityaprojects.photoapp.api.users.service.UsersService;
import com.adityaprojects.photoapp.api.users.shared.UserDTO;
import com.adityaprojects.photoapp.api.users.ui.model.LoginRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private UsersService usersService;
	private Environment environment;

	public AuthenticationFilter(UsersService usersService,
			Environment environment,
			AuthenticationManager authenticationManager) {
		this.usersService = usersService;
		this.environment = environment;
		super.setAuthenticationManager(authenticationManager);
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
		
		try {
			
			LoginRequestModel creds = new ObjectMapper().readValue(req.getInputStream(), LoginRequestModel.class);
			
			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
																creds.getEmail(),
																creds.getPassword(),
																new ArrayList<>()));
	
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
											Authentication auth) throws IOException, ServletException {
		
		// First get username
		String userName = ((User) auth.getPrincipal()).getUsername();
		
		//Query the database using UsersService
		UserDTO userDetails = usersService.getUserDetailsByEmail(userName);
		
		// Now once we get userDetails, we will get user id and add this user id to JWT token
		String token = Jwts.builder()
						.setSubject(userDetails.getUserId())
						.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
						.signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
						.compact();
		
		// Add this token to HTTP response header 
		res.addHeader("token", token);
		
		// Add public user id too so that client app can use both user id and token for subsequent HTTP requests
		res.addHeader("userID", userDetails.getUserId());
		
	}
}
