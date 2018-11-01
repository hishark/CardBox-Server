package service;

import java.util.List;

import pojo.User;
import pojo.UserRelation;

public interface UserRelationService {
	//����һ����ע��¼
	public void AddUserRelation(UserRelation user_relation);
	
	//ɾ��һ����ע��¼
	public void DeleteUserRelation(UserRelation user_relation);
	
	//�õ���ǰ�鿴�û��Ĺ�ע��
	public int GetFollowCount(String user_account);
	
	//�õ���ǰ�鿴�û��ķ�˿��
	public int GetFollowerCount(String user_account);
	
	//�õ���ǰ�鿴�û���ע��������
	public List<User> GetAllFollow(String user_account);
	
	//�õ���ǰ�鿴�û������з�˿
	public List<User> GetAllFollower(String user_account);
}
