package com.springboot.restapi.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springboot.restapi.beans.CustomerDetails;


@Service
public class DiscountService {
	
	List<CustomerDetails> disDetails = Arrays.asList(
	new CustomerDetails("Charith", "1","0568445236", 'Y', "Employee","2019-02-10"),
	new CustomerDetails("Lakshith", "2","0568445237", 'Y', "Affiliate","2015-07-09"),
	new CustomerDetails("Sohith", "3","0568445238", 'Y', "Customer","2019-04-03"),
	new CustomerDetails("Bashitha", "4","0568445239", 'N', "Employee","2010-10-12"),
	new CustomerDetails("Tapasya", "5","0568445240", 'N', "Affiliate","2016-01-04"),
	new CustomerDetails("karthick", "6","0568445235", 'Y', "Customer","2010-09-02"),
	new CustomerDetails("Ganesh"," 7","0568445234", 'Y', "Employee","2011-12-01"),
	new CustomerDetails("Adhavith", "8","0568445233", 'Y', "Affiliate","2016-12-14"),
	new CustomerDetails("Kavya", "9","0568445232", 'Y', "Customer","2003-01-16"),
	new CustomerDetails("Bhavana", "10","0568445231", 'N', "Employee","2018-01-18"),
	new CustomerDetails("Madhu"," 11","0568445230", 'N', "Affiliate","2006-07-15"),
	new CustomerDetails("Manoj", "12","0568445241", 'Y', "Customer","2004-09-18")
	);
	
	public Map<String,CustomerDetails> getAllDetails(){
		Map<String,CustomerDetails> customer = disDetails.stream().collect(Collectors.toMap(CustomerDetails::getPhoneNumber, customerDetails -> customerDetails));
		return  customer;
	}
}
