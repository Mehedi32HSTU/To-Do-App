package com.spring.rest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.rest.models.TaskDetails;

@Repository
public interface TaskDetailsRepository extends JpaRepository<TaskDetails, Long> {
	@Query(value = "select * from task_details where task_details.is_active = true", nativeQuery = true)
	List<TaskDetails> findAllActiveTask();
	
	@Query(value = "select * from task_details where task_details.is_active = true and task_details.task_id=?1", nativeQuery = true)
	TaskDetails findByTaskId(Long taskId);
	
	@Query(value = "select * from task_details where task_details.is_active = true and task_details.task_assigned_user_id =?1 and task_details.task_id=?2 ", nativeQuery = true)
	TaskDetails findByUserIdandTaskId(Long userId, Long taskId);
	
	@Query(value = "select * from task_details where task_details.is_active = true and task_details.is_Done = true", nativeQuery = true)
	List<TaskDetails> findAllFinishedTask();
	
	@Query(value = "select * from task_details where task_details.is_active = true and task_details.is_Done = false", nativeQuery = true)
	List<TaskDetails> findAllUnFinishedTask();
	
	@Query(value = "select * from task_details where task_details.is_active = true and task_details.task_assigned_user_id =?1", nativeQuery = true)
	List<TaskDetails> findAllTaskIndividualUser(Long userId);
	
	@Query(value = "select * from task_details where task_details.is_active = true and task_details.task_assigned_user_id =?1 and task_details.is_Done = true", nativeQuery = true)
	List<TaskDetails> findAllFinishedTaskIndividualUser(Long userId);
	
	@Query(value = "select * from task_details where task_details.is_active = true and task_details.task_assigned_user_id =?1 and task_details.is_Done = false", nativeQuery = true)
	List<TaskDetails> findAllUnFinishedTaskIndividualUser(Long userId);

}
