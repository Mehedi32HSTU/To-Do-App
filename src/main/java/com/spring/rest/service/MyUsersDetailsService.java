package com.spring.rest.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.rest.models.MyUsersDetails;
import com.spring.rest.models.Users;
import com.spring.rest.repository.UsersRepository;
//import com.spring.rest.models.User;

@Service
public class MyUsersDetailsService implements UserDetailsService {
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<Users> users = usersRepository.findByUserName(userName);
		if(users==null) throw new UsernameNotFoundException("Not Found : "+userName);
//		user.orElseThrow(()-> new UsernameNotFoundException("Not Found : "+userName));
		System.out.println(users.toString());
		
		return users.map(MyUsersDetails :: new).get();
		
//		return new User("mehedi", "pass",new ArrayList<>());
	}
	
//	@Autowired
//	private User myUser;
	
	
//	public User save (MyUserDetails myUserDetails)
//	{
//		User newUser = new User();
//		newUser.setUserName(myUserDetails.getUsername());
//		newUser.setPassword(bcryptEncoder.encode(myUserDetails.getPassword()));
//		return myUser.save(newUser);
//		
//		
//	}
	
	

}
