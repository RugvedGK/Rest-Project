<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1 style="color: green">${pos}</h1>
<h1 style="color: red">${neg}</h1>
<h1>Customer Page</h1>
<form action="/customer/login" method="post">
<fieldset>
<table>
<tr>
<th>Email: </th>
<td><input type="email" name="email"></td>

</tr>
<tr>
<th>Password: </th>
<td><input type="password" name="password"></td>
</tr>
<tr>
<th><a href=""><button>Login</button></a></th>
<td><a href=""><button>Cancle</button></a></td>
</tr>
<tr>
<th><button><a href="/customer/signup">Sign up</a></button></th>
</tr>
</table>
</fieldset>
</form>
</body>
</html>