package com.spring.rest.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class TaskDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long taskId;
	private String taskDescription;
	private String taskName;
	private String status;
	private Boolean isDone;
	private Boolean isActive;
	private Long taskAssignedUserId;
	
	
	public TaskDetails() {
		
	}
	public TaskDetails(String taskName, String taskDescription, Long taskAssignedUserId) {
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.status = "Not Finished";
		this.isDone = false;
		this.isActive = true;
		this.taskAssignedUserId = taskAssignedUserId;
	}
	
	public Long getTaskId() {
		return taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Boolean getIsDone() {
		return isDone;
	}
	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Long getTaskAssignedUserId() {
		return taskAssignedUserId;
	}
	public void setTaskAssignedUserId(Long taskAssignedUserId) {
		this.taskAssignedUserId = taskAssignedUserId;
	}
	@Override
	public String toString() {
		return "TaskDetails [taskId=" + taskId + ", taskDescription=" + taskDescription + ", taskName=" + taskName
				+ ", status=" + status + ", isDone=" + isDone + ", isActive=" + isActive + ", taskAssignedUserId="
				+ taskAssignedUserId + "]";
	}
	
	
	
	
	

}
