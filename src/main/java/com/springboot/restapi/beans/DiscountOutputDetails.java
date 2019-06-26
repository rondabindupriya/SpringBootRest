package com.springboot.restapi.beans;


public class DiscountOutputDetails {
	
	private  int statusCode;
	private double discountedBillAmount;
	
	public DiscountOutputDetails() {
	}
	
	public DiscountOutputDetails(int statusCode, double discountedBillAmount) {
		this.statusCode = statusCode;
		this.discountedBillAmount = discountedBillAmount;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public double getDiscountedBillAmount() {
		return discountedBillAmount;
	}
	public void setDiscountedBillAmount(double discountedBillAmount) {
		this.discountedBillAmount = discountedBillAmount;
	}
	
	

}
