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
String email =request.getParameter("email");
String password = request.getParameter("password");
String nickname = request.getParameter("nickname");
userBean.setEmail(email);
userBean.setPassWord(password);
userBean.setNickName(nickname);

%>
<% try{
  regist.setUserBean(userBean);
  out.println(email);
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