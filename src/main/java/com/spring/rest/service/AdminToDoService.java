package com.spring.rest.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.rest.controller.AdminToDoController;
import com.spring.rest.exception.ResourceNotFoundException;
import com.spring.rest.models.TaskDetails;
import com.spring.rest.models.Users;
import com.spring.rest.repository.TaskDetailsRepository;
import com.spring.rest.repository.UsersRepository;

@Service
public class AdminToDoService {
	public static Logger Log = LoggerFactory.getLogger(AdminToDoService.class);
	
	@Autowired
	private TaskDetailsRepository taskDetailsRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	public ResponseEntity<?> getAllToDoTask()
	{
		try {
			
			List<TaskDetails> allTasks= taskDetailsRepository.findAllActiveTask();
			return ResponseEntity.ok().body(allTasks);
			
		} catch (Exception e) {
			
			Log.info("Excepton : " + e + " has occured.");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body( "Exception :" + e + " has occured");
		}
		
	}
	public ResponseEntity<?> getOneTaskDetailsById(Long taskId) throws ResourceNotFoundException
	{
		try {
			TaskDetails taskById = taskDetailsRepository.findByTaskId(taskId);
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
	public ResponseEntity<?> addNewTask(TaskDetails task)
	{
		TaskDetails newTask = new TaskDetails();
		try {
			newTask.setTaskName(task.getTaskName());
			newTask.setTaskDescription(task.getTaskDescription());
			newTask.setIsActive(true);
			newTask.setIsDone(false);
			newTask.setStatus("Not Finished");
			newTask.setTaskAssignedUserId(task.getTaskAssignedUserId());
			TaskDetails savedTask=taskDetailsRepository.save(newTask);
			if(savedTask != null) {
				return ResponseEntity.ok().body(savedTask);
			}
			else {
				Log.info("Failed to add new task, try again");
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body( "Failed to add new task, try again");
			}	
		} catch (Exception e) {
			Log.info("Excepton : " + e + " has occured.");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body( "Exception :" + e + " has occured");
		}
		
	}
	public ResponseEntity<?> getAllFinishedTask()
	{
		try {
			List<TaskDetails> allFinishedTask = taskDetailsRepository.findAllFinishedTask();
			return ResponseEntity.ok().body(allFinishedTask);
		} catch (Exception e) {
			Log.info("Excepton : " + e + " has occured.");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body( "Exception :" + e + " has occured");
		}
		
	}
	public ResponseEntity<?> getAllUnFinishedTask()
	{
		try {
			List<TaskDetails> allUnFinishedTask = taskDetailsRepository.findAllUnFinishedTask();
			return ResponseEntity.ok().body(allUnFinishedTask);
		} catch (Exception e) {
			Log.info("Excepton : " + e + " has occured.");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body( "Exception :" + e + " has occured");
		}
		
	}
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
	public ResponseEntity<?> updateTaskByTaskId(Long taskId, TaskDetails task) throws ResourceNotFoundException
	{
		try {
			TaskDetails taskById = taskDetailsRepository.findByTaskId(taskId);
			if(taskById!=null) {
				taskById.setIsActive(task.getIsActive());
				taskById.setIsDone(task.getIsDone());
				taskById.setStatus(task.getStatus());
				taskById.setTaskAssignedUserId(task.getTaskAssignedUserId());
				taskById.setTaskDescription(task.getTaskDescription());
				taskById.setTaskName(task.getTaskName());
				taskById=taskDetailsRepository.save(taskById);
				Log.info("Task updated, Task id: "+taskId);
				return ResponseEntity.ok().body(taskById);
			}
			else {
				Log.info("Task Not found, failed to update by Id: "+taskId);
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body( "Task Not found, failed to update by Id: "+taskId);
			}
		} catch (Exception e) {
			Log.info("Excepton : " + e + " has occured.");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body( "Exception :" + e + " has occured");
		}
		
	}
	public ResponseEntity<?> setTaskAsDOne(Long taskId) throws ResourceNotFoundException
	{
		try {
			TaskDetails taskById = taskDetailsRepository.findByTaskId(taskId);
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
	public ResponseEntity<?> deleteTask(Long taskId) throws ResourceNotFoundException
	{
		try {
			TaskDetails taskById = taskDetailsRepository.findByTaskId(taskId);
			if(taskById!=null) {
				taskById.setIsActive(false);
				taskDetailsRepository.save(taskById);
				Log.info("Task deleted successfully, Task id was: "+taskId);
				return ResponseEntity.ok().body("Task deleted successfully, Task id was: "+taskId);
			}
			else {
				Log.info("Task Not found, failed to delete by Id: "+taskId);
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body( "Task Not found, failed to update by Id: "+taskId);
			}
		} catch (Exception e) {
			Log.info("Excepton : " + e + " has occured.");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body( "Exception :" + e + " has occured");
		}
		
	}
	public ResponseEntity<?> addNewUser(Users user) throws ResourceNotFoundException{
		try {
			Optional<Users> tempUser= usersRepository.findByUserName(user.getUserName());
//			Log.info(tempUser.toString());
			
			if(tempUser.isEmpty()) {
				
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				String hashedPassword= bCryptPasswordEncoder.encode(user.getPassword());
				user.setPassword(hashedPassword);
				return ResponseEntity.ok().body(usersRepository.save(user));
			}
			else {
				Log.info("UserName already exists, try with different one. UserName is : "+user.getUserName());
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("UserName already exists, try with different one. UserName is : "+user.getUserName());
			}
		} catch (Exception e) {
			Log.info("Excepton : " + e + " has occured.");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body( "Exception :" + e + " has occured");
		}
	}
	public ResponseEntity<?> deleteUser(Long userId) throws ResourceNotFoundException
	{
		try {
			Users userById = usersRepository.findByUserId(userId);
			if(userById!=null) {
				userById.setIsActive(false);
				usersRepository.save(userById);
				Log.info("User deleted successfully, User id was: "+userId);
				return ResponseEntity.ok().body("User deleted successfully, User id was: "+userId);
			}
			else {
				Log.info("User Not found, failed to delete by Id: "+userId);
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body( "User Not found, failed to update by Id: "+userId);
			}
		} catch (Exception e) {
			Log.info("Excepton : " + e + " has occured.");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body( "Exception :" + e + " has occured");
		}
		
	}

}
