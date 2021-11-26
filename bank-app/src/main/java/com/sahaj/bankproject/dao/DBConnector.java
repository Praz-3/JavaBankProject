package com.sahaj.bankproject.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DBConnector {
	private static Logger log = LogManager.getLogger(DBConnector.class);
	private static Connection conn;
	private static String url, root, pwd;
	
	public DBConnector() {
		super();
		init();
	}
	
	public static Connection getConn() {
		if (conn == null) {
			setConn();
		}
		return conn;
	}
	
	private static void setConn() {
		DBConnector.init();
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, root, pwd);
			log.trace("Successfully connected to database.");
		} catch (Exception e) {
			log.fatal("Error while connecting to database\n", e);
		}
	}
	
		
	private static void init() {
		url = "jdbc:mysql://localhost:3306/sahajbankdb";
		root = "root";
		pwd = "root";
	}

}