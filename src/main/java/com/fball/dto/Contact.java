package com.fball.dto;

public class Contact {

	private int id;
	private String createContact;
	private String agreeContact;
	private int state;
	private String createTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCreateContact() {
		return createContact;
	}
	public void setCreateContact(String createContact) {
		this.createContact = createContact;
	}
	public String getAgreeContact() {
		return agreeContact;
	}
	public void setAgreeContact(String agreeContact) {
		this.agreeContact = agreeContact;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
