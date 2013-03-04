package com.shining.ibookclubserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.shining.ibookclubserver.dao.BookDao;

/**
 * Servlet implementation class RecordBookServlet
 */
public class RecordBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordBookServlet() {
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
		String 	isbn=new String(request.getParameter("isbn"));	
		String	nickname=new String(request.getParameter("nickname"));
		
		BookDao dao=BookDao.getInstance();
	
		
		try {
			
			Boolean result=dao.recordBook(email, nickname,isbn);
			
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			  
			JSONObject jsonObj= new JSONObject();
			jsonObj.put("ActionResult", result);
			out.print(jsonObj.toString());
			out.flush();
			out.close();
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
