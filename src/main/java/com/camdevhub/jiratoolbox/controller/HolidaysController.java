package com.camdevhub.jiratoolbox.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.camdevhub.jiratoolbox.calendar.CDHCalendar;
import com.camdevhub.jiratoolbox.jira.CDHJiraClient;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class HolidaysController {
	private static final Logger logger = LoggerFactory.getLogger(HolidaysController.class);

	private CDHJiraClient jiraClient;

	@FXML
	CDHCalendar calendar;

	@FXML
	VBox holidaysPane;

	public HolidaysController(CDHJiraClient jiraClient) {
		this.jiraClient = jiraClient;
	}

	@FXML
	public void initialize() {
		this.calendar.setColorSelection(Color.rgb(255, 165, 0, 0.5));
		this.calendar.setDayCellWidth(80);
		this.calendar.setDayCellHeight(40);
		logger.info("HolidaysController initialized");
	}

	@FXML
	private void sendHolidaysToJira() {
		this.calendar.getSelectedDates().stream().forEach(date -> {
			logger.info("Sending Holiday date for {}", date.toString());
			this.jiraClient.logWorkInIssue(null);
		});
	}

}
