package com.shining.ibookclubserver.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.shining.ibookclubserver.BookBean;
import com.shining.ibookclubserver.dao.BookDao;

public class GetBookAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String email;

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

	public void getBook(){
		
	//	String email=request.getParameter("email");
		
		BookDao dao=BookDao.getInstance();
		ArrayList<BookBean> list;
		Gson gson_response=new Gson();
		if(email==null){
			
			list=dao.getPublicBook();
			
			System.out.println("GetPublicBook返回："+gson_response.toJson(list));
			
		}else{
			
			
			
			 list=dao.getMyBook(email);
			 System.out.println("GetMyBook返回："+gson_response.toJson(list));
		
		}
		
	
	
		
	
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(gson_response.toJson(list));
			out.flush();
			out.close();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	
		
	}
}
