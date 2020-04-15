package com.assignment.scheduler.enums;

public enum TaskPriority {
	HIGH(1), MEDIUM(10), LOW(100);
	
	private int priority;
	
	TaskPriority(int priority){
		this.priority=priority;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}
