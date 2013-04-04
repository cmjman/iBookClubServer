package com.shining.ibookclubserver.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

public class GetRecommendAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String email;
	
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
	
	public void getRecommend(){
		
		//TODO 具体推荐算法待加入
	}
}
