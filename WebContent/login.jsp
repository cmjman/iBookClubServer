<%@ page contentType="text/html; charset=utf-8" %>
<html>
	<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
	<link rel=stylesheet href="button.css"/>
	<link rel=stylesheet href="layout.css"/>
	</head>
	<body>
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