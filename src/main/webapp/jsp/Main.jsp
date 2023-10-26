<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
      integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
    <spring:url value="/css/Main.css" var="mainCss" />
    <link href="${mainCss}" rel="stylesheet" />
    <title>Main Home Page</title>
</head>
<body>
<h1 style="color: green">${pos}</h1>
<h1 style="color: red">${neg}</h1>
<div id="main">
      <div class="admin main">
        <a href="/admin"><i class="fa-solid fa-user fa-5x"></i></a><br />
        <i class="name">Admin</i>
      </div>
      <div class="customer main">
        <a href="/customer"><i class="fa-solid fa-user fa-5x"></i></a><br />
        <i class="name">Customer</i>
      </div>
      <div class="hotel main">
        <a href="/hotel"><i class="fa-solid fa-hotel fa-5x"></i></a><br />
        <i class="name">Hotel</i>
      </div>
    </div>
</body>
</html>