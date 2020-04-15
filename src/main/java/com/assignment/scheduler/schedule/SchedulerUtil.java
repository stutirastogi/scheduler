package com.assignment.scheduler.schedule;

import java.util.Calendar;
import java.util.Date;

public class SchedulerUtil {
	
	public static Date addTimeToCurrentTime(int minutes) {
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.MINUTE, minutes);
		return calendar.getTime();
	}

}
