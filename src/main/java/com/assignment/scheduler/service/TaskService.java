package com.assignment.scheduler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.scheduler.enums.Status;
import com.assignment.scheduler.model.Task;
import com.assignment.scheduler.repository.TaskRepository;
import com.assignment.scheduler.schedule.Job;
import com.assignment.scheduler.schedule.TaskScheduler;

@Service
@Transactional
public class TaskService {

	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	private ApplicationContext applicationContext;


	public void setTaskRepository(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public void save(Task task) {
		taskRepository.save(task);
	}
	
	public void saveAndSchedule(Task task) {
		task.setStatus(Status.INACTIVE);
		taskRepository.save(task);
		Job job=new Job(task.toDto());
		applicationContext.getAutowireCapableBeanFactory().autowireBean(job);
		TaskScheduler.getInstance().scheduleJob(job);
	}
	
	public void saveAndSchedule(List<Task> taskList) {
		for(Task task:taskList) {
			saveAndSchedule(task);
		}
	}

	public void delete(long id) {
		taskRepository.deleteById(id);
	}

	public List<Task> getTasks() {
		return (List<Task>) taskRepository.findAll();
	}
	
	public Task getTaskByName(String name) {
		return taskRepository.findByName(name);
	}
	
	public void updateTaskStatus(String taskName,Status status) {
		Task task=taskRepository.findByName(taskName);
		task.setStatus(status);
		taskRepository.save(task);
	}
	
	public List<Task> getTasksByStatus(Status status) {
		return (List<Task>) taskRepository.findAllByStatus(status);
	}

}
