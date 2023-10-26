<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="/hotel/edit-item" method="post" enctype="multipart/form-data">
<input name="id" value="${items.getId()}" hidden="hidden">
Name: <input type="text" name="name" value="${items.getName()}"><br>
Desciption: <input type="text" name="description" value="${items.getDescription()}"><br>
Price <input type="number" name="price" value="${items.getPrice()}"><br>
Image:<img style='display:block; width:100px;height:100px;' id='base64image'
    				 src='data:image/jpeg;base64,${Base64.encodeBase64String(items.getPicture())}'/>
    				 Image: <input type="file" name="image" value="${items.getPicture()}"><br>
<br>
Stock: <input type="number" name="stock" value="${items.getStock()}"><br>
<a href=""><button>update</button></a>
<a href=""><button>Cancle</button></a>

</form>
</body>
</html>