package com.springboot.restapi.error;


import java.util.Date;

public class ErrorDetail {

	private Date timeStamp;
	private int code;
	private String message;
	private String details;
	
	public ErrorDetail() {}
	
	public ErrorDetail(Date timeStamp,int code, String message, String details) {
		this.timeStamp = timeStamp;
		this.code = code;
		this.message = message;
		this.details = details;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	
	
	


}
