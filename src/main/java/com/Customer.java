package com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class Customer {
//DATABASE CONNECTION
public Connection connect()
{
Connection con = null; try
{
Class.forName("com.mysql.jdbc.Driver");
con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electro", "root", "root");
//For testing
System.out.print("Successfully connected");
}
catch(Exception e)
{
e.printStackTrace();
} return con;

} public String readCustomer()
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for reading.";
}
// Prepare the html table to be displayed
output = "<table border='1'><tr> <th>Customer Name</th><th>Customer Email</th> <th>Customer Age</th> <th>Password</th> <th>Phone</th> <th>Nic</th> <th>Update</th><th>Remove</th></tr>";
String query = "select * from customer";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
while (rs.next())
{
String customerNumber = Integer.toString(rs.getInt("customerNumber"));
String customerName = rs.getString("customerName");
String customerEmail = rs.getString("customerEmail");
String cusAge =  rs.getString("cusAge");
String password = rs.getString("password");
String phone = rs.getString("phone");
String nic = rs.getString("nic");
// Add into the html table
output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + customerNumber
+ "'>" + customerName + "</td>";
output += "<td>" + customerEmail + "</td>";
output += "<td>" + cusAge + "</td>";
output += "<td>" + password + "</td>";
output += "<td>" + phone + "</td>";
output += "<td>" + nic + "</td>";
// buttons
output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>" + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-customernumber='"
+ customerNumber + "'>" + "</td></tr>"; }
con.close();
// Complete the html table
output += "</table>";
}
catch (Exception e)
{
output = "Error while reading the Customers.";
System.err.println(e.getMessage());
}
return output;
}
public String insertCustomer(String customerName, String customerEmail,
String cusAge, String password, String phone,String nic)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for inserting.";
}
// create a prepared statement
String query = " insert into customer (`customerNumber`,`customerName`,`customerEmail`,`cusAge`,`password`,`phone`,`nic`)" + " values (?, ?, ?, ?, ?, ?, ?)";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values 
System.out.println("jkghjg");
preparedStmt.setInt(1, 0);
preparedStmt.setString(2, customerName);
preparedStmt.setString(3, customerEmail);
preparedStmt.setString(4, cusAge);
preparedStmt.setString(5, password);
preparedStmt.setString(6, phone);
preparedStmt.setString(7, nic);
// execute the statement
preparedStmt.execute();
con.close();
String newCustomers = readCustomer();
output = "{\"status\":\"success\", \"data\": \"" +
newCustomers + "\"}";
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\": \"Error while inserting the customer.\"}";
System.err.println(e.getMessage());
}
return output;
}
public String updateCustomer(String customerNumber, String customerName, String customerEmail,
String cusAge, String password,String phone,String nic)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for updating.";
}
// create a prepared statement
String query = "UPDATE customer SET customerName=?,customerEmail=?,cusAge=?,password=?,phone=?,nic=? WHERE customerNumber=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setString(1, customerName);
preparedStmt.setString(2, customerEmail);
preparedStmt.setString(3, cusAge);
preparedStmt.setString(4, password);
preparedStmt.setString(5, phone);
preparedStmt.setString(6, nic);
preparedStmt.setInt(7, Integer.parseInt(customerNumber));
// execute the statement
preparedStmt.execute();
con.close();
String newCustomers = readCustomer();
output = "{\"status\":\"success\", \"data\": \"" +
	newCustomers + "\"}";
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\": \"Error while updating the customer.\"}";
System.err.println(e.getMessage());
}
return output;
}
public String deleteCustomer(String customerNumber)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for deleting.";
}
// create a prepared statement
String query = "delete from customer where customerNumber=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, Integer.parseInt(customerNumber));
// execute the statement
preparedStmt.execute();
con.close();
String newCustomers = readCustomer();
output = "{\"status\":\"success\", \"data\": \"" +
newCustomers + "\"}";
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\": \"Error while deleting the customer.\"}";
System.err.println(e.getMessage());
}
return output;
}
}

