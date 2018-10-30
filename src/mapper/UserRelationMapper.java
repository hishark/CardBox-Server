package mapper;

import pojo.UserRelation;

public interface UserRelationMapper {
	//插入一条关注记录
	public void AddUserRelation(UserRelation user_relation);
	
	//删除一条关注记录
	public void DeleteUserRelation(UserRelation user_relation);
	
	//得到当前查看用户的关注数
	public int GetFollowCount(String user_account);
	
	//得到当前查看用户的粉丝数
	public int GetFollowerCount(String user_account);
}
