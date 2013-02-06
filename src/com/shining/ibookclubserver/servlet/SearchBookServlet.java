package com.shining.ibookclubserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.shining.ibookclubserver.BookBean;
import com.shining.ibookclubserver.dao.BookDao;

/**
 * Servlet implementation class SearchBookServlet
 */
public class SearchBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBookServlet() {
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
		String email=request.getParameter("email");
		String keyword=request.getParameter("keyword");
		
		BookDao dao=BookDao.getInstance();
		ArrayList<BookBean> list;
		Gson gson_response=new Gson();
		
		list=dao.searchPublicBook(keyword);
	
	
		
	
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print(gson_response.toJson(list));
		out.flush();
		out.close();
		
		
	}

}
