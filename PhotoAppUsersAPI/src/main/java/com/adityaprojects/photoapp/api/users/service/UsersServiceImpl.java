package com.adityaprojects.photoapp.api.users.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adityaprojects.photoapp.api.users.data.UserEntity;
import com.adityaprojects.photoapp.api.users.data.UserRepository;
import com.adityaprojects.photoapp.api.users.shared.UserDTO;

@Service
public class UsersServiceImpl implements UsersService {
	
	UserRepository userRepository;
	
	// Check below constructor based DI
	@Autowired
	public UsersServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDTO createUser(UserDTO userDetails) {
		// TODO Auto-generated method stub
		
		userDetails.setUserId(UUID.randomUUID().toString());
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
		
		userRepository.save(userEntity);
		
		UserDTO returnValue = modelMapper.map(userEntity, UserDTO.class);
		
		return returnValue;
	}

}
