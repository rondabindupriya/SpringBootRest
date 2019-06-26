# SpringBootRestAPI
This project deals with the sample API which gives the discount for the customers based on the designation

Based on the requirement that employee has to get 30%,Affiliate has to get 10%,Customer with 2yrs has to get 5% and the rest of the people has to get 5%discount on every purchase of 100.

I am writing a service that accepts json input with the details JSON Input :::: "customerId":"2","customerName":"Lakshith","phoneNumber":"0568445237","groceries":"false","billAmount":"2550" URL :::: http://localhost:8080/discountValue METHOD ::: POST

We can solve this by so many ways like taking customerId as unique,taking Mobile Number as Unique.I am taking Mobile Number as Unique.

For this input json and URL I wrote an method in the controller whose return JSON output with discounted bill amount. The method takes the iput json through the bean or model and gets all the input values after sucess validation on input values like the values should not be empty.If there is any empty it will through Validation Error. After validation it will call the method in the service class whose return tpye as Map<String,CustomerDetails>.I kept mobile as key for the map and the remianing details of the customer as Value.

Note:For the sample purpose I am not using any Repository and used some static data in service class it self.

Now I am passing the mobile number from input json as key to the Map.Normally in general scenario if the customer is not available in the database for the given mobile number means first time customer it will go insert in hte database and we will give the discount option 4.

I didnt implement the insertion logic as stated that expose an api to display the net payable amount based on options.

If the customer is available then I perform hte following conditions

1.It will check the input json customer id and service method customer id is same or not 2.It will check the input json Mobile Number and service method Mobile Number is same or not 3.It will checks whether the customer is valid

Based on the above 3 conditions it will apply discount value and process some mathematical calculations and produces JSON output with discounted bill amount.

Note : I used postman client for testing purpose.

I am using the below static data in service class

"Charith", "1","0568445236", 'Y', "Employee","2019-02-10" "Lakshith", "2","0568445237", 'Y', "Affiliate","2015-07-09" "Sohith", "3","0568445238", 'Y', "Customer","2019-04-03" "Bashitha", "4","0568445239", 'N', "Employee","2010-10-12" "Tapasya", "5","0568445240", 'N', "Affiliate","2016-01-04" "karthick", "6","0568445235", 'Y', "Customer","2010-09-02" "Ganesh"," 7","0568445234", 'Y', "Employee","2011-12-01" "Adhavith", "8","0568445233", 'Y', "Affiliate","2016-12-14" "Kavya", "9","0568445232", 'Y', "Customer","2003-01-16" "Bhavana", "10","0568445231", 'N', "Employee","2018-01-18" "Madhu"," 11","0568445230", 'N', "Affiliate","2006-07-15" "Manoj", "12","0568445241", 'Y', "Customer","2004-09-18"

Other than the above data it will treat the customer as new customer and apply the option 4 discount value

I wrote the test case for testing i am taking the values also.
