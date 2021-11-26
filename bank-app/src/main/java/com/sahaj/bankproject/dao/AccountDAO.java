package com.sahaj.bankproject.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sahaj.bankproject.dao.interfaces.AccountDAOInterface;
import com.sahaj.bankproject.enums.AccountStatus;
import com.sahaj.bankproject.model.Account;

public class AccountDAO implements AccountDAOInterface{

	private static Logger log = LogManager.getLogger(AccountDAO.class);

	@Override
	public boolean createAccount(Account acc) {
		try {
	  	    Connection testConn = DBConnector.getConn();
	  	    PreparedStatement st = testConn.prepareStatement("INSERT INTO ACCOUNT VALUES(?, ?, ?, ?, null, ?, null, ?)");
	  	    st.setInt(1, acc.getAccNo());
	  	    st.setInt(2, acc.getCustId());
	  	    st.setDouble(3, acc.getBalance());
	  	    st.setInt(4, AccountStatus.typeToInt(acc.getStatus()));
	  	    //st.setDate(5, Date.valueOf(acc.getLastDptDate()));
	  	    st.setInt(5, acc.getDepositCount());
	  	    //st.setDate(7, Date.valueOf(acc.getLastWthDate()));
	  	    st.setInt(6, acc.getWithdrawalCount());
	  	    st.execute();
	  	    log.trace("Record successfully inserted into Accounts table.");
	  	    return true;
		  } catch (Exception e){
			log.warn("Error while inserting into Accounts table in database", e);
		  }
		return false;
	}

	@Override
	public boolean deleteAccount(Account acc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account readAccount(int accNo) {
		Account acc = null;
		  
	      try {
		    Connection conn = DBConnector.getConn();
		    PreparedStatement st = conn.prepareStatement("SELECT * FROM ACCOUNT WHERE ACC_NO = ?");
		    st.setInt(1, accNo);
		    ResultSet rs = st.executeQuery();
		    
	    	if (rs.next()) {
	    		int custId = rs.getInt("CUST_ID");
	    		double balance = rs.getDouble("BALANCE");
	    		AccountStatus status = AccountStatus.intToType(rs.getInt("STATUS"));
	    		Date lastDate = rs.getDate("LAST_DPT_DATE");
	    		String lastDptDate = "";
	    		if(lastDate != null) {
	    			lastDptDate = lastDate.toString();
	    		}	    		
	    		int depositCount = rs.getInt("DEPOSIT_COUNT");
	    		lastDate = rs.getDate("LAST_WTH_DATE");
	    		String lastWthDate = "";
	    		if(lastDate != null) {
	    			lastWthDate = lastDate.toString();
	    		}
	    		int withdrawalCount = rs.getInt("WITHDRAWAL_COUNT");
	    		
			    log.trace( "Single record returned from Account table for Account Number "+ accNo);
		    	acc = new Account(accNo, custId, balance, status, lastDptDate, depositCount,
		    			lastWthDate, withdrawalCount);
		    } else {
		    	log.info("Attempt to retreive record from Accounts table failed");
		    }
		  } catch (Exception e){
		 	log.warn("Error while accessing Accounts table in database", e);
	  	  }
	      
		  return acc;
	}

	@Override
	public boolean updateAccount(Account acc) {
		try {
	  	    Connection conn = DBConnector.getConn();
	  	    String stmt = "UPDATE ACCOUNT SET BALANCE = ?,STATUS = ?";
	  	    if(!acc.getLastDptDate().equals("")) {
	  	    	stmt += ",LAST_DPT_DATE = ?";
	  	    }
	  	    stmt += ",DEPOSIT_COUNT = ?";
	  	    if(!acc.getLastWthDate().equals("")) {
	  	    	stmt += ",LAST_WTH_DATE = ?";
	  	    }
	  	    stmt += ",WITHDRAWAL_COUNT = ? WHERE ACC_NO = ?";
	  	    PreparedStatement st = conn.prepareStatement(stmt);
	  	    int i = 0;
	  	    st.setDouble(++i, acc.getBalance());
	  	    st.setInt(++i, AccountStatus.typeToInt(acc.getStatus()));
	  	    if(!acc.getLastDptDate().equals("")) {
	  	    	st.setDate(++i, Date.valueOf(acc.getLastDptDate()));
	  	    }
	  	    st.setInt(++i, acc.getDepositCount());
	  	    if(!acc.getLastWthDate().equals("")) {
	  	    	st.setDate(++i, Date.valueOf(acc.getLastWthDate()));
	  	    }
	  	    st.setInt(++i, acc.getWithdrawalCount());
	  	    st.setInt(++i, acc.getAccNo());
	  	    st.execute();
	  	    log.trace("Record successfully updated into Accounts table.");
	  	    return true;
		  } catch (Exception e){
			log.warn("Error while updating Accounts table in database", e);
		  }
		return false;
	}

	@Override
	public int getLastAccountNumber() {
		int lastID = 1000;
		try {
			Connection conn = DBConnector.getConn();
			PreparedStatement st = conn.prepareStatement("SELECT MAX(ACC_NO) FROM ACCOUNT");
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				lastID = rs.getInt(1);
				if(lastID==0) {
					lastID = 1000;
				}
			}
			st.close();
			
		} catch (Exception e){
		 	log.warn("Error while accessing Accounts table in database", e);
	  	}
	      
		return lastID;
	}

	@Override
	public boolean isAccountPreasent(int accNo) {

		boolean isPresent = false;
		
		try {
			Connection conn = DBConnector.getConn();
			PreparedStatement st = conn.prepareStatement("SELECT COUNT(*) FROM ACCOUNT WHERE ACC_NO = ?");
			st.setInt(1, accNo);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				if(count == 1){
					isPresent = true;
				}
			}
			st.close();
			
		} catch (Exception e){
		 	log.warn("Error while accessing Accounts table in database", e);
	  	}
	      
		return isPresent;
	}
	
	
}