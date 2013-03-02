package com.shining.ibookclubserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;



import com.shining.ibookclubserver.dao.BookDao;

/**
 * Servlet implementation class CheckBookServlet
 */
public class CheckBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckBookServlet() {
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
	String	email=request.getParameter("email");
		
	String	isbn=new String(request.getParameter("isbn"));
		
		BookDao dao=BookDao.getInstance();
		Boolean result=dao.checkBook(email, isbn);
		
		JSONObject jsonObj=new JSONObject();
		try {
			jsonObj.put("ActionResult", result);
		
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print(jsonObj);
		out.flush();
		out.close();
		
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
	}

}
