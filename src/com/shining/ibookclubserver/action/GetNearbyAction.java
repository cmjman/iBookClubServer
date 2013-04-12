package com.shining.ibookclubserver.action;

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

public class GetNearbyAction  extends ActionSupport implements ServletRequestAware,ServletResponseAware{


	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String email;
	private String latitude;
	private String longitude;

	public void setServletResponse(HttpServletResponse response) {
		
		this.response=response;
        this.response.setCharacterEncoding("UTF-8");
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		
		this.request=request;
	}
	
	public void setEmail(String email){
		
		this.email=email;
	}
	
	public String getEmail(){
		
		return email;
	}
	
	public void setLatitude(String latitude){
		this.latitude=latitude;
	}
	
	public String getLatitude(){
		return latitude;
	}
	
	public void setLongitude(String longitude){
		this.longitude=longitude;
	}
	
	public String getLongitude(){
		return longitude;
	}
	
	public void getNearby(){
		
		System.out.println("getNearby Location:"+latitude+longitude);
		
	//	BookDao dao=BookDao.getInstance();
		Dao dao=Dao.getInstance();
		ArrayList<BookBean> list;
		Gson gson_response=new Gson();
		
		ArrayList<String> isbn=new ArrayList<String>();
		
		isbn=dao.getNearbyBook(email, latitude, longitude);
		
		list=dao.getBookByIsbn(isbn);
		
		try {
			
			PrintWriter out = response.getWriter();
			
			System.out.println("GetNearbyAction:"+gson_response.toJson(list));
			
			out.print(gson_response.toJson(list));
			out.flush();
			out.close();
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
}
