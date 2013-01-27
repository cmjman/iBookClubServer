package com.shining.ibookclubserver;

public class UserBean {

	  private String email;
      
	  private String password;
	  
	  private String nickname;
	  
	//  private static int IDcount=0;
       
	  public void setEmail(String email){
          
		  this.email=email;
      }
       
	  public void setPassWord(String password){
               
		  this.password=password;
      }
	  
	  public void setNickName(String nickname){
		  this.nickname=nickname;
	  }
       
	  public String  getEmail(){
               
		  return this.email;
      }
       
	  public String getPassWord(){
                
		  return this.password;
	  }
	  
	  public String getNickName(){
		  
		  return this.nickname;
	  }
}
