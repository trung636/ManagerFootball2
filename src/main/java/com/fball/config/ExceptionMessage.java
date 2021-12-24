package com.fball.config;

import java.util.Date;


public class ExceptionMessage {

	private Date timstamp;
	private String message;
	private String detail;
	
	public ExceptionMessage(Date timstamp, String message, String detail) {
		super();
		this.timstamp = timstamp;
		this.message = message;
		this.detail = detail;
	}

	public Date getTimstamp() {
		return timstamp;
	}

	public void setTimstamp(Date timstamp) {
		this.timstamp = timstamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
