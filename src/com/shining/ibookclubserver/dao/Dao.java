package com.shining.ibookclubserver.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.shining.ibookclubserver.FinalConstants;
import com.shining.ibookclubserver.bean.BookBean;
import com.shining.ibookclubserver.bean.TimelineBean;
import com.shining.ibookclubserver.bean.UserBean;

public class Dao {
	
	private static Dao dao=new Dao();
	
	private SqlSessionFactory factory;
	
	private BookBean bookBean;
	
	private UserBean userBean;

	public Dao(){
		
		 String resource = "SQLMapConfig.xml";
	     Reader reader;
	     SqlSessionFactoryBuilder builder;
	     try {
				
	    	  reader = Resources.getResourceAsReader(resource);
	    	  builder = new SqlSessionFactoryBuilder();
			  factory = builder.build(reader);
	     
	     }catch(Exception e) {
				
			e.printStackTrace();
	     }
	}
	
	public static Dao getInstance() {
		
		return dao;
	}
	
	//UserDao
	
	 public void setUserBean(UserBean userBean){
         
    	 this.userBean=userBean;
     }
	 
	public String checkPassword(String email,String pw_check) throws SQLException {
	 
	 
	  	SqlSession session=  factory.openSession();
		String nickname=null;
	  	
	  	try{
		  	
	  //		UserMapper userMapper=session.selectOne("com.shining.ibookclubserver.dao.UserMapper.checkPassword", email);
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
	
	
	//BookDao
	
	public void setBookBean(BookBean bookBean){
        
		this.bookBean=bookBean;
    }
	
	public boolean isBookExist(){
		
		SqlSession session=  factory.openSession();
		Boolean result=false;
	  	
	  	try{
		  	
	  		BookBean bean=session.selectOne("com.shining.ibookclubserver.dao.BookMapper.getBookByISBN", bookBean.getIsbn());
		  	
	  		if(bean!=null)
	  			result=true;
	
	  	}catch(Exception e){
	  		
	  		e.printStackTrace();
	  	}finally {
	  		
	  		session.close();
	  	}
	  	
	  	return result;
   }
	
	public Boolean addBook(){
		
		SqlSession session=  factory.openSession();
		Boolean result=false;

		try{
			session.insert("com.shining.ibookclubserver.dao.BookMapper.addBook",bookBean);
			session.commit();
			
			result=true;
		}catch(Exception e){
	  		
	  		e.printStackTrace();
	  	}finally {
	  		
	  		session.close();
	  	}
		return result;
	}
	
	public Boolean setOwnerInfo(String email,String latitude,String longitude,String rating){
		
		SqlSession session=  factory.openSession();
		Boolean result=false;
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		
		try{
			UserMapper userMapper=getUserinfoByEmail(email);
			BookOwnerMapper bookOwnerMapper=new BookOwnerMapper();
			bookOwnerMapper.setIsbn(bookBean.getIsbn());
			bookOwnerMapper.setId(userMapper.getId());
			bookOwnerMapper.setLatitude(latitude);
			bookOwnerMapper.setLongitude(longitude);
			bookOwnerMapper.setPostTime(timeStamp);
			bookOwnerMapper.setRating(Float.parseFloat(rating));
			session.insert("com.shining.ibookclubserver.dao.BookMapper.setOwnerInfo",bookOwnerMapper);
			session.commit();
			result=true;
		}catch(Exception e){
	  		
	  		e.printStackTrace();
	  	}finally {
	  		
	  		session.close();
	  	}
		
		return result;
	}
	
	public Boolean checkBook(String email,String isbn){
		
		SqlSession session=  factory.openSession();
		Boolean result=false;
		
		UserMapper userMapper=new UserMapper();
		BookOwnerMapper bookOwnerMapper=new BookOwnerMapper();
		
		try{
			
			userMapper=session.selectOne("com.shining.ibookclubserver.dao.UserMapper.getUserinfo", email);
			bookOwnerMapper=session.selectOne("com.shining.ibookclubserver.dao.BookMapper.getBookOwnerInfo", userMapper.getId());
			if(bookOwnerMapper!=null)
				result=true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return result;
	}
	
	public Boolean recordBook(String email,String isbn,String nickname){
		
		Boolean result=false;
		
		//TODO 借书记录写入待补充
		
		return result;
	
	}
	
	
	
	public Hashtable<Integer,String> borrowBook(String email,String isbn){
		
		Hashtable<Integer,String> ownerTable=new Hashtable<Integer,String>();
		
		//TODO
		
		
		return ownerTable;
	}
	
	public Boolean deleteBook(String email,String isbn){
		
		Boolean result=false;
		SqlSession session=  factory.openSession();
		
		UserMapper userMapper=new UserMapper();
		BookOwnerMapper bookOwnerMapper=new BookOwnerMapper();
		
		try{
			
			userMapper=session.selectOne("com.shining.ibookclubserver.dao.UserMapper.getUserinfo", email);
			Map<String, Object> param=new HashMap<String, Object>();  
			param.put("isbn", isbn);
			param.put("id", userMapper.getId());
			session.delete("com.shining.ibookclubserver.dao.BookMapper.deleteBook",param);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		
		return result;
	}
	
	public ArrayList<BookBean> getRecommendBook(String email){
		
		 ArrayList<BookBean> bookList=new ArrayList<BookBean>();
		 
		
		 
		 return bookList;
	}
	
	
	public ArrayList<BookBean> getRecentBook(String email){
		
	  	 List<BookBean> bookList=new ArrayList<BookBean>();
	  	 SqlSession session=  factory.openSession();
		
		try{
			
			bookList=session.selectList("com.shining.ibookclubserver.dao.BookMapper.getRecentBook");
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	  	 
	  	
		return (ArrayList)bookList;
	}
	
	
	public ArrayList<BookBean> getMyBook(String email){
		
		 List<BookBean> bookList=new ArrayList<BookBean>();
		 SqlSession session=  factory.openSession();
		 
		 UserMapper userMapper=new UserMapper();
			
		try{
			userMapper=session.selectOne("com.shining.ibookclubserver.dao.UserMapper.getUserinfo", email);
			bookList=session.selectList("com.shining.ibookclubserver.dao.BookMapper.getMyBook",userMapper.getId());
			session.commit();
				
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	
		 return (ArrayList)bookList;
	}
	
	public ArrayList<BookBean> getPublicBook(){
		
	 	 List<BookBean> bookList=new ArrayList<BookBean>();
	 	 SqlSession session=  factory.openSession();
	 	 
	 	 try{
			
			bookList=session.selectList("com.shining.ibookclubserver.dao.BookMapper.getBookInfo");
			session.commit();
				
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}

	 	return (ArrayList)bookList;
		
	}
	
	public ArrayList<BookBean> searchPublicBook(String keyword){
		
		 List<BookBean> bookList=new ArrayList<BookBean>();
		 SqlSession session=  factory.openSession();
		 
		 try{
			
			bookList=session.selectList("com.shining.ibookclubserver.dao.BookMapper.searchPublicBook","%"+keyword+"%");
			session.commit();
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 session.close();
		 }
		 
		return (ArrayList)bookList;
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
	   
		//TODO
	   	
		
		return bookISBN;
		
	}
	
	public ArrayList<BookBean> getBookByIsbn(ArrayList<String> isbn){
		
		List<BookBean> bookList=new ArrayList<BookBean>();
		SqlSession session=  factory.openSession();
		
		try{
			for(String is:isbn){
			
				bookList.add((BookBean)session.selectOne("com.shining.ibookclubserver.dao.BookMapper.getBookByISBN", is));
				session.commit();
			}
	   	
	   	 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 session.close();
		 }
	
	   	 
	   	return (ArrayList)bookList;
	}
	
	 public ArrayList<TimelineBean> getTimeline(String email){
		 
		 List<TimelineBean> timeline=new ArrayList<TimelineBean>();
		 SqlSession session=  factory.openSession();
	
	   	 try{
	   			
	   		 timeline=session.selectList("com.shining.ibookclubserver.dao.BookMapper.getTimeline");
	   			
	   		 for(TimelineBean bean:timeline){
	   		 
		   		if(bean.getAvatar()==null){
		   			bean.setAvatar(FinalConstants.SERVER_URL+"Image/stub.png");
		   		}
	   		 }
	  
	   		session.commit();
	   		 
	   	 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 session.close();
		 }
	
		 return (ArrayList)timeline; 
	 }

}
