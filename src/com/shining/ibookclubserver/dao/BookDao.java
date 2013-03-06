package com.shining.ibookclubserver.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

import com.shining.ibookclubserver.BookBean;
import com.shining.ibookclubserver.UserBean;
import com.sina.sae.util.SaeUserInfo;

public class BookDao {

	private static BookDao dao =new BookDao();
	
	private Connection con;
	
	private BookBean bookBean;
	
	private UserBean userBean;
	
	private String username=SaeUserInfo.getAccessKey();
	private String password=SaeUserInfo.getSecretKey();
	
//	private String mysql_url="jdbc:mysql://localhost:3306/"+"iBookClubDB"+"?user="+"root"+"&password="+"123456";
	
	private String mysql_url_w="jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_ibookclubserver";
	
	private String mysql_url_r="jdbc:mysql://r.rdc.sae.sina.com.cn:3307/app_ibookclubserver";

	private BookDao() {
		 
		
       
	}
	
	public Connection getConnection(Boolean read){
		
		Connection conn=null;
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			if(read)
				conn = DriverManager.getConnection(mysql_url_r,username,password);
			else
				conn = DriverManager.getConnection(mysql_url_w,username,password);
            
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
         
    	 String sql="insert into bookinfo(isbn,name,publisher,author,bookcover,summary,price)  values(?,?,?,?,?,?,?)";
         try{
        	 con=getConnection(false);
        	 PreparedStatement pstmt=con.prepareStatement(sql);
             pstmt.setString(1,bookBean.getIsbn());
             pstmt.setString(2,bookBean.getBookname());
             pstmt.setString(3, bookBean.getPublisher());
             pstmt.setString(4, bookBean.getAuthor());
             pstmt.setString(5, bookBean.getBookcover_url());
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
	
	public void setOwnerInfo(String email,String latitude,String longitude){
   	 
		String sql="insert into bookowner(isbn,id,latitude,longitude)  values(?,?,?,?)";
   	 	int id=findID(email);
   	 	try{
   	 		con=getConnection(false);
   	 		PreparedStatement pstmt=con.prepareStatement(sql);
   	 		pstmt.setString(1, bookBean.getIsbn());
   	 		pstmt.setInt(2, id);
   	 		pstmt.setString(3, latitude);
   	 		pstmt.setString(4, longitude);
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
	
	public ArrayList<BookBean> getMyBook(String email){
   	 
	   	 String sql="select * from bookinfo where isbn in(select isbn from bookowner where id in(select id from userinfo where email='"+email+"'));";
	   	 ArrayList<BookBean> bookList=new ArrayList<BookBean>();
	   	 try{
	   		 con=getConnection(true);
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
	
	public ArrayList<BookBean> getPublicBook(){
   	 
	   	 String sql="select * from bookinfo where isbn in(select isbn from bookowner);";
	   	 ArrayList<BookBean> bookList=new ArrayList<BookBean>();
	   	 try{
	   		 con=getConnection(true);
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
		}
	   	 
	   	return bookList;
		
	}
	
	 public void setUserBean(UserBean userBean){
         
    	 this.userBean=userBean;
     }
	 
	 
	 
 
     public Boolean regist() throws Exception{
           
    	 String reg="insert into userinfo(email,password,nickname)  values(?,?,?)";
         try{
        	 con=getConnection(false);
        	 PreparedStatement pstmt=con.prepareStatement(reg);
             pstmt.setString(1,userBean.getEmail());
             pstmt.setString(2,userBean.getPassWord());
             pstmt.setString(3, userBean.getNickName());
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
