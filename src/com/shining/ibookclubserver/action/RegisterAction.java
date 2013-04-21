package com.shining.ibookclubserver.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.shining.ibookclubserver.Service;
import com.shining.ibookclubserver.bean.UserBean;

;


public class RegisterAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String email;
	private String password;
	private String nickname;
	private String interest;
	private String gender;
	private String age;
	
	private Service service;

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	public void register(){
		
		UserBean userBean=new UserBean();
		userBean.setEmail(email);
		userBean.setPassword(password);
		userBean.setNickname(nickname);
		userBean.setInterest(interest);
		userBean.setSex(gender);
		userBean.setAge(Integer.parseInt(age));
	//	BookDao dao=BookDao.getInstance();
	//	UserDao dao=(UserDao)UserDao.getInstance();
		
		JSONObject jsonObj= new JSONObject();
		try{
		  
		
		
		 if(service.regist(userBean))
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
