package com.camdevhub.jiratoolbox.controller;

import com.camdevhub.jiratoolbox.jira.CDHJiraClient;

public abstract class MenuController {
	protected final CDHJiraClient jiraClient;
	
	protected MenuController(CDHJiraClient jiraClient) {
		this.jiraClient = jiraClient;
	}
}
