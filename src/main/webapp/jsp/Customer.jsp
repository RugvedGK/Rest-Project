<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="/css/signup.css" rel="stylesheet" type="text/css">
    <title>Insert title here</title>
  </head>
  <body>
    <div class="container">
      <div class="image">
        <img src="../image/pexels-valeria-boltneva-842571.jpg" alt="" />
      </div>
      <div class="main">
        <h3>Customer Login Page</h3>
        <div class="main-text">
          <form action="/customer/login" method="post">
            <table>
              <tr>
                <!-- <th>Email:</th> -->
                <td><input type="email" name="email" placeholder="Email" /></td>
              </tr>
              <tr>
                <!-- <th>Password:</th> -->
                <td>
                  <input
                    type="password"
                    name="password"
                    placeholder="Password"
                  />
                </td>
              </tr>
              <tr>
                <td>
              <div class="buttons">
                <a href=""><button>Login</button></a></td>
              </div>
              <tr>
                <td>
                  <div class="forgot">
                    <a href="/customer/forgotpassword">Forgot password?</a>
                  </div>
                </td>
              </tr>
            </tr>
            <tr>
            <td><h5 style="color: green" >${pos}</h5>
				<h5 style="color: red">${neg}</h5>
				</td>
            </table>
          </form>
        </div>
        <div class="regi">
          Dont have an account? <a href="/customer/signup">Sign up</a>
        </div>
      </div>
    </div>
  </body>
</html>
