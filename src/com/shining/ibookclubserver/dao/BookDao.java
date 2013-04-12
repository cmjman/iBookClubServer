package com.shining.ibookclubserver.dao;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import java.util.Hashtable;

import com.shining.ibookclubserver.FinalConstants;
import com.shining.ibookclubserver.bean.BookBean;
import com.shining.ibookclubserver.bean.TimelineBean;
import com.shining.ibookclubserver.bean.UserBean;
import com.sina.sae.util.SaeUserInfo;

public class BookDao {

	private static BookDao dao=new BookDao();
	
	private Connection con;
	
	private BookBean bookBean;
	
	private UserBean userBean;
	

	public BookDao() {
		
		//TODO 此处SQL在navicat中运行正常，直接用JDBC注入则会出错，待修正 
		//com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; 
		//check the manual that corresponds to your MySQL server version for the right syntax to use 
		//near 'create table if not exists bookinfo(isbn char(13),primary key(isbn),name varchar' at line 1
		
		
    	 try{
    		 con=getConnection(false);
    	
    		 PreparedStatement pstmt=con.prepareStatement(FinalConstants.SQL_CREATE);
  
             pstmt.executeUpdate();
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }
	}
	
	public Connection getConnection(Boolean read){
		
		Connection conn=null;
		
		try{
			
			Class.forName(FinalConstants.DB_DRIVER).newInstance();
			if(read)
				conn = DriverManager.getConnection(FinalConstants.DB_URL_R,FinalConstants.DB_USERNAME,FinalConstants.DB_PASSWORD);
			else
				conn = DriverManager.getConnection(FinalConstants.DB_URL_W,FinalConstants.DB_USERNAME,FinalConstants.DB_PASSWORD);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        if(conn==null){
        	System.out.println("获取数据库连接失败！");
        }
        
        return conn;
	}

	public static BookDao getInstance() {
		
		return dao;
	}
	
	public void setBookBean(BookBean bookBean){
        
		this.bookBean=bookBean;
    }
	
	public boolean isBookExist(){
    	 
    	 String sql="select isbn from bookinfo where isbn='"+bookBean.getIsbn()+"';";
    	 
    	 try{
    		 con=getConnection(true);
    		 Statement stmt=con.createStatement();
    		 ResultSet rs=stmt.executeQuery(sql);
    		 if(rs.next())
    			 return true;
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }
    	 return false;
    }
	
	public void addBook() throws Exception{
         
    	
         try{
        	 con=getConnection(false);
        	 PreparedStatement pstmt=con.prepareStatement(FinalConstants.SQL_INSERT_BOOKINFO);
             pstmt.setString(1,bookBean.getIsbn());
             pstmt.setString(2,bookBean.getName());
             pstmt.setString(3, bookBean.getPublisher());
             pstmt.setString(4, bookBean.getAuthor());
             pstmt.setString(5, bookBean.getBookcover());
             pstmt.setString(6, bookBean.getSummary());
             pstmt.setString(7, bookBean.getPrice());
             pstmt.executeUpdate();
         }
         catch(Exception e){
              
        	 e.printStackTrace();
         }      

     }
	
	public int findID(String email){
    	 
    	 String sql="select id from userinfo where email='"+email+"';";
    	 try{
    		 con=getConnection(true);
    		 Statement stmt=con.createStatement();
    		 ResultSet rs=stmt.executeQuery(sql);
    		 if(rs.next())
    			 return rs.getInt("id");
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }
    	 return -1;
     }
	
	public void setOwnerInfo(String email,String latitude,String longitude,String rating){
   	 
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
   	 	int id=findID(email);
   	 	try{
   	 		con=getConnection(false);
   	 		PreparedStatement pstmt=con.prepareStatement(FinalConstants.SQL_INSERT_BOOKOWNER);
   	 		pstmt.setString(1, bookBean.getIsbn());
   	 		pstmt.setInt(2, id);
   	 		pstmt.setString(3, latitude);
   	 		pstmt.setString(4, longitude);
   	 		pstmt.setTimestamp(5, timeStamp);
   	 		pstmt.setFloat(6, Float.parseFloat(rating));
   	 		pstmt.executeUpdate();
   	 	}catch(Exception e){
   	 		e.printStackTrace();
   	 	}
    }
	

	
	public Boolean checkBook(String email,String isbn){
		
		 String sql="select * from bookowner where isbn ='"+isbn+"' and id in (select id from userinfo where email ='"+email+"');" ;
		 
		 try{
			 con=getConnection(true);
			 Statement stmt=con.createStatement();
			 ResultSet rs=stmt.executeQuery(sql);
			 if(rs.next())
				 return true;
	
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return false;
		
	}
	
	public Boolean recordBook(String email,String isbn,String nickname){
		
		String sql="";
		try{
			 con=getConnection(true);
			 Statement stmt=con.createStatement();
			 ResultSet rs=stmt.executeQuery(sql);
			 
			 while(rs.next()){
				 
				//TODO 借书记录，具体sql待写入
				
				
			 }
			}catch(Exception e){
			 e.printStackTrace();
			}
		
		return true;
	}
	
	public Hashtable<Integer,String> borrowBook(String email,String isbn){
		
		String sql="select id,nickname from userinfo where id in (" +
					"select id from bookowner where isbn ='"+isbn+"') and id <> (" +
					"select id from userinfo where email ='"+email+"');" ;
		Hashtable<Integer,String> ownerTable=new Hashtable<Integer,String>();
		 try{
			 con=getConnection(true);
			 Statement stmt=con.createStatement();
			 ResultSet rs=stmt.executeQuery(sql);
			 
			 while(rs.next()){
				 
				
				 ownerTable.put(rs.getInt("id"), rs.getString("nickname"));
				
			 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return ownerTable;
		
	}
	
	public void deleteBook(String email,String isbn){
		 
		 String sql="delete from bookowner where isbn ='"+isbn+"' and id in (select id from userinfo where email ='"+email+"');" ;
		 
		 try{
			 con=getConnection(false);
			 Statement stmt=con.createStatement();
			 stmt.execute(sql);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	
	public ArrayList<BookBean> getRecommendBook(String email){
		
		//TODO 具体SQL待修改,现为获取最近书籍数据，仅测试用
		String  sql="select * from (" +
	   	 		"(bookinfo inner join bookowner on bookinfo.isbn = bookowner.isbn)" +
	   	 		"inner join userinfo on bookowner.id = userinfo.id" +
	   	 		")order by postTime desc;"
	   	 	;
		
		 ArrayList<BookBean> bookList=new ArrayList<BookBean>();
	   	 try{
	   		 con=getConnection(true);
	   		 Statement stmt=con.createStatement();
	   		 ResultSet rs=stmt.executeQuery(sql);
	   		 while(rs.next()){
	   			BookBean bean=new BookBean();
	   			bean.setIsbn(rs.getString("isbn"));
	   			System.out.println("BookDao getRecommendBook isbn:"+rs.getString("isbn"));
	   			bean.setAuthor(rs.getString("author"));
	   			bean.setBookcover(rs.getString("bookcover"));
	   			bean.setPublisher(rs.getString("publisher"));
	   			bean.setPrice(rs.getString("price"));
	   			bean.setName(rs.getString("name"));
	   			bean.setSummary(rs.getString("summary"));
	   			bean.setTimeStamp(rs.getString("postTime"));
	   			bookList.add(bean);
	   		 }
	   	 }catch(Exception e){
	   		e.printStackTrace();
	   	 }
	   	 return bookList;
	}
	
	public ArrayList<BookBean> getRecentBook(String email){
	   	 
	   	 String sql="select * from (" +
	   	 		"(bookinfo inner join bookowner on bookinfo.isbn = bookowner.isbn)" +
	   	 		"inner join userinfo on bookowner.id = userinfo.id" +
	   	 		")order by postTime desc;"
	   	 	;
	   	 ArrayList<BookBean> bookList=new ArrayList<BookBean>();
	   	 try{
	   		 con=getConnection(true);
	   		 Statement stmt=con.createStatement();
	   		 ResultSet rs=stmt.executeQuery(sql);
	   		 while(rs.next()){
	   			BookBean bean=new BookBean();
	   			bean.setIsbn(rs.getString("isbn"));
	   			System.out.println("BookDao getRecentBook isbn:"+rs.getString("isbn"));
	   			bean.setAuthor(rs.getString("author"));
	   			bean.setBookcover(rs.getString("bookcover"));
	   			bean.setPublisher(rs.getString("publisher"));
	   			bean.setPrice(rs.getString("price"));
	   			bean.setName(rs.getString("name"));
	   			bean.setSummary(rs.getString("summary"));
	   			bean.setTimeStamp(rs.getString("postTime"));
	   			bookList.add(bean);
	   		 }
	   	 }catch(Exception e){
	   		e.printStackTrace();
	   	 }
	   	 return bookList;
  	 
   }
	
	public ArrayList<BookBean> getMyBook(String email){
   	 
	   	 String sql="select * from bookinfo where isbn in(select isbn from bookowner where id in(select id from userinfo where email='"+email+"'));";
	   	 ArrayList<BookBean> bookList=new ArrayList<BookBean>();
	   	 try{
	   		 con=getConnection(true);
	   		 Statement stmt=con.createStatement();
	   		 ResultSet rs=stmt.executeQuery(sql);
	   		 while(rs.next()){
	   			BookBean bean=new BookBean();
	   			bean.setIsbn(rs.getString("isbn"));
	   			bean.setAuthor(rs.getString("author"));
	   			bean.setBookcover(rs.getString("bookcover"));
	   			bean.setPublisher(rs.getString("publisher"));
	   			bean.setPrice(rs.getString("price"));
	   			bean.setName(rs.getString("name"));
	   			bean.setSummary(rs.getString("summary"));
	   			bookList.add(bean);
	   		 }
	   	 }catch(Exception e){
	   		e.printStackTrace();
	   	 }
	   	 return bookList;
   	 
    }
	
	public ArrayList<BookBean> getPublicBook(){
   	 
	   	
	   	 ArrayList<BookBean> bookList=new ArrayList<BookBean>();
	   	 try{
	   		 con=getConnection(true);
	   		 Statement stmt=con.createStatement();
	   		 ResultSet rs=stmt.executeQuery(FinalConstants.SQL_SELECT_BOOKINFO);
	   		 while(rs.next()){
	   			 
	   			BookBean bean=new BookBean();
	   			bean.setIsbn(rs.getString("isbn"));
	   			bean.setAuthor(rs.getString("author"));
	   			bean.setBookcover(rs.getString("bookcover"));
	   			bean.setPublisher(rs.getString("publisher"));
	   			bean.setPrice(rs.getString("price"));
	   			bean.setName(rs.getString("name"));
	   			bean.setSummary(rs.getString("summary"));
	   			bookList.add(bean);
	   		 }
	   	 }catch(Exception e){
	   		e.printStackTrace();
	   	 }
	   	 return bookList;
   	 
    }
	
	public ArrayList<BookBean> searchPublicBook(String keyword){
		
		String sql="select * from bookinfo where name like '%"+keyword+"%';";
			
		ArrayList<BookBean> bookList=new ArrayList<BookBean>();
	   	 try{
	   		 con=getConnection(true);
	   		 Statement stmt=con.createStatement();
	   		 ResultSet rs=stmt.executeQuery(sql);
	   		 for(int i=0;rs.next();i++){
	   			 
	   			BookBean bean=new BookBean();
	   			bean.setIsbn(rs.getString("isbn"));
	   			bean.setAuthor(rs.getString("author"));
	   			bean.setBookcover(rs.getString("bookcover"));
	   			bean.setPublisher(rs.getString("publisher"));
	   			bean.setPrice(rs.getString("price"));
	   			bean.setName(rs.getString("name"));
	   			bean.setSummary(rs.getString("summary"));
	   			bookList.add(bean);
	   		 }
	   	 }catch(Exception e){
	   		e.printStackTrace();
	   	 }
	   	 return bookList;
		
	}

	private static final double EARTH_RADIUS = 6378.137;
	
	private static double rad(double d)
	{
	   return d * Math.PI / 180.0;
	}

	public static double GetDistance(double lat1, double lng1, double lat2, double lng2)
	{
	   double radLat1 = rad(lat1);
	   double radLat2 = rad(lat2);
	   double a = radLat1 - radLat2;
	   double b = rad(lng1) - rad(lng2);
	   double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + 
	    Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
	   s = s * EARTH_RADIUS;
	   s = Math.round(s * 10000) / 10000;
	   return s;
	}

	
	public ArrayList<String> getNearbyBook(String email,String latitude,String longitude){
		
		String sql="select * from bookowner where id <> (select id from userinfo where email ='"+email+"');";
		
		double lat=Double.parseDouble(latitude);
		double lng=Double.parseDouble(longitude);	
		ArrayList<String> bookISBN=new ArrayList<String>();
	   	 try{
	   		 con=getConnection(true);
	   		 Statement stmt=con.createStatement();
	   		 ResultSet rs=stmt.executeQuery(sql);
	   		 for(int i=0;rs.next();i++){
	   			 
	   			if(GetDistance(Double.parseDouble(rs.getString("latitude")),
	   							Double.parseDouble(rs.getString("longitude")),
	   							lat,lng)<1.0)
	   			bookISBN.add(rs.getString("isbn"));
	   			
	   			System.out.println(GetDistance(Double.parseDouble(rs.getString("latitude")),
							Double.parseDouble(rs.getString("longitude")),
							lat,lng));	
	   		 }
	   	 }catch(Exception e){
	   		e.printStackTrace();
	   	 }
	   	 return bookISBN;
		
	}
	
	public ArrayList<BookBean> getBookByIsbn(ArrayList<String> isbn){
		
		ArrayList<BookBean> bookList=new ArrayList<BookBean>();
		
		for(String is:isbn){
		
		String sql="select * from bookinfo where isbn = '"+is+"';";
			
	
	   	 try{
	   		 con=getConnection(true);
	   		 Statement stmt=con.createStatement();
	   		 ResultSet rs=stmt.executeQuery(sql);
	   		 for(int i=0;rs.next();i++){
	   			 
	   			BookBean bean=new BookBean();
	   			bean.setIsbn(rs.getString("isbn"));
	   			bean.setAuthor(rs.getString("author"));
	   			bean.setBookcover(rs.getString("bookcover"));
	   			bean.setPublisher(rs.getString("publisher"));
	   			bean.setPrice(rs.getString("price"));
	   			bean.setName(rs.getString("name"));
	   			bean.setSummary(rs.getString("summary"));
	   			bookList.add(bean);
	   		 }
	   	 }catch(Exception e){
	   		e.printStackTrace();
	   	 }
		}
	   	 
	   	return bookList;
		
	}
	
	 public void setUserBean(UserBean userBean){
         
    	 this.userBean=userBean;
     }
	 
	 public ArrayList<TimelineBean> getTimeline(String email){
		 
		 ArrayList<TimelineBean> timeline=new ArrayList<TimelineBean>();
		
		 String sql="select * from("+
				 "timeline inner join userinfo on timeline.id = userinfo.id)" +
				 "order by timestamp desc;";
		
		

	   	 try{
	   		 con=getConnection(true);
	   		 Statement stmt=con.createStatement();
	   		 ResultSet rs=stmt.executeQuery(sql);
	   		 for(int i=0;rs.next();i++){
	   			 
	   			TimelineBean bean=new TimelineBean();
	   			
	   			String avatar=rs.getString("picture");
	   			if(avatar==null){
	   				avatar=FinalConstants.SERVER_URL+"Image/stub.png";
	   			}
	  
	   			bean.setAvatar(avatar);
	   			bean.setMessage(rs.getString("message"));
	   			bean.setNickname(rs.getString("nickname"));
	   			
	   	
	   			bean.setTimeStamp(rs.getString("timestamp"));
	   			
	   			timeline.add(bean);
	   		 }
	   	 }catch(Exception e){
	   		e.printStackTrace();
	   	 }
		 
		 return timeline; 
	 }
	 
	 public Boolean postTweet(String email,String message){
		 
	
		 String sql="insert into timeline(id,message,timestamp)  values(?,?,?)";
		 Date date = new Date();
		 Timestamp timeStamp = new Timestamp(date.getTime());
		 
		 try{
        	 con=getConnection(false);
        	 PreparedStatement pstmt=con.prepareStatement(sql);
             pstmt.setInt(1,findID(email));
             pstmt.setString(2,message);
             pstmt.setTimestamp(3, timeStamp);
             pstmt.executeUpdate();
             return true;
         }
         catch(Exception e){
              
        	 e.printStackTrace();
        	 
        	 return false;
         }      
		 
	 }
	 
	 
	 
 
     public Boolean regist() throws Exception{
           
    	 
         try{
        	 con=getConnection(false);
        	 PreparedStatement pstmt=con.prepareStatement(FinalConstants.SQL_INSERT_USERINFO);
             pstmt.setString(1,userBean.getEmail());
             pstmt.setString(2,userBean.getPassWord());
             pstmt.setString(3, userBean.getNickName());
             pstmt.setString(4, userBean.getAge());
             pstmt.setString(5, userBean.getGender());
             System.out.println( userBean.getGender());
             pstmt.setString(6, userBean.getInterest());
             pstmt.executeUpdate();
             return true;
         }
         catch(Exception e){
        	 e.printStackTrace();
        	 return false;
         }      

     }
     
     
     public String checkPassword(String email,String pw_check) throws SQLException {
    	 
    	 String sql="select * from  userinfo where email='"+email+"';";
    	 
    	 con=getConnection(true);
    	 Statement stmt=con.createStatement();
		 ResultSet rs=stmt.executeQuery(sql);
		 String pw=null;
		 String nickname=null;
		 
		  
		 if(rs.next())
		  {
			  pw=new String(rs.getString("password"));
		  
		 
			  if(pw.equals(pw_check)){
			 
				  nickname=new String(rs.getString("nickname"));
				  
			  }else{
				  nickname="-1";
			  }
			  System.out.println(nickname);
		  }
		 
		 return nickname;
		  
		 
     }
}
