package com.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.exception.ResourceNotFoundException;
import com.spring.rest.service.UserToDoService;

@RestController
public class UsersToDoController {
	
	@Autowired
	private UserToDoService userToDoService;
	
//	@PreAuthorize("hasRole('USER')")
//	@RequestMapping(value="/user/", method = RequestMethod.GET)
//	public ResponseEntity<?> userHome()
//	{
//		return ResponseEntity.ok().body("Hello, wellcome to home page.");
//	}
	
	@PreAuthorize("hasRole('USER') and @securityService.hasEntry(#userId)")
	@RequestMapping(value="/user/gettaskdetailsbyid", method = RequestMethod.GET)
	public ResponseEntity<?>getTaskById(@RequestParam("userId") Long userId,
			@RequestParam("taskId") Long taskId) throws ResourceNotFoundException{
		
		return userToDoService.getOneTaskDetailsById(userId, taskId);
	}
	@PreAuthorize("hasRole('USER') and @securityService.hasEntry(#userId)")
	@RequestMapping(value="/user/all-task", method = RequestMethod.GET)
	public ResponseEntity<?>getAllTaskofUser(@RequestParam("userId") Long userId) throws ResourceNotFoundException{
		
		return userToDoService.getAllTaskIndividualUser(userId);
	}
	
	@PreAuthorize("hasRole('USER') and @securityService.hasEntry(#userId)")
	@RequestMapping(value="/user/all-unfinished-task", method = RequestMethod.GET)
	public ResponseEntity<?>getAllUnFinishedTask(@RequestParam("userId") Long userId) throws ResourceNotFoundException{
		
		return userToDoService.getAllUnFinishedTaskIndividualUser(userId);
	}
	@PreAuthorize("hasRole('USER') and @securityService.hasEntry(#userId)")
	@RequestMapping(value="/user/all-finished-task", method = RequestMethod.GET)
	public ResponseEntity<?>getAllFinishedTask(@RequestParam("userId") Long userId) throws ResourceNotFoundException{
		
		return userToDoService.getAllFinishedTaskIndividualUser(userId);
	}
	@PreAuthorize("hasRole('USER') and @securityService.hasEntry(#userId)")
	@RequestMapping(value="/user/mark-task-done", method = RequestMethod.PUT)
	public ResponseEntity<?>markTaskDone(@RequestParam("userId") Long userId,
			@RequestParam("taskId") Long taskId) throws ResourceNotFoundException{
		
		return userToDoService.setTaskAsDone(userId,taskId);
	}
	

}
