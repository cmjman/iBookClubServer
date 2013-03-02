package com.shining.ibookclubserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONObject;




import com.google.gson.Gson;
import com.shining.ibookclubserver.BookBean;

import com.shining.ibookclubserver.dao.BookDao;

/**
 * Servlet implementation class AddBookServlet
 */
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public String isbn;
	public String email;
	public String name;
	public String author;
	public String publisher;
	public BookBean bookbean=new BookBean();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookServlet() {
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
		email=request.getParameter("email");
		String latitude=request.getParameter("latitude");
		String longitude=request.getParameter("longitude");
		
		
		Gson gson=new Gson();
		bookbean=gson.fromJson(request.getParameter("bookbean"),BookBean.class);
		
		
		BookDao dao=BookDao.getInstance();
		dao.setBookBean(bookbean);
		
		try {
			if(!dao.isBookExist())
				dao.addBook();
			dao.setOwnerInfo(email,latitude,longitude);
			
			
		
		//	ArrayList<BookBean> list=dao.getMyBook(email);
			
		//	Gson gson_response=new Gson();
		
			
		
			JSONObject jsonObj=new JSONObject();
	
			jsonObj.put("ActionResult", true);
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			
			System.out.println("AddBookServlet:True");
			
			out.print(jsonObj);
			out.flush();
			out.close();
			
		
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
	
		
		
		
	}

}
