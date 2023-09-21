<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1 style="color: green">${pos}</h1>
<h1 style="color: red">${neg}</h1>
<mvc:form action="/customer/signup" method="post" modelAttribute="customer">
 <table>
 <tr>
 <th>Name: </th>
 <td><mvc:input path="name"/></td>
 <td><mvc:errors path="name"/></td>
 </tr>
 <tr>
 <th>Email: </th>
 <td><mvc:input path="email"/></td>
 <td><mvc:errors path="email"/></td>
 </tr>
 <tr>
 <th>Password: </th>
 <td><mvc:input path="password"/></td>
 <td><mvc:errors path="password"/></td>
 </tr>
 <tr>
 <th>Mobile: </th>
 <td><mvc:input path="mobile"/></td>
 <td><mvc:errors path="mobile"/></td>
 </tr>
 <tr>
 <th><button>Submit</button></th>
 <th><button>Reset</button></th>
 </tr>
 </table>
</mvc:form>
</body>
</html>