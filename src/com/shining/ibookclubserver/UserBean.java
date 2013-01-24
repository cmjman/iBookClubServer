package com.shining.ibookclubserver;

public class UserBean {

	  private String userid;
      
	  private String password;
       
	  public void setUserId(String userid){
               
		  this.userid=userid;
      }
       
	  public void setPassword(String password){
               
		  this.password=password;
      }
       
	  public String  getUserId(){
               
		  return this.userid;
      }
       
	  public String getPassword(){
                
		  return this.password;
	  }
}
