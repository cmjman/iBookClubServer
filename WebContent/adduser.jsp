<%@ page  contentType="text/html;charset=gb2312" pageEncoding="gb2312"
 import="com.shining.ibookclubserver.*" %>
<jsp:useBean id="userBean" class="com.shining.ibookclubserver.UserBean" scope="request">
<jsp:setProperty name="userBean" property="*"/>
</jsp:useBean>
<jsp:useBean id="regist" class="com.shining.ibookclubserver.UserRegister" scope="request"/>
<html>
<head>
<title> 用户信息注册页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body>
<%
String userid =request.getParameter("userid");
String password = request.getParameter("password");
userBean.setUserId(userid);
userBean.setPassword(password);
System.out.println(userid+password);
%>
<% try{
  regist.setUserBean(userBean);
  out.println(userid);
  regist.regist();
  out.println("注册成功");}
  catch(Exception e){
  out.println(e.getMessage());
  }
%>
<br>
<a href="login.jsp">返回</a>
</body>
</html>