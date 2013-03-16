package com.shining.ibookclubserver.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.shining.ibookclubserver.UserBean;
import com.shining.ibookclubserver.dao.BookDao;

public class RegisterAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	
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
	
	
	public void register(){
		
		String email =request.getParameter("email");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		UserBean userBean=new UserBean();
		userBean.setEmail(email);
		userBean.setPassWord(password);
		userBean.setNickName(nickname);
		BookDao dao=BookDao.getInstance();
		
		JSONObject jsonObj= new JSONObject();
		try{
		  dao.setUserBean(userBean);
		
		 if(dao.regist())
		  	jsonObj.put("ActionResult", true);
		 else
		  	jsonObj.put("ActionResult", false);
		  PrintWriter out = response.getWriter();
		  out.print(jsonObj.toString());
		  out.flush();
		  out.close();
		}
		  catch(Exception e){
			  e.printStackTrace();
		}

	}
}
