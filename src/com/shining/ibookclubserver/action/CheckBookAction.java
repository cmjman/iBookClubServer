package com.shining.ibookclubserver.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.shining.ibookclubserver.dao.BookDao;

public class CheckBookAction  extends ActionSupport implements ServletRequestAware,ServletResponseAware{

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

	public void checkBook(){
		
			String	email=request.getParameter("email");
			
			String	isbn=new String(request.getParameter("isbn"));
			
			BookDao dao=BookDao.getInstance();
			Boolean result=dao.checkBook(email, isbn);
			
			JSONObject jsonObj=new JSONObject();
			try {
				jsonObj.put("ActionResult", result);
			
			
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				
				out.print(jsonObj);
				out.flush();
				out.close();
			
				} catch (Exception e) {
					
					e.printStackTrace();
				}
		}
}
