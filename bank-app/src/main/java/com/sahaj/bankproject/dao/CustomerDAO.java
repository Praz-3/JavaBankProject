package com.sahaj.bankproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sahaj.bankproject.dao.interfaces.CustomerDAOInterface;
import com.sahaj.bankproject.enums.CustomerType;
import com.sahaj.bankproject.model.Customer;

public class CustomerDAO implements CustomerDAOInterface {
	
	private static Logger log = LogManager.getLogger(CustomerDAO.class);

	@Override
	public boolean createCustomer(Customer cust) {
		try {
	  	    Connection conn = DBConnector.getConn();
	  	    PreparedStatement st = conn.prepareStatement("INSERT INTO CUSTOMER VALUES (?, ?, ?, ?, ?, ?)");
	  	    st.setInt(1, cust.getId());
	  	    st.setString(2, cust.getName());
	  	    st.setString(3, cust.getAddress());
	  	    st.setDouble(4, cust.getCIBIL());
	  	    st.setInt(5, CustomerType.typeToInt(cust.getType()));
	  	    st.setString(6, cust.getBankIFSC());
	  	    st.execute();
	  	    log.trace("Record successfully inserted into Customer table.");
	  	    return true;
		  } catch (Exception e){
			log.warn("Error while inserting into Customer table in database", e);
		  }
		return false;
	}

	@Override
	public boolean deleteCustomer(Customer cust) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Customer readCustomer(int custID) {
		Customer cust = null;
		  
	      try {
		    Connection conn = DBConnector.getConn();
		    PreparedStatement st = conn.prepareStatement("SELECT * FROM CUSTOMER WHERE ID = ?");
		    st.setInt(1, custID);
		    ResultSet rs = st.executeQuery();
		    
	    	if (rs.next()) {
	    		int id = rs.getInt("ID");
	    		String name = rs.getString("NAME");
	    		String address = rs.getString("ADDRESS");
	    		String bankIFSC = rs.getString("BANK_IFSC");
	    		double CIBIL = rs.getDouble("CIBIL");
	    		CustomerType type = CustomerType.intToType(rs.getInt("TYPE"));
	    		
			    log.trace( "Single record returned from Customer table for Customer ID: "+ custID);
		    	cust = new Customer(id, name, address, bankIFSC, CIBIL, type);
		    } else {
		    	log.info("Attempt to retreive record from Customer table failed");
		    }
		  } catch (Exception e){
		 	log.warn("Error while accessing Customer table in database", e);
	  	  }
	      
		return cust;
		
	}

	@Override
	public boolean updateCustomer(Customer cust) {
		try {
	  	    Connection conn = DBConnector.getConn();
	  	    PreparedStatement st = conn.prepareStatement("UPDATE CUSTOMER SET NAME = ?,ADDRESS = ?,CIBIL = ?,TYPE = ?,BANK_IFSC = ? WHERE ID = ?");
	  	    st.setString(1, cust.getName());
	  	    st.setString(2, cust.getAddress());
	  	    st.setDouble(3, cust.getCIBIL());
	  	    st.setInt(4, CustomerType.typeToInt(cust.getType()));
	  	    st.setString(5, cust.getBankIFSC());
	  	    st.setInt(6, cust.getId());
	  	    st.execute();
	  	    log.trace("Record successfully updated into Customer table.");
	  	    return true;
		  } catch (Exception e){
			log.warn("Error while updating Customer table in database", e);
		  }
		return false;
	}

	@Override
	public int getLastCustID() {
		int lastID = 1000;
		try {
			Connection conn = DBConnector.getConn();
			PreparedStatement st = conn.prepareStatement("SELECT MAX(ID) FROM CUSTOMER");
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				lastID = rs.getInt(1);
				if(lastID==0) {
					lastID = 1000;
				}
			}
			st.close();
			
		} catch (Exception e){
		 	log.warn("Error while accessing Customer table in database", e);
	  	}
	      
		return lastID;
	}

}