package com.shining.ibookclubserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.shining.ibookclubserver.BookBean;
import com.shining.ibookclubserver.dao.BookDao;

/**
 * Servlet implementation class GetNearbyServlet
 */
public class GetNearbyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetNearbyServlet() {
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
		String	email=new String(request.getParameter("email"));
			
		String	latitude=new String(request.getParameter("latitude"));
		
		String 	longitude =new String(request.getParameter("longitude"));
		
		BookDao dao=BookDao.getInstance();
		ArrayList<BookBean> list;
		Gson gson_response=new Gson();
		
		ArrayList<String> isbn=new ArrayList<String>();
		
		isbn=dao.getNearbyBook(email, latitude, longitude);
		
		list=dao.getBookByIsbn(isbn);
		
		try {
			
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			
			System.out.println("GetNearbyServlet:"+gson_response.toJson(list));
			
			out.print(gson_response.toJson(list));
			out.flush();
			out.close();
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

}
