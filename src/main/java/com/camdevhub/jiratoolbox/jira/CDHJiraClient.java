package com.camdevhub.jiratoolbox.jira;

import java.net.URI;
import java.time.LocalDate;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.jira.rest.client.api.domain.input.WorklogInput;
import com.atlassian.jira.rest.client.api.domain.input.WorklogInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.camdevhub.jiratoolbox.exception.JiraLoginException;

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
		return jiraClient != null;
	}
	
	public void initialize(String username, String password, String url) {
		this.username = username;
		this.password = password;
		this.url = url;
		
		this.jiraClient = getJiraRestClient();
	}
	
	private URI getJiraUri() {
	    return URI.create(url);
	}
	
	public void addWorklogInIssue(Issue issue, LocalDate date, int hour) {
		DateTime startDate = new DateTime(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), 0, 0);
		
		WorklogInputBuilder worklogInputBuilder = new WorklogInputBuilder(issue.getWorklogUri());
		worklogInputBuilder.setMinutesSpent(hour*60);
		worklogInputBuilder.setStartDate(startDate);
		WorklogInput worklogInput = worklogInputBuilder.build();
		
		jiraClient.getIssueClient().addWorklog(issue.getWorklogUri(), worklogInput);
		logger.info("Worklog of {} hours set the {} added to issue {}", worklogInput.getMinutesSpent()/60, worklogInput.getStartDate(), issue.getKey());
	}
	
	public User fetchUserByName(String username) throws JiraLoginException {
		User user = null;
		try {
			user = jiraClient.getUserClient().getUser(new URI(getJiraUri() + "rest/api/2/user?username=" + username)).claim();
			logger.info("User {} fetched", user.getDisplayName());
		} catch (Exception e) {
			throw new JiraLoginException(e.getMessage());
		}
		return user;
	}
	
	public Issue fetchIssueByIssueKey(String issueKey) {
		return jiraClient.getIssueClient().getIssue(issueKey).claim();
	}

}
