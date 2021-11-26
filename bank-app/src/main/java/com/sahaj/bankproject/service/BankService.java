package com.sahaj.bankproject.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sahaj.bankproject.dao.BankDAO;
import com.sahaj.bankproject.model.Bank;

public class BankService {
	private static Logger log = LogManager.getLogger(BankService.class);
	private static BankDAO bd = new BankDAO();
	
	public Bank getBank(String IFSC) {
		Bank bank = bd.readBank(IFSC);
		if(bank != null) {
			log.trace("Bank fetched successfully.");
		}else {
			log.warn("Bank not fetched");
		}
		
		return bank;
	}


}