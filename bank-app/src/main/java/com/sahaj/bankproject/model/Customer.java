package com.sahaj.bankproject.model;

import com.sahaj.bankproject.enums.CustomerType;

public class Customer {
	private int id;
	private String name, address, bankIFSC;
	private double CIBIL;
	private CustomerType type;

	public Customer(int id, String name, String address, String bankIFSC, double cIBIL, CustomerType type) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.bankIFSC = bankIFSC;
		CIBIL = cIBIL;
		this.type = type;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBankIFSC() {
		return bankIFSC;
	}
	public void setBankIFSC(String bankIFSC) {
		this.bankIFSC = bankIFSC;
	}
	public double getCIBIL() {
		return CIBIL;
	}
	public void setCIBIL(double cIBIL) {
		CIBIL = cIBIL;
	}
	public CustomerType getType() {
		return type;
	}
	public void setType(CustomerType type) {
		this.type = type;
	}

}