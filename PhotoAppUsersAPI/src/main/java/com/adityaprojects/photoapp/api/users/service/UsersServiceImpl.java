package com.adityaprojects.photoapp.api.users.service;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.adityaprojects.photoapp.api.users.data.UserEntity;
import com.adityaprojects.photoapp.api.users.data.UserRepository;
import com.adityaprojects.photoapp.api.users.shared.UserDTO;

@Service
public class UsersServiceImpl implements UsersService {
	
	UserRepository userRepository;
	
	// To encrypt user password
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// Check below constructor based DI
	@Autowired
	public UsersServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UserDTO createUser(UserDTO userDetails) {
		// TODO Auto-generated method stub
		
		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
		
		userRepository.save(userEntity);
		
		UserDTO returnValue = modelMapper.map(userEntity, UserDTO.class);
		
		return returnValue;
	}

	// Below method will load user details by username during login attempt
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(username);
		if(userEntity == null)  throw new UsernameNotFoundException(username);
		
		// true for email verified or not
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public UserDTO getUserDetailsByEmail(String email) {
		
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null)  throw new UsernameNotFoundException(email);
		
		// Model mapper to convert from one model class to another model class
		return new ModelMapper().map(userEntity, UserDTO.class);
		

	}

}
