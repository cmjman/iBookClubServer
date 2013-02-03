package com.shining.ibookclubserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;



public class BookAdder {
	
	private BookBean bookBean;
	
	 private Connection con;
	 
	 public BookAdder(){
         
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

     public void setBookBean(BookBean bookBean){
             
    	 this.bookBean=bookBean;
     }
     
     public boolean isBookExist(){
    	 
    	 String sql="select isbn from bookinfo where isbn='"+bookBean.getIsbn()+"';";
    	 
    	 try{
    		 Statement stmt=con.createStatement();
    		 ResultSet rs=stmt.executeQuery(sql);
    		 if(rs.next())
    			 return true;
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }
    	 return false;
     }
 
     public void add() throws Exception{
           
    	 String reg="insert into bookinfo(isbn,name,press,author)  values(?,?,?,?)";
         try{
        	 PreparedStatement pstmt=con.prepareStatement(reg);
             pstmt.setString(1,bookBean.getIsbn());
             pstmt.setString(2,bookBean.getBookname());
             pstmt.setString(3, bookBean.getPress());
             pstmt.setString(4, bookBean.getAuthor());
             pstmt.executeUpdate();
         }
         catch(Exception e){
              
        	 e.printStackTrace();
         }      

     }
     
     public int findID(String email){
    	 
    	 String sql="select id from userinfo where email='"+email+"';";
    	 try{
    		 Statement stmt=con.createStatement();
    		 ResultSet rs=stmt.executeQuery(sql);
    		 if(rs.next())
    			 return rs.getInt("id");
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }
    	 return -1;
     }
     
     public ArrayList<String> getMyBook(String email){
    	 
    	 String sql_getmybook="select isbn from bookowner where id in(select id from userinfo where email='"+email+"');";
    	 ArrayList<String> bookList=new ArrayList<String>();
    	 try{
    		 Statement stmt=con.createStatement();
    		 ResultSet rs=stmt.executeQuery(sql_getmybook);
    		 for(int i=0;rs.next();i++){
    			 
    			bookList.add(rs.getString("isbn"));
    		 }
    	 }catch(Exception e){
    		e.printStackTrace();
    	 }
    	 
    	 return bookList;
    	 
     }
     
     public void setOwner(String email){
    	 String sql="insert into bookowner(isbn,id)  values(?,?)";
    	 int id=findID(email);
    	 try{
    		 PreparedStatement pstmt=con.prepareStatement(sql);
    		 pstmt.setString(1, bookBean.getIsbn());
    		 pstmt.setInt(2, id);
    		 pstmt.executeUpdate();
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }
     }
}

