package it.enaip.corso.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {

	 private static DbConnect instance;
	  
	    private Connection connection;
	    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	    private String username = "SALVATORE";
	    private String password = "0000";

	    
	  private  DbConnect() throws SQLException {
	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            this.connection = DriverManager.getConnection(url, username, password);
	            
	        } catch (ClassNotFoundException ex) {
	            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
	        }
	    }

	    public Connection getConnection() {
	        return connection;
	    }


	    
	    public static DbConnect getInstance() throws SQLException {
	        if (instance == null) {
	            instance = new DbConnect();
	        } 

	        return instance;
	    }
	
	
	
	
}
