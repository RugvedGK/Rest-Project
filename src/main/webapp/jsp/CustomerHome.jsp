<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
${neg}${pos}
    <h1>Welcome to Our Restaurant</h1>
    <div>
        <a href="/customer/fetch-items"><button>View Menu</button></a>
        
        <a href="/customer/fetch-order"><button>View Orders</button></a>
     <a href="/logout"><button>Logout</button></a>
    </div>
</body>
</html>