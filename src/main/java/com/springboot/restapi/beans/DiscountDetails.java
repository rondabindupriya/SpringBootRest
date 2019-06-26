package com.springboot.restapi.beans;


import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class DiscountDetails {
	
	@NotEmpty(message = "Please provide the Customer ID")
	private String customerId;
	
	@NotEmpty(message = "Please provide the Customer Name")
	private String customerName;
	
	@NotEmpty(message = "Please provide the Mobile Number")
	private String phoneNumber;
	
	@NotNull(message = "Please provide the Groceries Value")
	private boolean groceries;
	
	private Date timeStamp;
	private  int statusCode;
	
	@Min(1)
	private double billAmount;
	
	public DiscountDetails() {}
	
	public DiscountDetails(String customerId, String customerName, String phoneNumber, boolean groceries, double billAmount) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.phoneNumber = phoneNumber;
		this.groceries = groceries;
		this.billAmount = billAmount;
	}

	public DiscountDetails(Date timeStamp, int statusCode, double billAmount) {
		this.timeStamp = timeStamp;
		this.statusCode = statusCode;
		this.billAmount = billAmount;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isGroceries() {
		return groceries;
	}
	public void setGroceries(boolean groceries) {
		this.groceries = groceries;
	}
	public double getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}


	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	
}
