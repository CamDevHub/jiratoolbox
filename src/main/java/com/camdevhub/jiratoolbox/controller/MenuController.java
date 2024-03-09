package com.camdevhub.jiratoolbox.controller;

import com.camdevhub.jiratoolbox.AppPreferences;
import com.camdevhub.jiratoolbox.jira.CDHJiraClient;

public abstract class MenuController {
	protected final CDHJiraClient jiraClient;
	protected final AppPreferences prefs;
	
	protected MenuController(CDHJiraClient jiraClient, AppPreferences prefs) {
		this.jiraClient = jiraClient;
		this.prefs = prefs;
	}
}
