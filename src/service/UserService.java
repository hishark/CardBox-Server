package service;

import java.util.List;

import pojo.User;

public interface UserService {
	//�õ������û�
	public List<User> User_GetAll();

	//�����˺Ų����û�
	public User User_GetByAccount(String user_account);
	
	//����û�
	public void User_Add(User user);
	
	//�����û���Ϣ
	public void User_Update(User user);
}
