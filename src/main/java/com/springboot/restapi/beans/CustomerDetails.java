package com.springboot.restapi.beans;

public class CustomerDetails {
	
	private String name;
	private String id;
	private String phoneNumber;
	private char available;
	private String customerType;
	private String registrationDate;
	
	public CustomerDetails() {}
	
	public CustomerDetails(String name, String id, String phoneNumber, char available, String customerType,String registrationDate) {
		this.name = name;
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.available = available;
		this.customerType = customerType;
		this.registrationDate = registrationDate;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public char getAvailable() {
		return available;
	}
	public void setAvailable(char available) {
		this.available = available;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	

}
