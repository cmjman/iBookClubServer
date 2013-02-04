package com.shining.ibookclubserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BookInfoReference {
	
	private BookBean bookBean;
	
	 private Connection con;
	
	public BookInfoReference(){
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
	
	  public ArrayList<BookBean> getMyBook(String email){
	    	 
	    	 String sql_getmybook="select * from bookinfo where isbn in(select isbn from bookowner where id in(select id from userinfo where email='"+email+"'));";
	    	 ArrayList<BookBean> bookList=new ArrayList<BookBean>();
	    	 try{
	    		 Statement stmt=con.createStatement();
	    		 ResultSet rs=stmt.executeQuery(sql_getmybook);
	    		 for(int i=0;rs.next();i++){
	    			BookBean bean=new BookBean();
	    			bean.setIsbn(rs.getString("isbn"));
	    			bean.setAuthor(rs.getString("author"));
	    			bean.setBookcover_url(rs.getString("bookcover"));
	    			bean.setPublisher(rs.getString("publisher"));
	    			bean.setPrice(rs.getString("price"));
	    			bean.setBookname(rs.getString("name"));
	    			bean.setSummary(rs.getString("summary"));
	    			bookList.add(bean);
	    		 }
	    	 }catch(Exception e){
	    		e.printStackTrace();
	    	 }
	    	 
	    	 return bookList;
	    	 
	     }
	     
	  public ArrayList<BookBean> getPublicBook(){
	    	 
	    	 String sql="select * from bookinfo;";
	    	 ArrayList<BookBean> bookList=new ArrayList<BookBean>();
	    	 try{
	    		 Statement stmt=con.createStatement();
	    		 ResultSet rs=stmt.executeQuery(sql);
	    		 for(int i=0;rs.next();i++){
	    			 
	    			BookBean bean=new BookBean();
	    			bean.setIsbn(rs.getString("isbn"));
	    			bean.setAuthor(rs.getString("author"));
	    			bean.setBookcover_url(rs.getString("bookcover"));
	    			bean.setPublisher(rs.getString("publisher"));
	    			bean.setPrice(rs.getString("price"));
	    			bean.setBookname(rs.getString("name"));
	    			bean.setSummary(rs.getString("summary"));
	    			bookList.add(bean);
	    		 }
	    	 }catch(Exception e){
	    		e.printStackTrace();
	    	 }
	    	 
	    	 return bookList;
	    	 
	     }
	     
	
}
