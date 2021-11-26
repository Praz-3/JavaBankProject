package com.sahaj.bankproject.service;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sahaj.bankproject.dao.TransactionDAO;
import com.sahaj.bankproject.enums.TransactionType;
import com.sahaj.bankproject.exceptions.InvalidTransactionException;
import com.sahaj.bankproject.model.Account;
import com.sahaj.bankproject.model.Bank;
import com.sahaj.bankproject.model.Customer;
import com.sahaj.bankproject.model.Transaction;

public class TransactionService {
	private static Logger log = LogManager.getLogger(TransactionService.class);
	private static TransactionDAO td = new TransactionDAO();
	private static BankService bs = new BankService();
	private static AccountService as = new AccountService();
	private static CustomerService cs = new CustomerService();
	
	public double deposit(int accNo, double amt) throws InvalidTransactionException {
		double balance =-1;
		Account acc = as.getAccount(accNo);
		Customer cust = cs.getCustomer(acc.getCustId());
		Bank bank = bs.getBank(cust.getBankIFSC());
		boolean valid = true;
		String msg= "";
		
		if(acc!=null && cust!=null && bank!=null) {
		//to check max balance limit is not crossed
		if(acc.getBalance() + amt > bank.getMaxBalance()) {
			msg = "Account balance cannot exceed ₹"+ bank.getMaxBalance();
			log.warn(msg);
			valid = false;
		}
		//to check min deposit amount
		if(amt < bank.getMinDptAmt()) {
			msg = "Minimum deposit amount is "+ bank.getMinDptAmt();
			log.warn(msg);
			valid = false;
		}
		//to check max deposit amount
		if(amt > bank.getMaxDptAmt()) {
			msg = "Maximum deposit amount is "+ bank.getMaxDptAmt();
			log.warn(msg);
			valid = false;
		}
		//to check if account number valid
		if(!AccountService.isValidAccountNo(accNo)) {
			msg = "Account number Invalid.";
			log.warn(msg);
			valid = false;
		}
		//to check deposit count
		if(acc.getLastDptDate().equals(new Date(System.currentTimeMillis()).toString())) {
			if(acc.getDepositCount() +1 > bank.getMaxDeposits()) {
				msg = "Only "+bank.getMaxDeposits()+" deposits are allowed in a day";
				log.warn(msg);
				valid = false;
			}
			acc.setDepositCount(acc.getDepositCount()+1);
		}else {
			acc.setDepositCount(1);
			acc.setLastDptDate(new Date(System.currentTimeMillis()).toString());
		}
		
		//if valid transaction, make it
		if(valid) {
			balance = acc.getBalance() + amt;
			acc.setBalance(balance);
			int id = td.getLastTrxID() +1;
			Transaction trx = new Transaction(id, accNo, accNo, TransactionType.DEPOSIT, amt
					, new Timestamp(System.currentTimeMillis()).toString());
			boolean success = td.createTransaction(trx);
			if (success) {
				boolean updated = as.updateAccount(acc);
				if(updated) {
				log.trace("Deposit Successfull for acc# "+acc.getAccNo());
				}else {
					log.warn("Deposit Unsuccessfull for acc# "+acc.getAccNo());
				}
			}else {
				log.warn("Deposit Unsuccessfull for acc# "+acc.getAccNo());
			}
		}else {
			throw new InvalidTransactionException(msg);
		}
		}
		
		return balance;
	}


	public double withdraw(int accNo, double amt) throws InvalidTransactionException {
		double balance =-1;
		Account acc = as.getAccount(accNo);
		Customer cust = cs.getCustomer(acc.getCustId());
		Bank bank = bs.getBank(cust.getBankIFSC());
		boolean valid = true;
		String msg= "";
		
		if(acc!=null && cust!=null && bank!=null) {
		//to check min balance limit is not crossed
		if(acc.getBalance() - amt < bank.getMinBalance()) {
			msg = "Account balance cannot be less than ₹"+ bank.getMinBalance();
			log.warn(msg);
			valid = false;
		}
		//to check sufficient balance
		if(acc.getBalance() < amt) {
			msg = "Insufficient balance";
			log.warn(msg);
			valid = false;
		}
		//to check min withdrawal amount
		if(amt < bank.getMinWthAmt()) {
			msg = "Minimum withdrawal amount is "+ bank.getMinWthAmt();
			log.warn(msg);
			valid = false;
		}
		//to check max withdrawal amount
		if(amt > bank.getMaxWthAmt()) {
			msg = "Maximum withdrawal amount is "+ bank.getMaxWthAmt();
			log.warn(msg);
			valid = false;
		}
		//to check if account number valid
		if(!AccountService.isValidAccountNo(accNo)) {
			msg = "Account number Invalid.";
			log.warn(msg);
			valid = false;
		}
		//to check withdrawal count
		if(acc.getLastWthDate().equals(new Date(System.currentTimeMillis()).toString())) {
			if(acc.getWithdrawalCount() +1 > bank.getMaxWithdrawals()) {
				msg = "Only "+bank.getMaxWithdrawals()+" withdrawal are allowed in a day";
				log.warn(msg);
				valid = false;
			}
			acc.setWithdrawalCount(acc.getWithdrawalCount()+1);
		}else {
			acc.setWithdrawalCount(1);
			acc.setLastWthDate(new Date(System.currentTimeMillis()).toString());
		}
		
		//if valid transaction, make it
		if(valid) {
			balance = acc.getBalance() - amt;
			acc.setBalance(balance);
			int id = td.getLastTrxID() +1;
			Transaction trx = new Transaction(id, accNo, accNo, TransactionType.WITHDRAW, amt
					, new Timestamp(System.currentTimeMillis()).toString());
			boolean success = td.createTransaction(trx);
			if (success) {
				boolean updated = as.updateAccount(acc);
				if(updated) {
				log.trace("Withdrawal Successfull for acc# "+acc.getAccNo());
				}else {
					log.warn("Withdrawal Unsuccessfull for acc# "+acc.getAccNo());
				}
			}else {
				log.warn("Withdrawal Unsuccessfull for acc# "+acc.getAccNo());
			}
		}else {
			throw new InvalidTransactionException(msg); 
		}
		}
				
		return balance;
	}
	
	
	public boolean transfer(int fromAccNo, int toAccNo, double amt) throws InvalidTransactionException {
		boolean success= false;
		double temp;
		
		temp = this.withdraw(fromAccNo, amt);
		if (temp != -1) {
			this.deposit(toAccNo, amt);
			if(temp != -1) {
				success = true;
			}
		}
				
		return success;
	}

}