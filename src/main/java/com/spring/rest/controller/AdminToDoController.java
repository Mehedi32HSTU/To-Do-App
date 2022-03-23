package com.spring.rest.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.exception.ResourceNotFoundException;
import com.spring.rest.models.TaskDetails;
import com.spring.rest.models.Users;
import com.spring.rest.service.AdminToDoService;

@RestController
public class AdminToDoController {
	
	@Autowired
	private AdminToDoService adminToDoService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="admin/all-tasks", method=RequestMethod.GET)
	public ResponseEntity<?> getAllTask(){
		return adminToDoService.getAllToDoTask();
	}
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="admin/get-task-details-by-id", method=RequestMethod.GET)
	public ResponseEntity<?>getTaskById(@RequestParam("taskId") Long taskId) throws ResourceNotFoundException{
		
		return adminToDoService.getOneTaskDetailsById(taskId);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="admin/create-task", method=RequestMethod.POST)
	public ResponseEntity<?> createTask(@RequestBody TaskDetails task){
		
		return adminToDoService.addNewTask(task);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="admin/all-finished", method=RequestMethod.GET)
	public ResponseEntity<?> seeAllFinishedTask(){
		
		return adminToDoService.getAllFinishedTask();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="admin/all-unfinished", method=RequestMethod.GET)
	public ResponseEntity<?> seeAllUnFinishedTask(){
		
		return adminToDoService.getAllUnFinishedTask();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="admin/individual-user/all-task", method=RequestMethod.GET)
	public ResponseEntity<?> seeAllTaskIndividualUser(@RequestParam("userId") Long userId)
			throws ResourceNotFoundException{
		
		return adminToDoService.getAllTaskIndividualUser(userId);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="admin/individual-user/all-finished", method=RequestMethod.GET)
	public ResponseEntity<?> seeAllFinishedTaskIndividualUser(@RequestParam("userId") Long userId)
			throws ResourceNotFoundException{
		
		return adminToDoService.getAllFinishedTaskIndividualUser(userId);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="admin/individual-user/all-unfinished", method=RequestMethod.GET)
	public ResponseEntity<?> seeAllUnFinishedTaskIndividualUser(@RequestParam("userId") Long userId)
			throws ResourceNotFoundException{
		
		return adminToDoService.getAllUnFinishedTaskIndividualUser(userId);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="admin/update-task", method=RequestMethod.PUT)
	public ResponseEntity<?> updateTaskById(@RequestParam("taskId") Long taskId,
			@RequestBody TaskDetails task)
			throws ResourceNotFoundException{
		
		return adminToDoService.updateTaskByTaskId(taskId,task);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="admin/mark-task-done", method=RequestMethod.PUT)
	public ResponseEntity<?> markTaskDone(@RequestParam("taskId") Long taskId)
					throws ResourceNotFoundException{
		
		return adminToDoService.setTaskAsDOne(taskId);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="admin/deleteTask", method=RequestMethod.PUT)
	public ResponseEntity<?> deleteTaskbyId(@RequestParam("taskId") Long taskId)
			throws ResourceNotFoundException{
		
		return adminToDoService.deleteTask(taskId);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="admin/add-user", method=RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody Users user) 
		throws ResourceNotFoundException{
		
		return adminToDoService.addNewUser(user);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="admin/deleteUser", method=RequestMethod.PUT)
	public ResponseEntity<?> deleteUserbyId(@RequestParam("userId") Long userId)
			throws ResourceNotFoundException{
		
		return adminToDoService.deleteUser(userId);
	}
	

}
