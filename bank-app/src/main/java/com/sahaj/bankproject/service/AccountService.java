package com.sahaj.bankproject.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sahaj.bankproject.dao.AccountDAO;
import com.sahaj.bankproject.enums.AccountStatus;
import com.sahaj.bankproject.model.Account;

public class AccountService {
	
	private static Logger log = LogManager.getLogger(AccountService.class);
    private static AccountDAO ad = new AccountDAO();
    
    public int createAccount(String name) {
    	int accNo = ad.getLastAccountNumber() + 1;
    	
    	//TODO later enhancement can be added to create multiple accounts for same customer
    	CustomerService custService = new CustomerService();
    	int custId = custService.createCustomer(name);
    	
    	Account acc = new Account(accNo, custId, 0, AccountStatus.ACTIVE, null, 0, null, 0);
    	boolean success = ad.createAccount(acc);
    	
    	if(success) {
    		log.trace("Account created successfully.");
    	}else {
    		log.warn("Account not created.");
    		accNo = -1;
    	}
    
    	return accNo;
    }
    
    public boolean updateAccount(Account acc) {
    	boolean updated = false;
    	if(isValidAccountNo(acc.getAccNo())) {
    		boolean success = ad.updateAccount(acc);
    		if(success) {
    			updated = true;
    			log.trace("Account updated successfully.");	
    		}else {
    			log.warn("Error in account update.");
    		}
        	
    	}else {
    		log.warn("Account not fetched. Invalid Account Number");
    	}
    	
    	return updated;
    }
    
    public Account getAccount(int accNo) {
    	Account acc = null;
    	
    	if(isValidAccountNo(accNo)) {
    		acc = ad.readAccount(accNo);
        	log.trace("Accountfetched successfully.");
    	}else {
    		log.warn("Account not fetched. Invalid Account Number");
    	}
    	
    	return acc;
    }
    
    
    public double currentBalance(int accNo) {
    	double bal = 0;
    
    	if(isValidAccountNo(accNo)) {
    		Account acc = ad.readAccount(accNo);
        	bal = acc.getBalance();
        	log.trace("Account balance fetched successfully.");
    	}else {
    		log.warn("Balance not fetched. Invalid Account Number");
    	}
    	
    	return bal;
    }
    
    
    public static boolean isValidAccountNo(int accNo) {
    	boolean valid = false;
    
    	//TODO here we can add conditions for a valid account number
    	
    	if (ad.isAccountPreasent(accNo)) {
    		valid = true;
    	}
    	
    	return valid;
    }
    
    //creating below method just for testing purpose
    public int getLastAccNo() {
    	return ad.getLastAccountNumber();
    }


}