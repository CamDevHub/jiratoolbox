package com.camdevhub.jiratoolbox.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.rest.client.api.domain.User;
import com.camdevhub.jiratoolbox.jira.CDHJiraClient;
import com.camdevhub.jiratoolbox.utils.JFXUtils;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	private @FXML TextField usernameField;
	private @FXML PasswordField passwordField;
	private @FXML TextField urlField;

	private final CDHJiraClient jiraClient;

	public LoginController(CDHJiraClient jiraClient) {
		this.jiraClient = jiraClient;
	}

	@FXML
	public void initialize() {
		logger.info("LoginController initialized");
	}

	@FXML
	private void loadJiraClient() {
		this.jiraClient.initialize(getUsername(), getPassword(), getUrl());
		User user = this.jiraClient.fetchUserByName(getUsername());
		
		if(user != null && user.getName().equals(getUsername())) {
			JFXUtils.showInformationPopup("Connection Successful", "Connection was a success and your user " + user.getName() + " successfully fetched");
		}
	}

	private String getPassword() {
		return this.passwordField.getText();
	}

	private String getUsername() {
		return this.usernameField.getText();
	}

	private String getUrl() {
		return this.urlField.getText();
	}
}