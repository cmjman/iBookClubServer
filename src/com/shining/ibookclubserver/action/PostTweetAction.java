package com.shining.ibookclubserver.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.shining.ibookclubserver.Service;
import com.shining.ibookclubserver.bean.BookBean;
import com.shining.ibookclubserver.bean.TimelineBean;


public class PostTweetAction  extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
	private String email;
	private String message;
	private TimelineBean timelinebean=new TimelineBean();
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private Service service;

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
	

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
	
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public void  postTweet(){ 
		

		
	
		//BookDao dao=BookDao.getInstance();

	
	
		
		
		try {
			service.postTweet(email, message);
		
			JSONObject jsonObj=new JSONObject();
	
			jsonObj.put("ActionResult", true);
			PrintWriter out = response.getWriter();
			
			System.out.println("PostTweet:true");
			
			out.print(jsonObj);
			out.flush();
			out.close();
			
		
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}

}
