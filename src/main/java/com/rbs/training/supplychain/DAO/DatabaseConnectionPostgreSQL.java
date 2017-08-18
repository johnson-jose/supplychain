package com.rbs.training.supplychain.DAO;

//Sample code to test connectivity in local machine- will be updated later


import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnectionPostgreSQL {

	 public static void main(String args[]) {
	      Connection c = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/testdb",
	            "postgres", "manager");
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	   }
}
