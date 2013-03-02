package com.shining.ibookclubserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;


import com.shining.ibookclubserver.dao.BookDao;




/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	 doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		 String email1=new String(request.getParameter("email"));
		  String password1=new String(request.getParameter("password"));
		  
		 System.out.println("LoginServlet:"+email1+password1);
		  try {
	
			  BookDao dao=BookDao.getInstance();	  
			  
			  String nickname=dao.checkPassword(email1,password1);
			  JSONObject jsonObj= new JSONObject();
			  
			  if(nickname!="-1"){
				
				request.getSession(true).setAttribute("email" , email1);
			
				jsonObj.put("email" , email1);
				jsonObj.put("nickname", nickname);
				jsonObj.put("ActionResult", true);
				
				
				System.out.println("登陆成功");
				
			  }
			  else{
				  jsonObj.put("ActionResult", false);
				  
				  System.out.println("登陆失败");
			  }
				
			//	String result="登陆成功！";
				PrintWriter out = response.getWriter();
				out.print(jsonObj.toString());
				out.flush();
				out.close();
			  	
			  
		  	
			 
		  
		 }
		  catch (Exception e) {
				
				e.printStackTrace();
		  }
		  
	}

}
