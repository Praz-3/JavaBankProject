package com.sahaj.bankproject.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sahaj.bankproject.dao.CustomerDAO;
import com.sahaj.bankproject.enums.CustomerType;
import com.sahaj.bankproject.model.Customer;

public class CustomerService {
	
	private static Logger log = LogManager.getLogger(CustomerService.class);
	private static CustomerDAO cd = new CustomerDAO();
	
	
    public int createCustomer(String name) {
    	int custID = cd.getLastCustID() + 1;
    	
    	//TODO below hard coded values can be later enhanced
    	Customer cust = new Customer(custID, name, "Pune", "SAHAJ000001", 0, CustomerType.SILVER);
    	boolean success = cd.createCustomer(cust);
    	
    	if(success) {
    		log.trace("Customer created successfully.");
    	}else {
    		log.warn("Customer not created.");
    		custID = -1;
    	}
    	
    	return custID;
    }
    
    public Customer getCustomer(int custID) {
    	Customer cust = null;
    	cust = cd.readCustomer(custID);
    	if(cust != null) {
        	log.trace("Customer fetched successfully.");
    	}else {
    		log.warn("Customer not fetched.");
    	}
    	
    	return cust;
    }


}