package com.camdevhub.jiratoolbox.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.rest.client.api.domain.User;
import com.camdevhub.jiratoolbox.AppPreferences;
import com.camdevhub.jiratoolbox.exception.JiraLoginException;
import com.camdevhub.jiratoolbox.jira.CDHJiraClient;
import com.camdevhub.jiratoolbox.utils.JFXUtils;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends MenuController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	private @FXML TextField usernameField;
	private @FXML PasswordField passwordField;
	private @FXML TextField urlField;
	private @FXML CheckBox rememberCheckbox;

	public LoginController(CDHJiraClient jiraClient, AppPreferences prefs) {
		super(jiraClient, prefs);
	}

	@FXML
	public void initialize() {
		loadLogin();
		logger.info("LoginController initialized");
	}

	@FXML
	private void processLogin() {
		saveLogin();
		loadJiraClient();
	}

	private void saveLogin() {
		prefs.saveUsername(usernameField.getText());
		prefs.saveUrl(urlField.getText());
		prefs.saveRemember(Boolean.toString(rememberCheckbox.isSelected()));
	}

	private void loadLogin() {
		usernameField.setText(prefs.loadUsername());
		urlField.setText(prefs.loadUrl());
		rememberCheckbox.setSelected(prefs.loadRemember());
	}

	private void loadJiraClient() {
		this.jiraClient.initialize(getUsername(), getPassword(), getUrl());
		try {
			User user = this.jiraClient.fetchUserByName(getUsername());

			if (user != null && user.getName().equals(getUsername())) {
				JFXUtils.showInformationPopup("Connection Successful",
						"Connection was a success and your user " + user.getName() + " successfully fetched");
			}
		} catch (JiraLoginException e) {
			JFXUtils.showErrorPopup("Connection Error", "Check your credentials, hostname and internet connection");
		}
	}

	private String getPassword() {
		return passwordField.getText();
	}

	private String getUsername() {
		return usernameField.getText();
	}

	private String getUrl() {
		return urlField.getText();
	}
}
