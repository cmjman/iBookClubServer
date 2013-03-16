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

public class SearchBookAction  extends ActionSupport implements ServletRequestAware,ServletResponseAware{


	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public void setServletResponse(HttpServletResponse response) {
		
		this.response=response;
        this.response.setCharacterEncoding("UTF-8");
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		
		this.request=request;
	}
	
	public void searchBook(){
		String email=request.getParameter("email");
		String keyword=request.getParameter("keyword");
		
		BookDao dao=BookDao.getInstance();
		ArrayList<BookBean> list;
		Gson gson_response=new Gson();
		
		list=dao.searchPublicBook(keyword);
	
	
		
	
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
