<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
   import="com.shining.ibookclubserver.*"  
    %>
<jsp:useBean id="bookBean" class="com.shining.ibookclubserver.BookBean" scope="request">
<jsp:setProperty name="bookBean" property="*"/>
</jsp:useBean>    
<jsp:useBean id="add" class="com.shining.ibookclubserver.BookAdder" scope="request"/>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AddBook</title>
</head>
<body>
	<%
		String email=(String)session.getAttribute("email"); 
		String isbn =request.getParameter("isbn");
		SearchBookThread search=new SearchBookThread(isbn);
		search.run();
		bookBean=search.getBean();
	
	//	System.out.println(bookBean.getIsbn());

	%>
	<% try{
  		add.setBookBean(bookBean);
  		if(!add.isBookExist())
  			add.add();
  		add.setOwner(email);
  		out.println("发布成功");}
  			catch(Exception e){
  		out.println(e.getMessage());
  	}
	%>
</body>
</html>