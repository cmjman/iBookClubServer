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
import com.shining.ibookclubserver.bean.BookBean;
import com.shining.ibookclubserver.dao.BookDao;
import com.shining.ibookclubserver.dao.Dao;

public class SearchBookAction  extends ActionSupport implements ServletRequestAware,ServletResponseAware{


	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String email;
	private String keyword;

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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void searchBook(){
		
	//	BookDao dao=BookDao.getInstance();
		Dao dao=Dao.getInstance();
		ArrayList<BookBean> list;
		Gson gson_response=new Gson();
		
		list=dao.searchPublicBook(keyword);
		
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
