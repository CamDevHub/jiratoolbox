package com.camdevhub.jiratoolbox.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.camdevhub.jiratoolbox.calendar.CDHCalendar;
import com.camdevhub.jiratoolbox.jira.CDHJiraClient;
import com.camdevhub.jiratoolbox.utils.JFXUtils;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class HolidaysController {
	private static final Logger logger = LoggerFactory.getLogger(HolidaysController.class);

	private @FXML CDHCalendar  calendar;
	private @FXML VBox holidaysPane;
	
	private @FXML TextField issueField;
	private @FXML ProgressBar worklogProgressBar;
	
	private final CDHJiraClient jiraClient;

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
		if(this.getIssueKey().isBlank()) {
			JFXUtils.showErrorPopup("Issue Key Error", "Please, fill the Issue Key field");
			return;
		}
		if(!this.jiraClient.isInitialized()) {
			JFXUtils.showErrorPopup("Connection Error", "Please, login before any operation");
			return;
		}
		
		Issue issue = this.jiraClient.fetchIssueByIssueKey(getIssueKey());
		
		int size = this.calendar.getSelectedDates().size();
		double step = 100.0/size;
		this.resetProgressBar();
		this.calendar.getSelectedDates().forEach(date -> {
			logger.info("Sending Holiday date for {}", date);
			this.jiraClient.addWorklogInIssue(issue, date, 8);
			this.updateProgressBar(step);
		});
		JFXUtils.showInformationPopup("Worklog Added", "Worklog added for all the selected dates!");
	}
	
	private String getIssueKey() {
		return this.issueField.getText();
	}
	
	private void updateProgressBar(double step) {
		this.worklogProgressBar.setProgress(this.worklogProgressBar.getProgress()+step);
	}
	
	private void resetProgressBar() {
		this.worklogProgressBar.setProgress(0.);
	}

}
