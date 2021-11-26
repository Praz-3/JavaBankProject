package com.sahaj.bankproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sahaj.bankproject.dao.interfaces.BankDAOInterface;
import com.sahaj.bankproject.model.Bank;

public class BankDAO implements BankDAOInterface {
	
	private static Logger log = LogManager.getLogger(BankDAO.class);
	
	public BankDAO() {
		super();
	}

	@Override
	public Bank readBank(String IFSC) {
		Bank bank = null;
		  
	      try {
		    Connection conn = DBConnector.getConn();
		    PreparedStatement st = conn.prepareStatement("SELECT * FROM BANK WHERE IFSC = ?");
		    st.setString(1, IFSC);
		    ResultSet rs = st.executeQuery();
		    
	    	if (rs.next()) {
	    		String name = rs.getString("NAME");
	    		String city = rs.getString("CITY");
	    		String state = rs.getString("STATE");
	    		String country = rs.getString("COUNTRY");
	    		String telephone = rs.getString("TELEPHONE");
	    		double maxBalance = rs.getDouble("MAX_BALANCE");
	    		double minBalance = rs.getDouble("MIN_BALANCE");
	    		double maxDptAmt = rs.getDouble("MAX_DPT_AMT");
	    		double minDptAmt = rs.getDouble("MIN_DPT_AMT");
	    		double maxWthAmt = rs.getDouble("MAX_WTH_AMT");
	    		double  minWthAmt = rs.getDouble("MIN_WTH_AMT");
	    		int maxDeposits = rs.getInt("MAX_DEPOSITS");
	    		int maxWithdrawals = rs.getInt("MAX_WITHDRAWALS");
		    	
			    log.trace( "Single record returned from Bank table for IFSC "+ IFSC);
		    	bank = new Bank(IFSC, name, city, state, country, telephone,
		    			maxBalance, minBalance, maxDptAmt, minDptAmt, maxWthAmt,
		    			minWthAmt, maxDeposits, maxWithdrawals);
		    } else {
		    	log.info("Attempt to retreive record from Accounts table failed");
		    }
		  } catch (Exception e){
		 	log.warn("Error while accessing Accounts table in database", e);
	  	  }
	      
		  return bank;

	}


	// TODO other methods to be developed, currently out of scope
	
	@Override
	public boolean createBank(Bank bank) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBank(Bank bank) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBank(Bank bank) {
		// TODO Auto-generated method stub
		return false;
	}
	

}