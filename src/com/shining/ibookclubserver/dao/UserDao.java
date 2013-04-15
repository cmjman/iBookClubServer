package com.shining.ibookclubserver.dao;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.shining.ibookclubserver.bean.UserBean;

public class UserDao extends Dao{

	private static UserDao userDao=new UserDao();
	
	private UserBean userBean;
	
	public static UserDao getInstance() {
		
		return userDao;
	}
	
	public UserDao(){
		super();
	}
	
	
	public void setUserBean(UserBean userBean){
         
    	 this.userBean=userBean;
    }
	
	public Boolean regist(){
		 
		 SqlSession session=  factory.openSession();
		 Boolean result=false;
		 
		 try{
			 
			 session.insert("com.shining.ibookclubserver.dao.UserMapper.regist");
			 session.commit();
			 result=true;
			 
		 }catch(Exception e){
		  		
		  		e.printStackTrace();
		  	}finally {
		  		
		  	session.close();
		}
		  	
		return result; 
	 }
	 
	public String checkPassword(String email,String pw_check){
	 
	 
	  	SqlSession session=  factory.openSession();
		String nickname=null;
	  	
	  	try{
		  
	  		UserMapper userMapper=getUserinfoByEmail(email);
		  	
	  		if(userMapper.getPassword().equals(pw_check)){
	
		  		nickname=userMapper.getNickname();
		  	}else{
		  		
		  		nickname="-1";
		  	}
	  	}catch(Exception e){
	  		
	  		e.printStackTrace();
	  	}finally {
	  		
	  		session.close();
	  	}
	  	
		 return nickname;
	}
	
	public UserMapper getUserinfoByEmail(String email){
		
		SqlSession session=factory.openSession();
		
		UserMapper userMapper=new UserMapper();
		
		try{
			
			userMapper=session.selectOne("com.shining.ibookclubserver.dao.UserMapper.getUserinfo", email);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return userMapper;
	}
}
