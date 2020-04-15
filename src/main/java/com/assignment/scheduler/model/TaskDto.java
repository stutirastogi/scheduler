package com.assignment.scheduler.model;


import org.springframework.stereotype.Component;

import com.assignment.scheduler.enums.Status;
import com.assignment.scheduler.enums.TaskPriority;
import com.assignment.scheduler.enums.TaskType;

@Component
public class TaskDto {
	
	private Long id;

	private TaskType type;
	
	private String name;

	private Status status;

	private TaskPriority priority;

	private int duration;

	private int startTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public TaskPriority getPriority() {
		return priority;
	}

	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

}
