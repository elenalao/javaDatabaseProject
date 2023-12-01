package Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabasePractice {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (ClassNotFoundException ex) {
			System.out.println("Problem in loading the driver");
			ex.printStackTrace();
		}

		try {
			// Database file name with file extension
			String dbName = "Employee.accdb";
			// Database path URL
			String dbURL = "jdbc:ucanaccess://" + dbName;
			// Driver manager
			conn = DriverManager.getConnection(dbURL);
			// Retrieve as well as apply query
			stat = conn.createStatement();

			// Insert data into table
			String query1 = "INSERT INTO EMP (Ename, Salary) " + "values ('ABC', 65000)";
			// Execute the insertion
			stat.executeUpdate(query1);

			// Getting user input
			String n = "John";
			double sa = 66000.00;
			String query2 = "INSERT INTO EMP (Ename, Salary) " + "values ('" + n + "', " + sa + ")";

			// Insert data
			stat.executeUpdate(query2);

			// Update data
			String query3 = "UPDATE EMP SET Salary = 120000 " + "where EName = 'ABC'";
			stat.executeUpdate(query3);

			// Delete data
			String query4 = "DELETE FROM EMP where EName = 'John'";
			stat.executeUpdate(query4);

			// Execute query
			rs = stat.executeQuery("SELECT * FROM Emp");
			// Use while loop to retrieve data from each row
			int id;
			String name;
			double salary;
			while (rs.next()) {
				id = rs.getInt(1); // Getting data from first column
				name = rs.getString(2); // Getting data from second column
				salary = rs.getDouble(3); // Getting data from third column
				System.out.println("id " + id + " name " + name + " salary " + salary);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		// Close connection in finally block
		finally {
			try {
				if (conn != null) {
					rs.close();
					stat.close();
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

	}

}
