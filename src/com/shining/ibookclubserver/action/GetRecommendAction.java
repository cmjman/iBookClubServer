package com.shining.ibookclubserver.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.shining.ibookclubserver.Service;
import com.shining.ibookclubserver.bean.BookBean;


public class GetRecommendAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
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
	
	
	
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public void getRecommend(){
		
		
		
	
	
		ArrayList<BookBean> list;
		Gson gson_response=new Gson();
		
		Map<String,String> json=new HashMap<String,String>();  
		
		list=service.getRecommendBook(email);
	
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(gson_response.toJson(list));
			out.flush();
			out.close();
			System.out.println("GetRecommendBookAction:"+gson_response.toJson(list));
		
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		
	}
}
