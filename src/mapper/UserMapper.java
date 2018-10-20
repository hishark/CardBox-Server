package mapper;

import java.util.List;

import pojo.User;
//说明对数据库中表的相关操作
//可以提供给用户的服务
public interface UserMapper {
	//得到所有图书
	public List<User> User_GetAll();
	
	//根据账号查找用户
	public User User_GetByAccount(String user_account);

	//根据关键词查找用户（查找类型为用户昵称）
	public List<User> User_SearchAllByNickname(String keyword);
	
	//根据关键词查找用户（查找类型为用户账号）
	public List<User> User_SearchAllByAccount(String keyword);
		
	
	//添加用户
	public void User_Add(User user);
	
	//更新用户信息
	public void User_Update(User user);//
	
	
}
