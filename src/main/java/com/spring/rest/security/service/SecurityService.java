package com.spring.rest.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.spring.rest.exception.ResourceNotFoundException;
import com.spring.rest.models.Users;
import com.spring.rest.repository.UsersRepository;


@Service
public class SecurityService {
	
	public static Logger Log = LoggerFactory.getLogger(SecurityService.class);
	
	@Autowired
	private UsersRepository usersRepository;
	
	public boolean hasEntry( Long userId ) throws ResourceNotFoundException {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		
		Users request = usersRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
		Log.info("Request UserName: "+request.getUserName()+" Authenticated UserName: "+ userDetails.getUsername());
		
		if( request.getUserName().equals(userDetails.getUsername()) ) return true;

		return false;
	}
	
}
