package com.spring.rest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.rest.exception.ResourceNotFoundException;
import com.spring.rest.models.TaskDetails;
import com.spring.rest.repository.TaskDetailsRepository;

@Service
public class UserToDoService {
	public static Logger Log = LoggerFactory.getLogger(AdminToDoService.class);
	
	@Autowired
	private TaskDetailsRepository taskDetailsRepository;
	
	public ResponseEntity<?> getAllTaskIndividualUser(Long userId) throws ResourceNotFoundException
	{
		try {
			List<TaskDetails> allTaskOfIndividualUser = taskDetailsRepository.findAllTaskIndividualUser(userId);
			return ResponseEntity.ok().body(allTaskOfIndividualUser);
		} catch (Exception e) {
			Log.info("Excepton : " + e + " has occured.");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body( "Exception :" + e + " has occured");
		}
		
	}
	public ResponseEntity<?> getOneTaskDetailsById(Long userId, Long taskId) throws ResourceNotFoundException
	{
		try {
			TaskDetails taskById = taskDetailsRepository.findByUserIdandTaskId(userId, taskId);
			if(taskById!=null) {
				return ResponseEntity.ok().body(taskById);
			}
			else {
				Log.info("Task Not found by Id: "+taskId);
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body( "Task Not found, failed to update by Id: "+taskId);
			}
		} catch (Exception e) {
			Log.info("Excepton : " + e + " has occured.");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body( "Exception :" + e + " has occured");
		}
		
	}
	
	public ResponseEntity<?> getAllUnFinishedTaskIndividualUser(Long userId) throws ResourceNotFoundException
	{
		try {
			List<TaskDetails> allUnFinishedTaskOfIndividualUser = taskDetailsRepository.findAllUnFinishedTaskIndividualUser(userId);
			return ResponseEntity.ok().body(allUnFinishedTaskOfIndividualUser);
		} catch (Exception e) {
			Log.info("Excepton : " + e + " has occured.");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body( "Exception :" + e + " has occured");
		}
		
	}
	public ResponseEntity<?> getAllFinishedTaskIndividualUser(Long userId) throws ResourceNotFoundException
	{
		try {
			List<TaskDetails> allFinishedTaskOfIndividualUser = taskDetailsRepository.findAllFinishedTaskIndividualUser(userId);
			return ResponseEntity.ok().body(allFinishedTaskOfIndividualUser);
		} catch (Exception e) {
			Log.info("Excepton : " + e + " has occured.");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body( "Exception :" + e + " has occured");
		}
		
	}
	public ResponseEntity<?> setTaskAsDone(Long userId, Long taskId) throws ResourceNotFoundException
	{
		try {
			TaskDetails taskById = taskDetailsRepository.findByUserIdandTaskId(userId, taskId);
			if(taskById!=null) {
				taskById.setIsDone(true);
				taskById.setStatus("Finished");
				Log.info("Task updated as Done, Task id: "+taskId);
				return ResponseEntity.ok().body(taskDetailsRepository.save(taskById));
			}
			else {
				Log.info("Task Not found, failed to mark as done by Id: "+taskId);
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body( "Task Not found, failed to update by Id: "+taskId);
			}
		} catch (Exception e) {
			Log.info("Excepton : " + e + " has occured.");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body( "Exception :" + e + " has occured");
		}
		
	}
	

}
