package mapper;

import pojo.UserRelation;

public interface UserRelationMapper {
	//����һ����ע��¼
	public void AddUserRelation(UserRelation user_relation);
	
	//ɾ��һ����ע��¼
	public void DeleteUserRelation(UserRelation user_relation);
	
	//�õ���ǰ�鿴�û��Ĺ�ע��
	public int GetFollowCount(String user_account);
	
	//�õ���ǰ�鿴�û��ķ�˿��
	public int GetFollowerCount(String user_account);
}
