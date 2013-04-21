package com.shining.ibookclubserver.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.shining.ibookclubserver.Service;



public class DeleteBookAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String isbn;
	private String email;

	private Service service;
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
	
	
	
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public void deleteBook() throws IOException{
		
		
	//	BookDao dao=BookDao.getInstance();

	
		service.deleteBook(email, isbn);
		
		JSONObject jsonObject=new JSONObject();
			
		jsonObject.put("Result","Success");
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		System.out.println("DeleteBook:"+jsonObject.toString());
		
		out.print(jsonObject.toString());
		out.flush();
		out.close();
		
	}
}
