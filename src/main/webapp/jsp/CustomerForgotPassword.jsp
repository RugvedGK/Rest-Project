<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="/customer/forgotpassword" method="post">
		<table>
		<tr>
		<td><input type="email" name="name" placeholder="Email"></td>
		</tr>
		<tr>
		<td><input type="number" name="number" placeholder="Enter Otp"></td>
		</tr>
		<tr>
		<td><button>Submit</button></td>
		</tr>
		</table>	
	</form>
</body>
</html>