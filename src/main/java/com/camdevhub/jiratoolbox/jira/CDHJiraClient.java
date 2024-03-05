package com.camdevhub.jiratoolbox.jira;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

public class CDHJiraClient {
	private static final Logger logger = LoggerFactory.getLogger(CDHJiraClient.class);
	
	private String username;
	private String password;
	private String url;
	
	private JiraRestClient jiraClient;
	
	public CDHJiraClient() {
		
	}
	
	public CDHJiraClient(String username, String password, String url) {
		this.username = username;
		this.password = password;
		this.url = url;
		
		this.jiraClient = getJiraRestClient();
	}
	
	private JiraRestClient getJiraRestClient() {
		return new AsynchronousJiraRestClientFactory()
			      .createWithBasicHttpAuthentication(getJiraUri(), this.username, this.password);
	}
	
	public boolean isInitialized() {
		return this.jiraClient != null;
	}
	
	public void initialize(String username, String password, String url) {
		this.username = username;
		this.password = password;
		this.url = url;
		
		this.jiraClient = getJiraRestClient();
	}
	
	private URI getJiraUri() {
	    return URI.create(this.url);
	}
	
	public void logWorkInIssue(String issueKey) {
		//Issue issue = this.jiraClient.getIssueClient().getIssue(issueKey).claim();
		logger.info("Issue {} with id {} fetched", issueKey, issueKey);
	}
	
	public User fetchUserByName(String username) {
		User user = null;
		try {
			user = this.jiraClient.getUserClient().getUser(new URI(getJiraUri() + "rest/api/2/user?username=" + username)).claim();
			logger.info("User {} fetched", user.getDisplayName());
		} catch (URISyntaxException e) {
			logger.error("username {} malformed", username);
		}
		return user;
	}

}
