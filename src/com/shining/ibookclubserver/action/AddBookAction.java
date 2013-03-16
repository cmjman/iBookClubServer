package com.shining.ibookclubserver.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.shining.ibookclubserver.BookBean;
import com.shining.ibookclubserver.dao.BookDao;

public class AddBookAction  extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
	public String isbn;
	public String email;
	public String name;
	public String author;
	public String publisher;
	public BookBean bookbean=new BookBean();
	
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
	
	public void  addBook(){ 
		
	
		email=request.getParameter("email");
		String latitude=request.getParameter("latitude");
		String longitude=request.getParameter("longitude");
		
		
		Gson gson=new Gson();
		bookbean=gson.fromJson(request.getParameter("bookbean"),BookBean.class);
		
		
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
