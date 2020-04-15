package com.assignment.scheduler.schedule;

import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TaskScheduler {

	private static final Log logger = LogFactory.getLog(TaskScheduler.class);
	private static final int QUEUE_SIZE = 10;
	private static final int THREAD_POOL_SIZE = 2;

	private ScheduledExecutorService priorityJobPoolExecutor;
	private ExecutorService priorityJobScheduler = Executors.newSingleThreadExecutor();
	private PriorityBlockingQueue<Job> priorityQueue;

	private TaskScheduler() {
		priorityJobPoolExecutor = Executors.newScheduledThreadPool(THREAD_POOL_SIZE);
		priorityQueue = new PriorityBlockingQueue<Job>(QUEUE_SIZE,
				Comparator.comparing(Job::getJobPriority).thenComparing(Job::getDelay));
		priorityJobScheduler.execute(() -> {
			while (true) {
				try {
					Job job = priorityQueue.take();
					priorityJobPoolExecutor.schedule(job, job.getDelay(), TimeUnit.MINUTES);
					logger.info(job.getJobName() + " scheduled");
				} catch (InterruptedException e) {
					break;
				}
			}
		});
	}

	private static class TaskSchedulerHelper {
		private static final TaskScheduler INSTANCE = new TaskScheduler();
	}

	public static TaskScheduler getInstance() {
		return TaskSchedulerHelper.INSTANCE;
	}

	public void scheduleJob(Job job) {
		priorityQueue.add(job);
	}
}
