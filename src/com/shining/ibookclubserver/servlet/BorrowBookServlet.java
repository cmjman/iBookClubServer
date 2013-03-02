package com.shining.ibookclubserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import com.google.gson.Gson;
import com.shining.ibookclubserver.dao.BookDao;

/**
 * Servlet implementation class BorrowBookServlet
 */
public class BorrowBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowBookServlet() {
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
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		String	email=new String(request.getParameter("email"));
			
		String	isbn=new String(request.getParameter("isbn"));
		
		BookDao dao=BookDao.getInstance();
		Hashtable<Integer,String> ownerList=dao.borrowBook(email, isbn);
		
		Gson gson_response=new Gson();
		try {
			
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			
			System.out.println("BorrowBookServlet:"+gson_response.toJson(ownerList));
			
			out.print(gson_response.toJson(ownerList));
			out.flush();
			out.close();
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}

}
