package com.camdevhub.jiratoolbox.controller;

import com.camdevhub.jiratoolbox.calendar.CDHCalendar;

import javafx.fxml.FXML;

public class HolidaysController {
	
	@FXML
	CDHCalendar calendar;
	
	@FXML
	public void initialize() {

	}
	
	@FXML
	private void sendHolidaysToJira() {
		this.calendar.getSelectedDates().forEach(System.out::println);
		
	}
}
