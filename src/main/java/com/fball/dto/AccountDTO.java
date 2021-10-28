package com.fball.dto;

public class AccountDTO {

	private String email;
	private String password;
	private String currentPassword;
	private String confirmCrPassword;
	
	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getConfirmCrPassword() {
		return confirmCrPassword;
	}

	public void setConfirmCrPassword(String confirmCrPassword) {
		this.confirmCrPassword = confirmCrPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
