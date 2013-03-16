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

public class LoginAction  extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	/**
	 * 
	 */
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
	
	public void  login(){  
		
		String email=new String(request.getParameter("email"));
		String password=new String(request.getParameter("password"));
	
		System.out.println("LoginAction:"+email+password);
		
		try {
			
			BookDao dao=BookDao.getInstance();	  
			String nickname=dao.checkPassword(email,password);
			JSONObject jsonObj= new JSONObject();
			  
			if(nickname!="-1"){
				
				request.getSession(true).setAttribute("email" , email);
				jsonObj.put("email" , email);
				jsonObj.put("nickname", nickname);
				jsonObj.put("ActionResult", true);
				System.out.println("登陆成功");
				
			}
			else{
				 
				jsonObj.put("ActionResult", false);
				System.out.println("登陆失败");
			}

			PrintWriter out = response.getWriter();
			out.print(jsonObj.toString());
			out.flush();
			out.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}     
}
