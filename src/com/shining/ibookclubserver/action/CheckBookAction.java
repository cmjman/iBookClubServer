package com.shining.ibookclubserver.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.shining.ibookclubserver.dao.BookDao;
import com.shining.ibookclubserver.dao.Dao;

public class CheckBookAction  extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	private String email;
	private String isbn;

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

	public void checkBook(){
			
		//	BookDao dao=BookDao.getInstance();

		BookDao dao=(BookDao) BookDao.getInstance();
			Boolean result=dao.checkBook(email, isbn);
			
			JSONObject jsonObj=new JSONObject();
			try {
				jsonObj.put("ActionResult", result);
			
			
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				
				out.print(jsonObj);
				out.flush();
				out.close();
			
				} catch (Exception e) {
					
					e.printStackTrace();
				}
		}
}
