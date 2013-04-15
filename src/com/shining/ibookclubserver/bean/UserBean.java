package com.shining.ibookclubserver.bean;

public class UserBean {

	  private String email;
      
	  private String password;
	  
	  private String nickname;
	  
	  private String interest;
	  
	  private String sex;
	  
	  private String age;
       
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return sex;
	}

	public void setGender(String gender) {
		this.sex = gender;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

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
