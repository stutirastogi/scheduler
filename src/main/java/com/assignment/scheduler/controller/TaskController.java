package com.assignment.scheduler.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.scheduler.enums.Status;
import com.assignment.scheduler.model.Task;
import com.assignment.scheduler.service.TaskService;

@RestController
@RequestMapping("/api")
public class TaskController {

	@Autowired
	TaskService taskService;

	@GetMapping("/tasks")
	public List<Task> getAllTasks() {
		return taskService.getTasks();
	}

	@PostMapping("/task")
	public void createTask(@Valid @RequestBody Task task) {
		taskService.saveAndSchedule(task);
	}

	@PostMapping("/tasks")
	public void createTask(@Valid @RequestBody List<Task> taskList) {
		taskService.saveAndSchedule(taskList);
	}

	@PutMapping("/task/{name}")
	public Task updateTask(@PathVariable(value = "name") String name, @Valid @RequestBody Task taskDetails) {
		Task task = taskService.getTaskByName(name);
		task.setStatus(taskDetails.getStatus());
		task.setDuration(taskDetails.getDuration());
		task.setPriority(taskDetails.getPriority());
		task.setStartTime(taskDetails.getStartTime());
		taskService.save(task);
		return task;
	}
	
	@GetMapping("/tasks/status/{status}")
	public List<Task> getTasksByStatus(@PathVariable(value = "status") Status status) {
		return taskService.getTasksByStatus(status);
	}

}
