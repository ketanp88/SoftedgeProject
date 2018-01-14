package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
	
	
	
public void GetDBConnection(String query) throws Exception
{
	   final String DB_URL = "jdbc:mysql://localhost/DBName";
	   final String USER = "username";
	   final String PASS = "password";

	 //  String sql = "SELECT id, first, last, age FROM Employees";

	   Class.forName("com.mysql.jdbc.Driver");

	   Connection  conn = DriverManager.getConnection(DB_URL,USER,PASS);
	   Statement stmt = conn.createStatement();
	   ResultSet rs = stmt.executeQuery(query);

	 while(rs.next())
	     {
	         //Retrieve by column name
	         int id  = rs.getInt("id");
	         int age = rs.getInt("age");
	         String first = rs.getString("first");
	         String last = rs.getString("last");

	         //Display values
	         System.out.print("ID: " + id);
	         System.out.print(", Age: " + age);
	         System.out.print(", First: " + first);
	         System.out.println(", Last: " + last);
	      }	
	
	}

}
