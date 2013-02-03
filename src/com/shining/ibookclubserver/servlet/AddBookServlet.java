package com.shining.ibookclubserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;



import com.google.gson.Gson;
import com.shining.ibookclubserver.BookAdder;
import com.shining.ibookclubserver.BookBean;

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
	public BookAdder bookadder=new BookAdder();
       
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		email=request.getParameter("email");
		
//		isbn=new String(request.getParameter("isbn"));
	//	name=request.getParameter("name");
//		author=request.getParameter("author");
//		publisher=request.getParameter("publisher");
	
	//	bookbean.setIsbn(isbn);
	//	bookbean.setAuthor(author);
	//	bookbean.setBookname(name);
	//	bookbean.setPublisher(publisher);
//		JSONObject jsonBean=JSONObject.fromObject(request.getParameter("bookbean"));
	//	bookbean= (BookBean)JSONSerializer.toJava(jsonBean);
		Gson gson=new Gson();
		bookbean=gson.fromJson(request.getParameter("bookbean"),BookBean.class);

		bookadder.setBookBean(bookbean);
		try {
			if(!bookadder.isBookExist())
				bookadder.add();
			bookadder.setOwner(email);
		
			ArrayList<BookBean> list=bookadder.getMyBook(email);
			//JSONArray jsonArray=new JSONArray();
			Gson gson_response=new Gson();
		
			
		
			
		//	for(BookBean book:list){
			
			//	JSON json=JSONSerializer.toJSON(book);
		
			//	jsonArray.add(json);
				
		
		//	}
			
		//	jsonObj.append("count", list.size());
		//	jsonObj.append("ActionResult", true);
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
		//	System.out.println(jsonArray);
			out.print(gson_response.toJson(list));
			out.flush();
			out.close();
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
		
	}

}
