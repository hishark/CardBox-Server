package service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.UserRelationMapper;
import pojo.User;
import pojo.UserRelation;
import service.UserRelationService;

@Service
public class UserRelationServiceImp implements UserRelationService {

	@Autowired
	UserRelationMapper userRelationMapper;
	
	@Override
	public void AddUserRelation(UserRelation user_relation) {
		// TODO Auto-generated method stub
		userRelationMapper.AddUserRelation(user_relation);
	}

	@Override
	public void DeleteUserRelation(UserRelation user_relation) {
		// TODO Auto-generated method stub
		userRelationMapper.DeleteUserRelation(user_relation);
	}

	@Override
	public int GetFollowCount(String user_account) {
		// TODO Auto-generated method stub
		return userRelationMapper.GetFollowCount(user_account);
	}

	@Override
	public int GetFollowerCount(String user_account) {
		// TODO Auto-generated method stub
		return userRelationMapper.GetFollowerCount(user_account);
	}

	@Override
	public List<User> GetAllFollow(String user_account) {
		// TODO Auto-generated method stub
		return userRelationMapper.GetAllFollow(user_account);
	}

	@Override
	public List<User> GetAllFollower(String user_account) {
		// TODO Auto-generated method stub
		return userRelationMapper.GetAllFollower(user_account);
	}

}
