package com.shining.ibookclubserver.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionSupport;
import com.shining.ibookclubserver.Service;
import com.shining.ibookclubserver.bean.BookBean;



public class AddBookAction  extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
	private String email;
	private String latitude;
	private String longitude;
	private String rating;
	private BookBean bookbean=new BookBean();
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private Service service;

	
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	@Override
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
	
	
	public void  addBook(){ 
		
		System.out.println(request.getParameter("bookbean_gson"));
		
		Gson gson=new Gson();
		bookbean=gson.fromJson(request.getParameter("bookbean_gson"),BookBean.class);
		
		System.out.println(bookbean.getIsbn());
		

	
		
		
		try {
			if(!service.isBookExist(bookbean))
				service.addBook(bookbean);
			service.setOwnerInfo(email,latitude,longitude,rating,bookbean);
		
			JSONObject jsonObj=new JSONObject();
	
			jsonObj.put("ActionResult", true);
			PrintWriter out = response.getWriter();
			
			System.out.println("AddBookServlet:True");
			
			out.print(jsonObj);
			out.flush();
			out.close();
			
		
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}
	
}
