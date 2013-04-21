package com.shining.ibookclubserver.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.shining.ibookclubserver.Service;
import com.shining.ibookclubserver.ServiceImpl;



public class LoginAction  extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String email;
	private String password;
	
	private Service service;

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void  login(){  
		
		try{
		
			
			
			PrintWriter out = response.getWriter();
			out.print(service.login(email, password));
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		/*
		
	
		System.out.println("LoginAction:"+email+password);
		
		try {
			
			//BookDao dao=BookDao.getInstance();	

			UserDao dao=(UserDao)UserDao.getInstance();
			String nickname=dao.checkPassword(email,password);
			Map<String,String> json=new HashMap<String,String>();  
			if(nickname!="-1"){
				
				request.getSession(true).setAttribute("email" , email);
				
				
				
				json.put("email" , email);
				json.put("nickname", nickname);
				json.put("ActionResult", "true");
				System.out.println("登陆成功");
				
			}
			else{
				 
				json.put("ActionResult", "false");
				System.out.println("登陆失败");
			}
			
			//byte[] jsonBytes = json.toString().getBytes("utf-8");  

			PrintWriter out = response.getWriter();
			out.print(json.toString());
			out.flush();
			out.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}	*/
		
		
	}     
}
