<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
	<tr>
	<th>Picture</th>
	<th>Name</th>
	<th>Description</th>
	<th>Stocks</th>
	<th>Price</th>
	<th>Edit</th>
	<th>Delete</th>
	</tr>
	<c:forEach var="items" items="${items}">
	<tr>
	<th> <img style='display:block; width:100px;height:100px;' id='base64image'
    				 src='data:image/jpeg;base64,${Base64.encodeBase64String(items.getPicture())}'/></th>
	<th>${items.getName()} </th>
	<th>${items.getDescription()}</th>
	<th>${items.getStock()} </th>
	<th>${items.getPrice()} </th>
	<th><a href="/hotel/edit-products/${items.getId()}"><button>Edit</button></a></th>
	<th><a href="/hotel/delete-products/${items.getId()}"><button>Delete</button></a></th>
	</tr>
	</c:forEach>
	</table>
	<br>
	<a href="/hotel/home"><button>Back</button></a>
</body>
</html>