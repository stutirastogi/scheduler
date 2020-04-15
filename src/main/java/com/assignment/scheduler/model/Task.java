package com.assignment.scheduler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.assignment.scheduler.enums.Status;
import com.assignment.scheduler.enums.TaskPriority;
import com.assignment.scheduler.enums.TaskType;

@Entity
@Table(name = "task")
public class Task extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private TaskType type;

	@Column(unique = true)
	private String name;

	@Enumerated(EnumType.STRING)
	private Status status = Status.ACTIVE;

	@Enumerated(EnumType.STRING)
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

	public TaskDto toDto() {
		TaskDto taskDto = new TaskDto();
		taskDto.setId(id);
		taskDto.setDuration(duration);
		taskDto.setName(name);
		taskDto.setPriority(priority);
		taskDto.setStartTime(startTime);
		taskDto.setStatus(status);
		return taskDto;
	}

}
