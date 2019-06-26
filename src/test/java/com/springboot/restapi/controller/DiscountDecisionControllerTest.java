package com.springboot.restapi.controller;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.function.BiFunction;
import java.util.function.IntPredicate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.springboot.restapi.application.SpringBootRestAPIApplication;
import com.springboot.restapi.beans.CustomerDetails;
import com.springboot.restapi.beans.DiscountDetails;
import com.springboot.restapi.service.DiscountService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringBootRestAPIApplication.class)
@WebMvcTest(value = DiscountDecisionController.class, secure = false)
public class DiscountDecisionControllerTest {
	
	@MockBean
	DiscountService discountService;
	
	@Autowired
	MockMvc mockMvc;
	
	DiscountDetails discountDetails = new DiscountDetails("2","Lakshith","0568445237",false,2550);
	CustomerDetails customerDetails = new CustomerDetails("Lakshith", "2","0568445237", 'Y', "Affiliate","2015-07-09");
	String inputString ="{\"customerId\":\"1\",\"customerName\":\"Lakshith\",\"phoneNumber\":\"0568445237\",\"groceries\":\"false\",\"billAmount\":\"2550\"}";

	@Test
	public void testEmployeeDiscount() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/discountValue").accept(MediaType.APPLICATION_JSON).content(inputString)
				.contentType(MediaType.APPLICATION_JSON);;
		when(discountService.getAllDetails()).thenReturn(Collections.EMPTY_MAP);
		//doThrow(new RuntimeException()).when(discountService.getAllDetails());
		//verify(discountService).getAllDetails();
		double billAmount = discountDetails.getBillAmount();
		boolean groceries = discountDetails.isGroceries();
		String mobileNumber = discountDetails.getPhoneNumber();
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
		}
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		String expected = "{statusCode:200,discountedBillAmount:1912.5}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}
	private long getMonthsDiff(String registeredDate) 
	{
		LocalDate now = LocalDate.now().withDayOfMonth(LocalDate.parse(registeredDate).getDayOfMonth());
		long monthsBetween = ChronoUnit.MONTHS.between(LocalDate.parse(registeredDate),now.withDayOfMonth(LocalDate.parse(registeredDate).getDayOfMonth()));
		return monthsBetween;
	}

}
