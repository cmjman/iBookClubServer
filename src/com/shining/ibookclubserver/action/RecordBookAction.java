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

public class RecordBookAction  extends ActionSupport implements ServletRequestAware,ServletResponseAware{

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
	
	public void recordBook(){
		
		String	email=new String(request.getParameter("email"));
		String 	isbn=new String(request.getParameter("isbn"));	
		String	nickname=new String(request.getParameter("nickname"));
		
		BookDao dao=BookDao.getInstance();
	
		
		try {
			
			Boolean result=dao.recordBook(email, nickname,isbn);
			
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			  
			JSONObject jsonObj= new JSONObject();
			jsonObj.put("ActionResult", result);
			out.print(jsonObj.toString());
			out.flush();
			out.close();
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
