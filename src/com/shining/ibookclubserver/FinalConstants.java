package com.shining.ibookclubserver;

import com.sina.sae.util.SaeUserInfo;

public class FinalConstants {
	
//	Local DB info for test	

	public static final String DB_USERNAME="root";
	
	public static final String DB_PASSWORD="123456";
	
	public static final String DB_URL_W="jdbc:mysql://localhost:3306/app_ibookclubserver";
	
	public static final String DB_URL_R="jdbc:mysql://localhost:3306/app_ibookclubserver";
	
//	public static final String DB_USERNAME=SaeUserInfo.getAccessKey();
	
//	public static final String DB_PASSWORD=SaeUserInfo.getSecretKey();
	
//	public static final String DB_URL_W="jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_ibookclubserver";
	
//	public static final String DB_URL_R="jdbc:mysql://r.rdc.sae.sina.com.cn:3307/app_ibookclubserver";
	
	public static final String DB_DRIVER="com.mysql.jdbc.Driver";
	
	public static final String SQL_INSERT_USERINFO="insert into userinfo(email,password,nickname,age,sex,interest)  values(?,?,?,?,?,?)";
	
	public static final String SQL_INSERT_BOOKINFO="insert into bookinfo(isbn,name,publisher,author,bookcover,summary,price)  values(?,?,?,?,?,?,?)";
	
	public static final String SQL_INSERT_BOOKOWNER="insert into bookowner(isbn,id,latitude,longitude,postTime,rating)  values(?,?,?,?,?,?)";
	
	public static final String SQL_SELECT_BOOKINFO="select * from bookinfo where isbn in(select isbn from bookowner);";
	
	public static final String SQL_CREATE="CREATE DATABASE IF NOT EXISTS `app_ibookclubserver`;"+
		
					"CREATE TABLE IF NOT EXISTS `bookinfo` ("+
					" `isbn` char(13) NOT NULL,"+
					" `name` varchar(128) NOT NULL,"+
					" `author` varchar(128) NOT NULL,"+
					" `publisher` varchar(128) NOT NULL,"+
					" `bookcover` varchar(128) DEFAULT NULL,"+
					" `summary` longtext,"+
					" `price` varchar(10) DEFAULT NULL,"+
					" PRIMARY KEY (`isbn`)"+
					") ENGINE=MyISAM DEFAULT CHARSET=utf8;"+
		 
					"CREATE TABLE IF NOT EXISTS `bookowner` ("+
					"`bookID` int(11) NOT NULL AUTO_INCREMENT,"+
					"`isbn` char(13) NOT NULL,"+
					"`id` int(8) NOT NULL,"+
					"`borrowId` int(8) DEFAULT NULL,"+
					"`borrowTime` date DEFAULT NULL,"+
					"`latitude` varchar(32) DEFAULT NULL,"+
					"`longitude` varchar(32) DEFAULT NULL,"+
					"`postTime` datetime NOT NULL,"+
					"`rating` int,"+
					"PRIMARY KEY (`bookID`,`isbn`),"+
					"KEY `postTime` (`postTime`)"+
					") ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;"+

		 			
					"CREATE TABLE IF NOT EXISTS `friends` ("+
					"`my_id` int(8) NOT NULL,"+
					"`friend_id` int(8) NOT NULL,"+
					"PRIMARY KEY (`my_id`)"+
					") ENGINE=MyISAM DEFAULT CHARSET=utf8;"+

		 			
		   			" CREATE TABLE IF NOT EXISTS `userinfo` ("+
		   			"`id` int(8) NOT NULL AUTO_INCREMENT,"+
		   			" `email` varchar(40) NOT NULL,"+
		   			" `password` varchar(20) NOT NULL,"+
		   			" `nickname` varchar(20) NOT NULL,"+
		   			" `picture` varchar(128) DEFAULT NULL,"+
		   			" `age` int(8) DEFAULT NULL,"+
		   			"`sex` char(1) DEFAULT NULL,"+
		   			"`interest` varchar(4) NOT NULL,"+
		   			" `registertime` datetime DEFAULT NULL,"+
		   			" PRIMARY KEY (`id`)"+
		   			") ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;";
}
