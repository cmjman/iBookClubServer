package com.shining.ibookclubserver;

import java.sql.*;
import com.mysql.jdbc.Driver;

public class UserRegister {

	 private UserBean userBean;
     private Connection con;
 //    private static int IDcount=0;
   
     public UserRegister(){
          
    	 String url="jdbc:mysql://localhost:3306/"+"iBookClubDB"+"?user="+"root"+"&password="+"123456";
         try
         {
             Class.forName("com.mysql.jdbc.Driver").newInstance();
                     con = DriverManager.getConnection(url);
             }
             catch(Exception e)
             {
                     e.printStackTrace();
             }
     }

     public void setUserBean(UserBean userBean){
             
    	 this.userBean=userBean;
     }
 
     public void regist() throws Exception{
           
    	 String reg="insert into userinfo(email,password,nickname)  values(?,?,?)";
         try{
        	 PreparedStatement pstmt=con.prepareStatement(reg);
             pstmt.setString(1,userBean.getEmail());
             pstmt.setString(2,userBean.getPassWord());
             pstmt.setString(3, userBean.getNickName());
             pstmt.executeUpdate();
         }
         catch(Exception e){
              
        	 e.printStackTrace();
         }      

     }
}
