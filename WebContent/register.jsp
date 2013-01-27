<%@page contentType="text/html; charset=gb2312" language="java" import="java.util.*,java.io.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="button.css">
</head>
<body>
<center>
  <h1>注册新用户</h1>
  <form action="adduser.jsp" method=post>
  <table border="1" bgcolor="#0099CC">
    <tr>
      <td>        邮箱:
        <input type="text" name="email">
      </td>
       <td>        昵称:
        <input type="text" name="nickname">
      </td>
    </tr>
    <tr valign="middle">
      <td>        密码：
        <input type="password" name="password">
      </td>
    </tr>
    <tr>
      <td>
        <input type=submit value=提交 class="button">
      </td>
    </tr>
  </table>
  </form>
</center>
</body>
</html>