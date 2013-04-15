package com.shining.ibookclubserver.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import java.util.Properties;
import java.util.PropertyResourceBundle;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.shining.ibookclubserver.bean.BookBean;
import com.shining.ibookclubserver.bean.TimelineBean;
import com.shining.ibookclubserver.bean.UserBean;
import com.sina.sae.util.SaeUserInfo;

public abstract class Dao {
	
	protected SqlSessionFactory factory;
	
	public static String SERVER_URL;
	
	public Dao(){
		
		 String resource = "SQLMapConfig.xml";
	     Reader reader;
	     SqlSessionFactoryBuilder builder;
	     try {
	    	 
	    	  Properties prop = new Properties();  
	    	 
		      InputStream fis = Dao.class.getClassLoader().getResourceAsStream("SQLMapConfig.properties");
		         
		      prop.load(fis);
		     
		      SERVER_URL=prop.getProperty("url");
		     
		      fis.close();
		  
	    	  reader = Resources.getResourceAsReader(resource);
	    	  builder = new SqlSessionFactoryBuilder();
			  factory = builder.build(reader);
	     
	     }catch(Exception e) {
				
			e.printStackTrace();
	     }
	}
}
