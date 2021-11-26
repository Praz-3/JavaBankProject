package com.sahaj.bankproject.model;

import com.sahaj.bankproject.enums.AccountStatus;

public class Account {
	private int accNo;
	private int custId;
	private double balance;
	private AccountStatus status;
	private String lastDptDate;
	private int depositCount;
	private String lastWthDate;
	private int withdrawalCount;
	
	public Account(int accNo, int custId, double balance, AccountStatus status, String lastDptDate, int depositCount,
			String lastWthDate, int withdrawalCount) {
		super();
		this.accNo = accNo;
		this.custId = custId;
		this.balance = balance;
		this.status = status;
		this.lastDptDate = lastDptDate;
		this.depositCount = depositCount;
		this.lastWthDate = lastWthDate;
		this.withdrawalCount = withdrawalCount;
	}

	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public String getLastDptDate() {
		return lastDptDate;
	}

	public void setLastDptDate(String lastDptDate) {
		this.lastDptDate = lastDptDate;
	}

	public int getDepositCount() {
		return depositCount;
	}

	public void setDepositCount(int depositCount) {
		this.depositCount = depositCount;
	}

	public String getLastWthDate() {
		return lastWthDate;
	}

	public void setLastWthDate(String lastWthDate) {
		this.lastWthDate = lastWthDate;
	}

	public int getWithdrawalCount() {
		return withdrawalCount;
	}

	public void setWithdrawalCount(int withdrawalCount) {
		this.withdrawalCount = withdrawalCount;
	}




	@Override
	public String toString() {
		return "Account [accountNumber=" + accNo + ", status=" + status + ", balance="
				+ balance + "]";
	}

}