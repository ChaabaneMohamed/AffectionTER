package Calcul.Calcul.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BaseHandler {
	Statement st;
	ResultSet getResultOfQuery(String query) {
		/**
		 * A Java MySQL SELECT statement example. Demonstrates the use of a SQL SELECT
		 * statement against a MySQL database, called from a Java program.
		 * 
		 * Created by Alvin Alexander, http://alvinalexander.com
		 */
		try {
			// create our mysql database connection
			String myDriver = "org.hsqldb.jdbcDriver";
			
			String myUrl = "jdbc:hsqldb:file:data/affectop";
			
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "SA", "");
			
			st = conn.createStatement();

			ResultSet rs = st.executeQuery(query);	
			return rs;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}