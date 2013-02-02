package com.shining.ibookclubserver;

public class BookBean {

	private String isbn;
	private String bookname;
	private String press;
	private String author;
	
	public void setIsbn(String isbn){
		this.isbn=isbn;
	}
	
	public void setBookname(String bookname){
		this.bookname=bookname;
	}
	
	public void setPress(String press){
		this.press=press;
	}
	
	public void setAuthor(String author){
		this.author=author;
	}
	
	public String getIsbn(){
		
		return this.isbn;
	}
	
	public String getBookname(){
		return this.bookname;
	}
	
	public String getPress(){
		return this.press;
	}
	
	public String getAuthor(){
		return this.author;
	}
	
	
	
}
