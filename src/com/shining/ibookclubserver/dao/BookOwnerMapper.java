package com.shining.ibookclubserver.dao;

import java.sql.Timestamp;

import org.apache.tomcat.util.buf.TimeStamp;

public class BookOwnerMapper {

	private int bookID;
	
	private String isbn;
	
	private int id;
	
	private int borrowID;
	
	private String borrowTime;
	
	private String latitude;
	
	private String longitude;
	
	private Timestamp postTime;
	
	private Float rating;

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBorrowID() {
		return borrowID;
	}

	public void setBorrowID(int borrowID) {
		this.borrowID = borrowID;
	}

	public String getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Timestamp getPostTime() {
		return postTime;
	}

	public void setPostTime(Timestamp postTime) {
		this.postTime = postTime;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}
	
	
}
