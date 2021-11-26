package com.sahaj.bankproject.dao.interfaces;

import com.sahaj.bankproject.model.Account;

public interface AccountDAOInterface {
	public boolean createAccount(Account acc);
	public boolean deleteAccount(Account acc);
	public Account readAccount(int accNo);
	public boolean updateAccount(Account acc);
	public int getLastAccountNumber();
	public boolean isAccountPreasent(int accNo);

}