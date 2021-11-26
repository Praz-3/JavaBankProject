package com.sahaj.bankproject.dao.interfaces;

import com.sahaj.bankproject.model.Transaction;

public interface TransactionDAOInterface {
	
	public boolean createTransaction(Transaction trx);
	public boolean deleteTransaction(Transaction trx);
	public Transaction readTransaction(int trxID);
	public boolean updateTransaction(Transaction trx);
	public int getLastTrxID();

}