package java7_multicatch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
	
	public static void main(String[] args) {		
		/*
		 * Catching Multiple Exception Types and Rethrowing Exceptions with Improved Type Checking - 
		 * A single catch block can handle more than one type of exception. 
		 * In addition, the compiler performs more precise analysis of rethrown exceptions than earlier releases of Java SE. 
		 * This enables you to specify more specific exception types in the throws clause of a method declaration.
		 */
		try {
			Connection con = DriverManager.getConnection(args[0], args[1], args[2]);
			con.close();
		} catch (SQLException | RuntimeException ex) {
			System.out.println(ex.getMessage());
		}
	}
}