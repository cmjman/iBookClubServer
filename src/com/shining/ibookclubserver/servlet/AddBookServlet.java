package com.shining.ibookclubserver.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		email=request.getParameter("email");
		isbn=new String(request.getParameter("isbn"));
		name=request.getParameter("name");
		author=request.getParameter("author");
		publisher=request.getParameter("publisher");
	
		bookbean.setIsbn(isbn);
		bookbean.setAuthor(author);
		bookbean.setBookname(name);
		bookbean.setPress(publisher);

		bookadder.setBookBean(bookbean);
		try {
			if(!bookadder.isBookExist())
				bookadder.add();
			bookadder.setOwner(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
