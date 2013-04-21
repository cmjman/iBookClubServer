package com.shining.ibookclubserver;

import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;

import com.shining.ibookclubserver.bean.BookBean;
import com.shining.ibookclubserver.bean.TimelineBean;
import com.shining.ibookclubserver.bean.UserBean;
import com.shining.ibookclubserver.dao.BookMapper;
import com.shining.ibookclubserver.dao.BookOwnerMapper;
import com.shining.ibookclubserver.dao.UserMapper;

public class ServiceImpl implements Service{
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private BookMapper bookMapper;
	

	
	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	public BookMapper getBookMapper() {
		return bookMapper;
	}

	public void setBookMapper(BookMapper bookMapper) {
		this.bookMapper = bookMapper;
	}
	
	@Override
	public String login(String email,String password) {
		
		System.out.println("LoginAction:"+email+password);
		
		Map<String,String> json=new HashMap<String,String>(); ;
		
		try {
			
			
		
			
			String nickname;
			UserBean userBean=userMapper.getUserinfo(email);
			
			
			
	  		
	  		if(userBean==null){
	  			//用户不存在
	  			nickname="0";
	  		}
	  		else if(userBean.getPassword().equals(password)){
	
		  		nickname=userBean.getNickname();
		  	}else{
		  		//密码错误
		  		nickname="-1";
		  	}
			
			
			
			if(nickname!="-1"){
				
		//		request.getSession(true).setAttribute("email" , email);
				
				
				
				json.put("email" , email);
				json.put("nickname", nickname);
				json.put("ActionResult", "true");
				System.out.println("登陆成功");
				
			}
			else{
				 
				json.put("ActionResult", "false");
				System.out.println("登陆失败");
			}
			
			//byte[] jsonBytes = json.toString().getBytes("utf-8");  

		
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
		
		return json.toString();
		
	}

	@Override
	public Boolean regist(UserBean userBean) {
		
		
		userMapper.regist(userBean);
		
		return true;
	}

	@Override
	public boolean isBookExist(BookBean bookBean) {
		
		Boolean result=false;
	  	
	  	try{
		  	
	  		
	  		BookBean bean=bookMapper.getBookByISBN(bookBean.getIsbn());
		  	
	  		if(bean!=null)
	  			result=true;
	
	  	}catch(Exception e){
	  		
	  		e.printStackTrace();
	  	}
	  	
	  	return result;
	}

	@Override
	public Boolean addBook(BookBean bookBean) {
		
		Boolean result=false;

		bookMapper.addBook(bookBean);
		
		result =true;
		
		return result;
	}

	@Override
	public Boolean setOwnerInfo(String email, String latitude,
			String longitude, String rating,BookBean bookBean) {
		
		UserBean userBean=userMapper.getUserinfo(email);
		
		Boolean result=false;
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		BookOwnerMapper bookOwnerMapper=new BookOwnerMapper();
		bookOwnerMapper.setIsbn(bookBean.getIsbn());
		bookOwnerMapper.setId(userBean.getId());
		bookOwnerMapper.setLatitude(latitude);
		bookOwnerMapper.setLongitude(longitude);
		bookOwnerMapper.setPostTime(timeStamp);
		bookOwnerMapper.setRating(Float.parseFloat(rating));
		
		
		bookMapper.setOwnerInfo(bookOwnerMapper);
		result=true;
		
		return result;
	}

	@Override
	public Boolean checkBook(String email, String isbn) {
		
		Boolean result=false;
		
		UserBean userBean=new UserBean();
		BookOwnerMapper bookOwnerMapper=new BookOwnerMapper();
		
		try{
			
			userBean=userMapper.getUserinfo(email);
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("isbn", isbn);
			param.put("id", userBean.getId());
			bookOwnerMapper=bookMapper.checkIfPosted(param);
			if(bookOwnerMapper!=null)
				result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Boolean recordBook(String email, String isbn, String nickname) {
		
		Boolean result=false;
		
		//TODO 借书记录写入待补充
		
		return result;
	}

	@Override
	public Hashtable<Integer, String> borrowBook(String email, String isbn) {
		
		Hashtable<Integer,String> ownerTable=new Hashtable<Integer,String>();
		
		//TODO
		
		
		return ownerTable;
	}

	@Override
	public Boolean deleteBook(String email, String isbn) {
		Boolean result=false;
		
		
		UserBean userBean=new UserBean();
		BookOwnerMapper bookOwnerMapper=new BookOwnerMapper();
		
		try{
			
			userBean=userMapper.getUserinfo(email);
			Map<String, Object> param=new HashMap<String, Object>();  
			param.put("isbn", isbn);
			param.put("id", userBean.getId());
			bookMapper.deleteBook(param);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public ArrayList<BookBean> getRecommendBook(String email) {
	
		ArrayList<BookBean> bookList=new ArrayList<BookBean>();
		 
		 //TODO 获取推荐书籍算法未完成，现返回最近书籍列表，仅供测试用
		
		 bookList=getRecentBook(email);
		 
		 return bookList;
	}

	@Override
	public ArrayList<BookBean> getRecentBook(String email) {
		
		 List<BookBean> bookList=new ArrayList<BookBean>();
	  	
			
			bookList=bookMapper.getRecentBook();
		
	  	 
	  	
		return (ArrayList)bookList;
	}

	@Override
	public ArrayList<BookBean> getMyBook(String email) {
		
		 List<BookBean> bookList=new ArrayList<BookBean>();
		  	
			int id=userMapper.getUserinfo(email).getId();
		 
			bookList=bookMapper.getMyBook(id);
		

		return (ArrayList)bookList;
	}

	@Override
	public ArrayList<BookBean> getPublicBook() {
	 	 
		List<BookBean> bookList=new ArrayList<BookBean>();
	 
		bookList=bookMapper.getBookInfo();
	
	 	return (ArrayList)bookList;
	}

	@Override
	public ArrayList<BookBean> searchPublicBook(String keyword) {
		
		List<BookBean> bookList=new ArrayList<BookBean>();
	
		bookList=bookMapper.searchPublicBook("%"+keyword+"%");
		
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

	@Override
	public ArrayList<BookBean> getNearbyBook(String email, String latitude,
			String longitude) {
		double lat=Double.parseDouble(latitude);
		double lng=Double.parseDouble(longitude);	
		ArrayList<BookBean> bookList=new ArrayList<BookBean>();
		List<String> bookISBN=new ArrayList<String>();
	
		
		try{
			
			UserBean userBean=userMapper.getUserinfo(email);
			
			List<BookOwnerMapper> ownerList=bookMapper.getOwnerInfoExceptSelf(userBean.getId());
		
			
			for(BookOwnerMapper mapper:ownerList){
				
				if(GetDistance(Double.parseDouble(mapper.getLatitude()),
						Double.parseDouble(mapper.getLongitude()),
						lat,lng)<1.0)
				bookISBN.add(mapper.getIsbn());		
			}
			
			bookList=getBookByIsbn((ArrayList)bookISBN);
			
		}catch(Exception e){
			 e.printStackTrace();
		 }

		return (ArrayList)bookList;
		
	}

	@Override
	public ArrayList<BookBean> getBookByIsbn(ArrayList<String> isbn) {
	
		List<BookBean> bookList=new ArrayList<BookBean>();
	
	
		
		try{
			for(String is:isbn){
			
				bookList.add((BookBean)bookMapper.getBookByISBN(is));
			
			}
	   	
	   	 }catch(Exception e){
			 e.printStackTrace();
		 }
	   	 
	   	return (ArrayList)bookList;
	}

	@Override
	public ArrayList<TimelineBean> getTimeline(String email) {
		
		 List<TimelineBean> timeline=new ArrayList<TimelineBean>();
	
		 String SERVER_URL=null;
		 
	   	 try{
	   			
	   		 timeline=bookMapper.getTimeline();
	   		 
	   		Properties prop = new Properties();  
	    	 
		  //    InputStream fis = ServiceImpl.class.getClassLoader().getResourceAsStream("SQLMapConfig.properties");
		         
		//      prop.load(fis);
		     
		 //     SERVER_URL=prop.getProperty("url");
	   		
	   		SERVER_URL="http://1.ibookclubserver.sinaapp.com/";
	   			
	   		 for(TimelineBean bean:timeline){
	   		 
		   		if(bean.getAvatar()==null){
		   			bean.setAvatar(SERVER_URL+"Image/stub.png");
		   		}
	   		 }
	  
	   		 
	   	 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return (ArrayList)timeline; 
	}

	@Override
	public Boolean postTweet(String email, String message) {
		 
		 Boolean result=false;
	
		 Date date = new Date();
		 Timestamp timeStamp = new Timestamp(date.getTime());
		 
		 try{
			
			UserBean userBean=userMapper.getUserinfo(email);
			Map<String, Object> param=new HashMap<String, Object>();  
			param.put("id", userBean.getId());
			param.put("message", message);
			param.put("timestamp", timeStamp);
			
			bookMapper.postTweet(param);
			result=true;
		
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 return result;
	}
	
}
