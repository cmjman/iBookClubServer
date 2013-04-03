package com.shining.ibookclubserver.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.shining.ibookclubserver.bean.UserBean;
import com.shining.ibookclubserver.dao.BookDao;

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
		userBean.setPassWord(password);
		userBean.setNickName(nickname);
		userBean.setInterest(interest);
		userBean.setGender(gender);
		userBean.setAge(age);
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
