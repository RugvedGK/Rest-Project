<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="/hotel/add-item" method="post" enctype="multipart/form-data">
Name: <input type="text" name="name"><br>
Desciption: <input type="text" name="description"><br>
Price <input type="number" name="price"><br>
Image: <input type="file" name="image"><br>
Stock: <input type="number" name="stock"><br>
<a href=""><button>Submit</button></a>
<a hrev=""><button>Cancle</button></a>

</form>
</body>
</html>