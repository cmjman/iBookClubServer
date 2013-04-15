package com.shining.ibookclubserver.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.shining.ibookclubserver.dao.BookDao;
import com.shining.ibookclubserver.dao.Dao;

public class RecordBookAction  extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String email;
	private String isbn;
	private String nickname;

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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void recordBook(){
		
	//	BookDao dao=BookDao.getInstance();
	

		BookDao dao=(BookDao) BookDao.getInstance();
		
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
