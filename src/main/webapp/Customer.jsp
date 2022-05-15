<%@page import="com.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/customer.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Customer Management V10.1</h1>
<form id="formItem" name="formItem">
Customer Name :
<input id="customerName" name="customerName" type="text"
class="form-control form-control-sm">
<br> Customer Email :
<input id="customerEmail" name="customerEmail" type="text"
class="form-control form-control-sm">
<br> Customer Age :
<input id="cusAge" name="cusAge" type="text"
class="form-control form-control-sm">
<br> Password :
<input id="password" name="password" type="text"
class="form-control form-control-sm">
<br> Phone
<input id="phone" name="phone" type="text"
class="form-control form-control-sm">
<br> NIC
<input id="nic" name="nic" type="text"
class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save"
class="btn btn-primary">
<input type="hidden" id="hidItemIDSave"
name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
<%
Customer cusObj = new Customer();
out.print(cusObj.readCustomer());
%>
</div>
</div> </div> </div>

</body>
</html>