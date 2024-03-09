package com.camdevhub.jiratoolbox.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class LocalDateUtils {
	private LocalDateUtils() {
		throw new AssertionError();
	}
	
	public static boolean isWorkingDay(LocalDate date) {
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
	}
}
