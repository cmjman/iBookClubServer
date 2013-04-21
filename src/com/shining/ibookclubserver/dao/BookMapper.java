package com.shining.ibookclubserver.dao;

import java.util.List;
import java.util.Map;

import com.shining.ibookclubserver.bean.BookBean;
import com.shining.ibookclubserver.bean.TimelineBean;

public interface BookMapper {

	public BookBean getBookByISBN(String isbn);
	
	public BookOwnerMapper checkIfPosted(Map map);
	
	public List<BookOwnerMapper> getOwnerInfoExceptSelf(int id);
	
	public List<BookBean> getRecentBook();
	
	public List<BookBean> getMyBook(int id);
	
	public List<BookBean> getBookInfo();
	
	public List<BookBean> searchPublicBook(String keyword);
	
	public List<TimelineBean> getTimeline();
	
	public void addBook(BookBean bookBean);
	
	public void setOwnerInfo(BookOwnerMapper bookOwnerMapper);

	public void postTweet(Map<String,Object> map);
	
	public void deleteBook(Map<String,Object> map);
}
