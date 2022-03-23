package com.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.models.AuthenticationRequest;
import com.spring.rest.models.AuthenticationResponse;
import com.spring.rest.service.MyUsersDetailsService;
import com.spring.rest.util.JwtUtil;

@RestController
public class HomeController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	MyUsersDetailsService myUsersDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ResponseEntity<?> adminHome()
	{
		return ResponseEntity.ok().body("Hello Admin, wellcome to home page.");
	}
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@RequestMapping(value="/user/home", method = RequestMethod.GET)
	public ResponseEntity<?> userHome()
	{
		return ResponseEntity.ok().body("Hello, wellcome to home page.");
	}
	
	@PostMapping("/account/signin")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		
//		System.out.println(authenticationRequest.toString());
		System.out.println("-----------------------------Authenticate----------------------");
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword())
					);
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect Username or Password",e);
		}
		final UserDetails userDetails = myUsersDetailsService
				.loadUserByUsername(authenticationRequest.getUserName());
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	

}
