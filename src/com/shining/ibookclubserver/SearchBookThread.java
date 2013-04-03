package com.shining.ibookclubserver;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.shining.ibookclubserver.bean.BookBean;

import net.sf.json.JSONObject;



public class SearchBookThread extends Thread{

	private static String APIKey="003afe0642e755f700b0fa12c8b601e5";
	
	private static String URL = "https://api.douban.com/v2/book/isbn/";
	
	private String isbn;
	private BookBean bookbean=new BookBean();
	
	public SearchBookThread(String isbn){
		this.isbn=isbn;
	}
	
	public void run() {
		
		URL url;
		JSONObject json;
		try {
			
			url = new URL(URL+isbn+"?apikey="+APIKey);
		
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
			conn.setConnectTimeout(5 * 1000);      
			conn.setRequestMethod("GET");     
			conn.setRequestProperty("Charset", "UTF-8");
		
		
			InputStream inStream = conn.getInputStream(); 
			
			String strResult="";  
			
			byte[] b = new byte[1024];  
			int i = 0;  
			while ((i = inStream.read(b)) != -1) {  
			    strResult+=new String(b);  
			    b = new byte[1024];   
			}  
			
			
			json = JSONObject.fromObject(strResult);
			
			//System.out.println(json);
			
			
			bookbean.setIsbn(isbn);
			bookbean.setAuthor(json.getString("author"));
			bookbean.setBookname(json.getString("title"));
			bookbean.setPublisher(json.getString("publisher"));
			
		//	bookbean.notify();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	
		
		
	}
	
	public BookBean getBean() throws InterruptedException{
	//	bookbean.wait();
		return bookbean;
	}
}
