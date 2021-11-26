package com.sahaj.bankproject.service;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sahaj.bankproject.model.Bank;

public class BankServiceTest {

	@Test
	public void test() {
		BankService bs = new BankService();
		String IFSC = "SAHAJ000001";
		Bank bank = bs.getBank(IFSC);
		assertEquals(bank.getName(), "Sahaj Bank");
	}

}
