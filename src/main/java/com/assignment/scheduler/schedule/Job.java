package com.assignment.scheduler.schedule;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.assignment.scheduler.enums.Status;
import com.assignment.scheduler.model.TaskDto;
import com.assignment.scheduler.service.TaskService;

public class Job implements Runnable {

	private static final Log logger = LogFactory.getLog(Job.class);

	@Autowired
	private TaskService taskService;

	private String jobName;
	private int delay;
	private int jobPriority;
	private int duration;
	private Date startTime;

	private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

	public Job(TaskDto task) {
		jobName = task.getName();
		jobPriority = task.getPriority().getPriority();
		this.delay = task.getStartTime();
		this.duration = task.getDuration();
		startTime = SchedulerUtil.addTimeToCurrentTime(delay);

	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getJobPriority() {
		return jobPriority;
	}

	public void setJobPriority(int jobPriority) {
		this.jobPriority = jobPriority;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public void run() {
		logger.info("Task:" + jobName +" started");
		taskService.updateTaskStatus(jobName, Status.ACTIVE);
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			logger.info("Task" + jobName + " Priority:" + jobPriority);
			taskService.updateTaskStatus(jobName, Status.INACTIVE);
			logger.info("Task:" + jobName +" failed");
		}
		logger.info("Task:" + jobName +" completed");
		taskService.updateTaskStatus(jobName, Status.COMPLETED);
	}
}
