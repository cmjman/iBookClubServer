package com.shining.ibookclubserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.shining.ibookclubserver.BookAdder;
import com.shining.ibookclubserver.BookBean;
import com.shining.ibookclubserver.BookInfoReference;

/**
 * Servlet implementation class PersonalBookServlet
 */
public class GetBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String email=request.getParameter("email");
		
		BookInfoReference bookInfoReference=new BookInfoReference();
		ArrayList<BookBean> list;
		Gson gson_response=new Gson();
		if(email==null){
			
			list=bookInfoReference.getPublicBook();
			
			System.out.println("GetPublicBook返回："+gson_response.toJson(list));
			
		}else{
			
			
			
			 list=bookInfoReference.getMyBook(email);
			 System.out.println("GetMyBook返回："+gson_response.toJson(list));
		
		}
		
	
	
		
	
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print(gson_response.toJson(list));
		out.flush();
		out.close();
		
	
	
	}

}
