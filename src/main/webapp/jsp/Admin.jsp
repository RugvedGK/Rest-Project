<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1 style="color: green">${pass}</h1>
	<h1 style="color: red">${fail}</h1>
	<form action="/admin/login" method="post">
		Email: <input type="email" name="email" required/><br />
		<br /> Password: <input type="password" name="password" required/><br />
		<br /> <a href=""><button>Login</button></a> <a href=""><button
				type="reset">Cancel</button></a>
	</form>
	<br />
	<a href="/"><button>Back</button></a>
</body>	
</html>