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
import com.shining.ibookclubserver.BookBean;
import com.shining.ibookclubserver.dao.BookDao;

public class AddBookAction  extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
	//public String isbn;
	private String email;
	private String latitude;
	private String longitude;
//	private String bookbean_gson;
//	public String name;
//	public String author;
//	public String publisher;
	private BookBean bookbean=new BookBean();
	
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;

	
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
		
		BookDao dao=BookDao.getInstance();
	
		dao.setBookBean(bookbean);
		
		
		try {
			if(!dao.isBookExist())
				dao.addBook();
			dao.setOwnerInfo(email,latitude,longitude);
		
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
