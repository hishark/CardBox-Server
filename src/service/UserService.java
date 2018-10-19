package service;

import java.util.List;

import pojo.User;

public interface UserService {
	//得到所有用户
	public List<User> User_GetAll();

	//根据账号查找用户
	public User User_GetByAccount(String user_account);
	
	//添加用户
	public void User_Add(User user);
	
	//更新用户信息
	public void User_Update(User user);
}
