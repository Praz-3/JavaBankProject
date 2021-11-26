package com.sahaj.bankproject.dao.interfaces;

import com.sahaj.bankproject.model.Bank;

public interface BankDAOInterface {
	
	public Bank readBank(String IFSC);
	public boolean createBank(Bank bank);
	public boolean updateBank(Bank bank);
	public boolean deleteBank(Bank bank);

}