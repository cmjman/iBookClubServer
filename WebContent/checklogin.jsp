<%@page import="java.sql.*" contentType="text/html;charset=GB2312" %>
<%@page import="java.util.*"%>
<%
  String email1=new String(request.getParameter("email"));
  String password1=new String(request.getParameter("password"));
 Class.forName("com.mysql.jdbc.Driver");
 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/iBookClubDB","root","123456");
 Statement stmt=con.createStatement();
 String sql="select * from  userinfo where email='"+email1+"';";
 ResultSet rs=stmt.executeQuery(sql);
  if(rs.next())
  {String password=new String(rs.getString("password"));
  if(password.equals(password1))
  {session.setAttribute("email1",email1);
  response.sendRedirect("success.jsp");
  }
  else
{response.sendRedirect("login.jsp");
}
}
else
{response.sendRedirect("login.jsp");
}
%>