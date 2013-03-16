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
import com.shining.ibookclubserver.dao.BookDao;

public class DeleteBookAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String isbn;
	private String email;

	@Override
	public void setServletResponse(HttpServletResponse response) {
		
		this.response=response;
        this.response.setCharacterEncoding("UTF-8");
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {

		this.request=request;
	}
	
	public void deleteBook() throws IOException{
		
	
		email=request.getParameter("email");
		
		isbn=new String(request.getParameter("isbn"));
		
		BookDao dao=BookDao.getInstance();
		dao.deleteBook(email, isbn);
		
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
