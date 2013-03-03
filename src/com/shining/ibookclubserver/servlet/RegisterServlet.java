package com.shining.ibookclubserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.shining.ibookclubserver.UserBean;
import com.shining.ibookclubserver.dao.BookDao;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
	
		String email =request.getParameter("email");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		UserBean userBean=new UserBean();
		userBean.setEmail(email);
		userBean.setPassWord(password);
		userBean.setNickName(nickname);
		BookDao dao=BookDao.getInstance();
		
		JSONObject jsonObj= new JSONObject();
		try{
		  dao.setUserBean(userBean);
		
		 if(dao.regist())
		  	jsonObj.put("ActionResult", true);
		 else
		  	jsonObj.put("ActionResult", false);
		  PrintWriter out = response.getWriter();
		  out.print(jsonObj.toString());
		  out.flush();
		  out.close();
		}
		  catch(Exception e){
			  e.printStackTrace();
		}

		
	}

}
