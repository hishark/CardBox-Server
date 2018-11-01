package service;

import java.util.List;

import pojo.User;
import pojo.UserRelation;

public interface UserRelationService {
	//插入一条关注记录
	public void AddUserRelation(UserRelation user_relation);
	
	//删除一条关注记录
	public void DeleteUserRelation(UserRelation user_relation);
	
	//得到当前查看用户的关注数
	public int GetFollowCount(String user_account);
	
	//得到当前查看用户的粉丝数
	public int GetFollowerCount(String user_account);
	
	//得到当前查看用户关注的所有人
	public List<User> GetAllFollow(String user_account);
	
	//得到当前查看用户的所有粉丝
	public List<User> GetAllFollower(String user_account);
}
