<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="/css/signup1.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
<div class="container">
        <div class="image">
          <img src="../image/pexels-marta-dzedyshko-7441761.jpg" alt="" />
        </div>
        <div class="main">
          <h1>Customer Signup Page</h1>
          <div class="main-text">
            <mvc:form action="/customer/signup" method="post" modelAttribute="customer">
              <table>
                <tr>
                    <td><mvc:input path="name" placeholder="Username" /></td>
                    <td><mvc:errors path="name" /></td>
                </tr>
                <tr>
                    <td><mvc:input path="email"  placeholder="Email"/></td>
                    <td><mvc:errors path="email" /></td>
                  </tr>
                <tr>
                    <td><mvc:input path="password"  placeholder="Password"/></td>
                    <td><mvc:errors path="password" /></td>
                </tr>
                <tr>
                    <td><mvc:input path="mobile" placeholder="Mobile"/></td>
                    <td><mvc:errors path="mobile" /></td>
                  </tr>
                  <div >
                <tr class="buttons">
                    <td><button>Submit</button></td>
                </tr>
                <tr class="buttons1">
                    <td><button>Cancle</button></td>
                </tr>
            </div>
              </table>
            </mvc:form>
           <h5 style="color: green">${pos}</h5>
            <h5 style="color: red">${neg}</h5>
</body>
</html>