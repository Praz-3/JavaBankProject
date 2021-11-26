package com.sahaj.bankproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sahaj.bankproject.dao.interfaces.TransactionDAOInterface;
import com.sahaj.bankproject.enums.TransactionType;
import com.sahaj.bankproject.model.Transaction;

public class TransactionDAO implements TransactionDAOInterface {
	
	private static Logger log = LogManager.getLogger(TransactionDAO.class);

	@Override
	public boolean createTransaction(Transaction trx) {
		try {
	  	    Connection conn = DBConnector.getConn();
	  	    PreparedStatement st = conn.prepareStatement("INSERT INTO TRANSACTION VALUES (?, ?, ?, ?, ?, ?)");
	  	    st.setInt(1, trx.getId());
	  	    st.setInt(2, TransactionType.typeToInt(trx.getType()));
	  	    st.setInt(3, trx.getToAccNo());
	  	    st.setInt(4, trx.getFromAccNo());
	  	    st.setDouble(5, trx.getAmount());
	  	    st.setTimestamp(6, Timestamp.valueOf(trx.getTimestamp()));
	  	    st.execute();
	  	    log.trace("Record successfully inserted into Transactions table.");
	  	    return true;
		  } catch (Exception e){
			log.warn("Error while inserting into Transactions table in database", e);
		  }
		return false;
	}

	@Override
	public boolean deleteTransaction(Transaction trx) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Transaction readTransaction(int trxID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateTransaction(Transaction trx) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getLastTrxID() {
		int lastID = 1000;
		try {
			Connection conn = DBConnector.getConn();
			PreparedStatement st = conn.prepareStatement("SELECT MAX(ID) FROM TRANSACTION");
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				lastID = rs.getInt(1);
				if(lastID == 0) {
					lastID = 1000;
				}
			}
			st.close();
			
		} catch (Exception e){
		 	log.warn("Error while accessing Transaction table in database", e);
	  	}
	      
		return lastID;
	}

}