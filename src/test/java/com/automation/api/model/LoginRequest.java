package com.automation.api.model;

public class LoginRequest {
	
	private String username = "TSMTeamAcceptance";
	private String password = "password";
	private String loginUrl = "https://acceptance.mobilekeys.assaabloy.net/MobileKeysPlatform/mobile-keys-management/invitation";
	private String issueCredentialUrl = "https://acceptance.mobilekeys.assaabloy.net/MobileKeysPlatform/mobile-keys-management/credential";
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoginUrl() {
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	public String getIssueCredentialUrl() {
		return issueCredentialUrl;
	}
	public void setIssueCredentialUrl(String issueCredentialUrl) {
		this.issueCredentialUrl = issueCredentialUrl;
	}

}
