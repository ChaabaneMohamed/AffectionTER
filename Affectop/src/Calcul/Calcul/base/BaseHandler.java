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
			String myDriver = "com.mysql.cj.jdbc.Driver";
			
			String myUrl = "jdbc:mysql://localhost:3306/affectop_BD?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC&autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf-8";
			
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "root", "1234");

			
			st = conn.createStatement();

			ResultSet rs = st.executeQuery(query);	
			return rs;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}