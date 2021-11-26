package com.sahaj.bankproject.dao.interfaces;

import com.sahaj.bankproject.model.Customer;

public interface CustomerDAOInterface {
	
	public boolean createCustomer(Customer cust);
	public boolean deleteCustomer(Customer cust);
	public Customer readCustomer(int custID);
	public boolean updateCustomer(Customer cust);
	public int getLastCustID();

}