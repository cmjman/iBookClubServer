package com.shining.ibookclubserver;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import com.shining.ibookclubserver.bean.BookBean;
import com.shining.ibookclubserver.bean.TimelineBean;
import com.shining.ibookclubserver.bean.UserBean;

public interface Service {

	public String login(String email,String password);
	
	public Boolean regist(UserBean userBean);
	
	public boolean isBookExist(BookBean bookBean);
	
	public Boolean addBook(BookBean bookBean);
	
	public Boolean setOwnerInfo(String email,String latitude,String longitude,String rating,BookBean bookBean);
	
	public Boolean checkBook(String email,String isbn);
	
	public Boolean recordBook(String email,String isbn,String nickname);
	
	public Hashtable<Integer,String> borrowBook(String email,String isbn);
	
	public Boolean deleteBook(String email,String isbn);
	
	public ArrayList<BookBean> getRecommendBook(String email);
	
	public ArrayList<BookBean> getRecentBook(String email);
	
	public ArrayList<BookBean> getMyBook(String email);
	
	public ArrayList<BookBean> getPublicBook();
	
	public ArrayList<BookBean> searchPublicBook(String keyword);
	
	public ArrayList<BookBean> getNearbyBook(String email,String latitude,String longitude);
	
	public ArrayList<BookBean> getBookByIsbn(ArrayList<String> isbn);
	
	 public ArrayList<TimelineBean> getTimeline(String email);
	 
	 public Boolean postTweet(String email,String message);
	
}
