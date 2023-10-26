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
<h3>${pos}</h3>
	<h2>${neg}</h2>
	<section class="product-list">
		<table border="1">
			<tr>
				<th>Item Name</th>
				<th>Picture</th>
				<th>Price</th>
				<th>Stock</th>
				<th>Description</th>
				<th>Status</th>
				<th>Change</th>
			</tr>
			<c:forEach var="items" items="${items}">
				<tr>
					<td>${items.getName()}</td>
					<td><img class="product-image" alt="Product Image"
						src="data:image/jpeg;base64,${Base64.encodeBase64String(items.getPicture())}">
					</td>
					<td>${items.getPrice()}</td>
					<td>${items.getStock()}</td>
					<td>${items.getDescription()}</td>
					<td>${items.isStatus()}</td>
					<td><a href="/admin/product-status/${items.getId()}"><button>Change</button></a></td>
				</tr>
			</c:forEach>
		</table>
	</section>
	<a href="/admin/home" class="button">
		<button class="back-button">Back</button>
	</a>
</body>
</html>