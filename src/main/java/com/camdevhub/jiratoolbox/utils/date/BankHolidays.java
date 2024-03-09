package com.camdevhub.jiratoolbox.utils.date;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class BankHolidays {
	private static final Set<LocalDate> holidays = new HashSet<>();
	private static final int BASE_YEAR = 2000;
	
	static {
		holidays.add(LocalDate.of(BASE_YEAR, 1, 1));
		holidays.add(LocalDate.of(BASE_YEAR, 5, 1));
		holidays.add(LocalDate.of(BASE_YEAR, 5, 8));
		holidays.add(LocalDate.of(BASE_YEAR, 7, 14));
		holidays.add(LocalDate.of(BASE_YEAR, 12, 25));
	}
	
	private BankHolidays() {
		throw new AssertionError();
	}
	
	public static boolean isBankHolidays(LocalDate date) {
		LocalDate dateToCheck = LocalDate.of(BASE_YEAR, date.getMonthValue(), date.getDayOfMonth());
		return holidays.contains(dateToCheck);
	}
}
