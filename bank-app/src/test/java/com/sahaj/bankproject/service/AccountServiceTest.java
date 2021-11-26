package com.sahaj.bankproject.service;

import org.junit.jupiter.api.Test;

import com.sahaj.bankproject.exceptions.InvalidTransactionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AccountServiceTest {

	@Test
	public void testCreateAccount() {
		
		AccountService as = new AccountService();
		
		int lastAccNo = as.getLastAccNo();
		int accNo1 = as.createAccount("Amit Dugal");
		assertEquals(lastAccNo+1, accNo1);
		
	}
	
	@Test
	public void testBalance() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		int accNo1 = as.createAccount("Amit Dugal");
		try {
			ts.deposit(accNo1, 500);
			ts.deposit(accNo1, 1000);
			ts.deposit(accNo1, 10000);
			double expectedBal = 11500;
			double bal = as.currentBalance(accNo1);
			assertEquals(expectedBal, bal);
		}
		catch(InvalidTransactionException e) {
			fail(e);
		}
		
	}

}