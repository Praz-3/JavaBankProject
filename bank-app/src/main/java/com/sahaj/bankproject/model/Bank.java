package com.sahaj.bankproject.model;

public class Bank {
	private String IFSC, name, city, state, country, telephone;
	private double maxBalance, minBalance, maxDptAmt, minDptAmt, maxWthAmt, minWthAmt;
	private int maxDeposits, maxWithdrawals;
	
	public Bank() {
		super();
	}
	
	public Bank(String iFSC, String name, String city, String state, String country, String telephone,
			double maxBalance, double minBalance, double maxDptAmt, double minDptAmt, double maxWthAmt,
			double minWthAmt, int maxDeposits, int maxWithdrawals) {
		this();
		this.IFSC = iFSC;
		this.name = name;
		this.city = city;
		this.state = state;
		this.country = country;
		this.telephone = telephone;
		this.maxBalance = maxBalance;
		this.minBalance = minBalance;
		this.maxDptAmt = maxDptAmt;
		this.minDptAmt = minDptAmt;
		this.maxWthAmt = maxWthAmt;
		this.minWthAmt = minWthAmt;
		this.maxDeposits = maxDeposits;
		this.maxWithdrawals = maxWithdrawals;
	}
	
	public String getIFSC() {
		return IFSC;
	}
	public void setIFSC(String iFSC) {
		IFSC = iFSC;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public double getMaxBalance() {
		return maxBalance;
	}
	public void setMaxBalance(double maxBalance) {
		this.maxBalance = maxBalance;
	}
	public double getMinBalance() {
		return minBalance;
	}
	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}
	public double getMaxDptAmt() {
		return maxDptAmt;
	}
	public void setMaxDptAmt(double maxDptAmt) {
		this.maxDptAmt = maxDptAmt;
	}
	public double getMinDptAmt() {
		return minDptAmt;
	}
	public void setMinDptAmt(double minDptAmt) {
		this.minDptAmt = minDptAmt;
	}
	public double getMaxWthAmt() {
		return maxWthAmt;
	}
	public void setMaxWthAmt(double maxWthAmt) {
		this.maxWthAmt = maxWthAmt;
	}
	public double getMinWthAmt() {
		return minWthAmt;
	}
	public void setMinWthAmt(double minWthAmt) {
		this.minWthAmt = minWthAmt;
	}
	public int getMaxDeposits() {
		return maxDeposits;
	}
	public void setMaxDeposits(int maxDeposits) {
		this.maxDeposits = maxDeposits;
	}
	public int getMaxWithdrawals() {
		return maxWithdrawals;
	}
	public void setMaxWithdrawals(int maxWithdrawals) {
		this.maxWithdrawals = maxWithdrawals;
	}
	
	
}