package com.adityaprojects.photoapp.api.users.ui.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adityaprojects.photoapp.api.users.service.UsersService;
import com.adityaprojects.photoapp.api.users.shared.UserDTO;
import com.adityaprojects.photoapp.api.users.ui.model.CreateUserRequestModel;
import com.adityaprojects.photoapp.api.users.ui.model.CreateUserResponseModel;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	UsersService usersService;

	@GetMapping("/status/check")
	public String getStatus() {
		return "Working on port " + env.getProperty("local.server.port");
	}
	
	@PostMapping
	public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDTO userDTO = modelMapper.map(userDetails, UserDTO.class);
		
		UserDTO createdUser = usersService.createUser(userDTO);
		
		CreateUserResponseModel returnValue = modelMapper.map(createdUser,CreateUserResponseModel.class);
	
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
}
