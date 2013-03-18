package com.shining.ibookclubserver.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.shining.ibookclubserver.dao.BookDao;

public class BorrowBookAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String email;
	private String isbn;

	@Override
	public void setServletResponse(HttpServletResponse response) {
		
		this.response=response;
        this.response.setCharacterEncoding("UTF-8");
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		
		this.request=request;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public void borrowBook(){
		
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
