package com.shining.ibookclubserver.dao;

import com.shining.ibookclubserver.bean.UserBean;

public interface UserMapper {

	public UserBean	getUserinfo(String email);
	public void regist(UserBean userBean);
}
