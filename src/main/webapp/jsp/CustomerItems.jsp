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
				<th>Description</th>
				<th>Stock</th>
				<th>Remove</th>
				<th>Quantity</th>
				<th>Add</th>
			</tr>
			<c:forEach var="items" items="${items}">
				<tr>
					<td>${items.getName()}</td>
					<td><img class="product-image" alt="Product Image"
						src="data:image/jpeg;base64,${Base64.encodeBase64String(items.getPicture())}">
					</td>
					<td>${items.getPrice()}</td>
					<td>${items.getDescription()}</td>
					<td>${items.getStock()}</td>
					<td><a href="/customer/cart-remove/${items.getId()}" style="text-decoration: none">-</a></td>
					<td>
					<c:if test="${cartItems==null}">
						0
						</c:if>
						<c:if test="${cartItems!=null}">
						<c:set var="flag" value="true"></c:set>
						<c:forEach var="food" items="${cartItems}">
						<c:if test="${food.getName().equals(items.getName())}">
						${food.getQuantity()}
						<c:set var="flag" value="false"></c:set>
						</c:if>
						</c:forEach>
						<c:if test="${flag==true}">
						0
						</c:if>
						</c:if>
					</td>
					<td><a href="/customer/cart-add/${items.getId()}" style="text-decoration: none">+</a></td>
				</tr>
			</c:forEach>
		</table>
	</section>
	<a href="/customer/home" class="button">
		<button class="back-button">Back</button>
		<a href="/customer/view-cart"><button class="">View Cart</button></a>
	</a>
</body>
</html>