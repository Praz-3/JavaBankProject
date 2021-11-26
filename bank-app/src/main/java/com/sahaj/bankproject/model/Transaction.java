package com.sahaj.bankproject.model;

import com.sahaj.bankproject.enums.TransactionType;

public class Transaction {
	private int id, toAccNo, fromAccNo;
	private TransactionType type;
	private double amount;
	private String timestamp;
	
	public Transaction(int id, int toAccNo, int fromAccNo, TransactionType type, double amount, String timestamp) {
		super();
		this.id = id;
		this.toAccNo = toAccNo;
		this.fromAccNo = fromAccNo;
		this.type = type;
		this.amount = amount;
		this.timestamp = timestamp;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getToAccNo() {
		return toAccNo;
	}
	public void setToAccNo(int toAccNo) {
		this.toAccNo = toAccNo;
	}
	public int getFromAccNo() {
		return fromAccNo;
	}
	public void setFromAccNo(int fromAccNo) {
		this.fromAccNo = fromAccNo;
	}
	public TransactionType getType() {
		return type;
	}
	public void setType(TransactionType type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
}