<%@ page language="java" contentType="text/html; charset=utf-8"
   %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%
	String email=(String)session.getAttribute("email1"); 

	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/iBookClubDB","root","123456");
	Statement stmt=con.createStatement();
	String sql_getmybook="select isbn from bookowner where id in(select id from userinfo where email='"+email+"');";

	ResultSet rs=stmt.executeQuery(sql_getmybook);
	 if(rs.next())
	 {
		String isbn=rs.getString("isbn");
	//	System.out.println(isbn);
		session.setAttribute("isbn",isbn);
		session.setAttribute("email",email);
	 }
	 

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Book</title>
</head>
<body>

	<h1>发布新书</h1>
	<form action="addbook.jsp">
	<td>        ISBN：
        <input type="text" name="isbn">
     </td>
	
		<input type=submit value=发布 class="button">
	</form>
	
	<h1>我发布的书</h1>
	<c:out value="${isbn}" default="No Book" />
	
</body>
</html>