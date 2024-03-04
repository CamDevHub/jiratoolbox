package com.camdevhub.jiratoolbox.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.camdevhub.jiratoolbox.calendar.CDHCalendar;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;

public class HolidaysController {
	private static final Logger logger = LoggerFactory.getLogger(HolidaysController.class);
	
	@FXML
	CDHCalendar calendar;
	
	@FXML
	public void initialize() {
		this.calendar.setColorSelection(Color.rgb(255, 165, 0, 0.5));
	}
	
	@FXML
	private void sendHolidaysToJira() {
		this.calendar.getSelectedDates().stream().forEach(date -> logger.info(date.toString()));
	}
	
	
}
