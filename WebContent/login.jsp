<%@ page contentType="text/html; charset=utf-8" %>
<html>
	<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="CSS/bootstrap.min.css" rel="stylesheet" media="screen"> 
	<link rel=stylesheet href="CSS/button.css"/>
	<link rel=stylesheet href="CSS/layout.css"/>
	</head>
	<body>
		<script src="JS/bootstrap.min.js"></script>
		<script src="JS/jquery-1.9.1.min.js"></script>
		<form method="post" action="checklogin.jsp">
		<table>
		<tr>
			<td>输入用户名：</td>
			<td><input type=text name=email ></td>
		</tr>
		<tr>
			<td>输入密码：</td>
			<td><input type=password name=password></td>
		</tr>
		<tr>
			<td><input type=submit value=确认 class="button">
			</td>
		</tr>
		</table>
		</form>
		<form action="register.jsp">
			<input type=submit value=注册 class="button">
		</form>
	</body>
</html>