package java7_try_with_ressource;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {		
		/*
		 * The try-with-resources Statement - 
		 * The try-with-resources statement is a try statement that declares one or more resources. 
		 * A resource is an object that must be closed after the program is finished with it. 
		 * The try-with-resources statement ensures that each resource is closed at the end of the statement.
		 * Any object that implements the new java.lang.AutoCloseable interface or 
		 * the java.io.Closeable interface can be used as a resource. 
		 * The classes java.io.InputStream, OutputStream, Reader, Writer, java.sql.Connection, Statement, 
		 * and ResultSet have been retrofitted to implement the AutoCloseable interface and 
		 * can all be used as resources in a try-with-resources statement.
		 */
		try (Scanner scan = new Scanner("a,b,c,d,e")) {
			scan.useDelimiter(",");
			while (scan.hasNext()) {
				System.out.println(scan.next());
			}
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
	}
}