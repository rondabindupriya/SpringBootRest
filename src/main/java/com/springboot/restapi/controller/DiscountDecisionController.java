package com.springboot.restapi.controller;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.IntPredicate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restapi.beans.CustomerDetails;
import com.springboot.restapi.beans.DiscountDetails;
import com.springboot.restapi.beans.DiscountOutputDetails;
import com.springboot.restapi.service.DiscountService;

@RestController
public class DiscountDecisionController {
	
	@Autowired
	private DiscountService discountService;
	
	@RequestMapping(value="/discountValue",method= {RequestMethod.POST})
	public ResponseEntity<DiscountOutputDetails> EmployeeDiscount(@Valid @RequestBody DiscountDetails discountDetails) 
	{
		/*From the json input we are getting these values*/
		String mobileNumber = discountDetails.getPhoneNumber();
		double billAmount = discountDetails.getBillAmount();
		boolean groceries = discountDetails.isGroceries();
		
		/*I am using service instead of repository because dummy data.In real life scenario we will collect all the data in service layer from there
		 * we will call method available in repository*/
		 Map<String,CustomerDetails> employeeDetails=discountService.getAllDetails();
		 /*Here I am considering mobile number as check field.So the service returns us a Map.from the map we will pull the information based on mobile number as key*/
		 CustomerDetails customerDetails=employeeDetails.get(mobileNumber);
		//Actually In present scenario if the customer is not available it the database it will go and insert the record.
		 //Next time if the customer comes back at that time data will be available and returns the discount based on the conditions.
		 //So for the first time customers also default we will take as option 4 like  for every 100 discount 5.
		 int typeValue=0;
		 if(customerDetails!=null) {
			 BiFunction<CustomerDetails,DiscountDetails,Boolean> f= (customer,discount) -> customer.getId().equalsIgnoreCase(discount.getCustomerId()) && customer.getPhoneNumber().equals(mobileNumber);
			 
				if(f.apply(customerDetails, discountDetails))
				{
					typeValue = (customerDetails.getCustomerType().equals("Employee") && customerDetails.getAvailable()=='Y')?30:
						(customerDetails.getCustomerType().equals("Affiliate") && customerDetails.getAvailable()=='Y')?10:
						(customerDetails.getCustomerType().equals("Customer") && getMonthsDiff(customerDetails.getRegistrationDate())>=24)?5:0;
				}
		 }
		
		IntPredicate p =  i -> i==0;
		
		if(!groceries) {
			if(p.test(typeValue)) 
			{
				typeValue =(int)billAmount/100;
				billAmount=  billAmount-(billAmount*typeValue)/100;
			}
			else 
			{
				billAmount = billAmount-(billAmount*typeValue)/100;
			}
			DiscountOutputDetails discountOutputDetails = new DiscountOutputDetails(200, billAmount);
			return new ResponseEntity<>(discountOutputDetails, HttpStatus.OK);
		}
		DiscountOutputDetails discountOutputDetails = new DiscountOutputDetails(200, billAmount);
		return new ResponseEntity<>(discountOutputDetails, HttpStatus.OK);
		}
		/*Method to calculate the number of months for discount*/
	private long getMonthsDiff(String registeredDate) 
	{
		LocalDate now = LocalDate.now().withDayOfMonth(LocalDate.parse(registeredDate).getDayOfMonth());
		long monthsBetween = ChronoUnit.MONTHS.between(LocalDate.parse(registeredDate),now.withDayOfMonth(LocalDate.parse(registeredDate).getDayOfMonth()));
		return monthsBetween;
	}
}
