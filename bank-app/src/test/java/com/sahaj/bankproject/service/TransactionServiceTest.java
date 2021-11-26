package com.sahaj.bankproject.service;

import org.junit.jupiter.api.Test;

import com.sahaj.bankproject.exceptions.InvalidTransactionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;


public class TransactionServiceTest {


	@Test
	public void testDeposit() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		int accNo1 = as.createAccount("Amit Dugal");
		try {
			double bal = ts.deposit(accNo1, 500);
			assertEquals(bal, 500);
		}
		catch(InvalidTransactionException e) {
			fail(e);
		}
	}
	
	@Test
	public void testMinDepositAmt() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		int accNo1 = as.createAccount("Amit Dugal");
		InvalidTransactionException e = assertThrows(
				InvalidTransactionException.class,
				() -> ts.deposit(accNo1, 100));
		assertTrue(e.getMessage().contains("Minimum deposit amount is"));
		
	}

	@Test
	public void testMaxDepositAmt() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		int accNo1 = as.createAccount("Amit Dugal");
		InvalidTransactionException e = assertThrows(
				InvalidTransactionException.class,
				() -> ts.deposit(accNo1, 60000));
		assertTrue(e.getMessage().contains("Maximum deposit amount is"));
		
	}

	@Test
	public void testMaxDepositCount() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		int accNo1 = as.createAccount("Amit Dugal");
		try {
			ts.deposit(accNo1, 500);
			ts.deposit(accNo1, 500);
			ts.deposit(accNo1, 500);
		} catch (InvalidTransactionException e1) {
			fail(e1);
		}
		InvalidTransactionException e = assertThrows(
				InvalidTransactionException.class,
				() -> ts.deposit(accNo1, 500));
		assertTrue(e.getMessage().contains("deposits are allowed in a day"));
		
	}

	@Test
	public void testWithdraw() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		int accNo1 = as.createAccount("Amit Dugal");
		try {
			ts.deposit(accNo1,11500);
			double bal = ts.withdraw(accNo1, 1000);
			assertEquals(bal, 10500);
		}
		catch(InvalidTransactionException e) {
			fail(e);
		}
	}
	
	@Test
	public void testMinWithdrawalAmt() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		int accNo1 = as.createAccount("Amit Dugal");
		try {
			ts.deposit(accNo1,11500);
		}
		catch(InvalidTransactionException e) {
			fail(e);
		}
		InvalidTransactionException e = assertThrows(
				InvalidTransactionException.class,
				() -> ts.withdraw(accNo1, 500));
		assertTrue(e.getMessage().contains("Minimum withdrawal amount is"));
		
	}

	@Test
	public void testInsufficientBalWithdrawal() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		int accNo1 = as.createAccount("Amit Dugal");
		try {
			ts.deposit(accNo1,11500);
		}
		catch(InvalidTransactionException e) {
			fail(e);
		}
		InvalidTransactionException e = assertThrows(
				InvalidTransactionException.class,
				() -> ts.withdraw(accNo1, 20000));
		assertTrue(e.getMessage().contains("Insufficient balance"));
		
	}

	@Test
	public void testMaxWithdrawalCount() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		int accNo1 = as.createAccount("Amit Dugal");
		try {
			ts.deposit(accNo1, 10000);	
			ts.withdraw(accNo1, 1000);
			ts.withdraw(accNo1, 1000);
			ts.withdraw(accNo1, 1000);
		} catch (InvalidTransactionException e1) {
			fail(e1);
		}
		InvalidTransactionException e = assertThrows(
				InvalidTransactionException.class,
				() -> ts.withdraw(accNo1, 1000));
		assertTrue(e.getMessage().contains("withdrawal are allowed in a day"));
		
		
	}
	
	@Test
	public void testTransfer() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		int accNo1 = as.createAccount("Amit Dugal");
		int accNo2 = as.createAccount("Gauri Kalla");
		boolean success = false;
		try {
			ts.deposit(accNo1, 10000);	
			ts.deposit(accNo2, 10000);	
			success = ts.transfer(accNo1, accNo2, 5000);
		} catch (InvalidTransactionException e1) {
			fail(e1);
		}
		assertTrue(success);
		
	}

	@Test
	public void testMinWithdrawalAmtTransfer() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		int accNo1 = as.createAccount("Amit Dugal");
		int accNo2 = as.createAccount("Gauri Kalla");
		try {
			ts.deposit(accNo1, 10000);	
			ts.deposit(accNo2, 10000);	
		}
		catch(InvalidTransactionException e) {
			fail(e);
		}
		InvalidTransactionException e = assertThrows(
				InvalidTransactionException.class,
				() -> ts.transfer(accNo1, accNo2, 500));
		assertTrue(e.getMessage().contains("Minimum withdrawal amount is"));
		
	}

	@Test
	public void testMaxWithdrawalAmtTransfer() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		int accNo1 = as.createAccount("Amit Dugal");
		int accNo2 = as.createAccount("Gauri Kalla");
		try {
			ts.deposit(accNo1, 10000);	
			ts.deposit(accNo2, 10000);	
		}
		catch(InvalidTransactionException e) {
			fail(e);
		}
		InvalidTransactionException e = assertThrows(
				InvalidTransactionException.class,
				() -> ts.transfer(accNo1, accNo2, 30000));
		assertTrue(e.getMessage().contains("Maximum withdrawal amount is"));
		
	}

}
