package com.camdevhub.jiratoolbox.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.camdevhub.jiratoolbox.AppPreferences;
import com.camdevhub.jiratoolbox.calendar.CDHCalendar;
import com.camdevhub.jiratoolbox.jira.CDHJiraClient;
import com.camdevhub.jiratoolbox.utils.JFXUtils;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class HolidaysController  extends MenuController{
	private static final Logger logger = LoggerFactory.getLogger(HolidaysController.class);

	private @FXML CDHCalendar  calendar;
	private @FXML VBox holidaysPane;
	
	private @FXML TextField issueField;
	private @FXML ProgressBar worklogProgressBar;

	public HolidaysController(CDHJiraClient jiraClient, AppPreferences prefs) {
		super(jiraClient, prefs);
	}

	@FXML
	public void initialize() {
		calendar.setColorSelection(Color.rgb(255, 165, 0, 0.5));
		calendar.setDayCellWidth(80);
		calendar.setDayCellHeight(40);
		logger.info("HolidaysController initialized");
	}

	@FXML
	private void sendHolidaysToJira() {
		if(getIssueKey().isBlank()) {
			JFXUtils.showErrorPopup("Issue Key Error", "Please, fill the Issue Key field");
			return;
		}
		if(!jiraClient.isInitialized()) {
			JFXUtils.showErrorPopup("Connection Error", "Please, login before any operation");
			return;
		}
		
		Issue issue = jiraClient.fetchIssueByIssueKey(getIssueKey());
		
		int size = calendar.getSelectedDates().size();
		double step = 100.0/size;
		resetProgressBar();
		calendar.getSelectedDates().forEach(date -> {
			logger.info("Sending Holiday date for {}", date);
			jiraClient.addWorklogInIssue(issue, date, 8);
			updateProgressBar(step);
		});
		JFXUtils.showInformationPopup("Worklog Added", "Worklog added for all the selected dates!");
	}
	
	private String getIssueKey() {
		return issueField.getText();
	}
	
	private void updateProgressBar(double step) {
		worklogProgressBar.setProgress(worklogProgressBar.getProgress()+step);
	}
	
	private void resetProgressBar() {
		worklogProgressBar.setProgress(0.);
	}

}
