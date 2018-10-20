package service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.UserMapper;
import pojo.User;
import service.UserService;

/* Serviceע��
 * ����spring����һ��ʵ���ࣨ��BookService��ʵ���ࣩ
 * ���Բ���Ҫ�Լ�ʵ������
 */
@Service
public class UserServiceImp implements UserService {

	/* ֻ��Ҫ����ӿڵ����ñ���
	 * spring����ע�����ע��
	 * spring��ܿ�ʵ���Զ�ʵ����
	 */
	@Autowired
	UserMapper userMapper;
	
	@Override
	public void User_Add(User user) {
		userMapper.User_Add(user);
	}

	@Override
	public List<User> User_GetAll() {
		return userMapper.User_GetAll();
	}

	@Override
	public User User_GetByAccount(String user_account) {
		User user = userMapper.User_GetByAccount(user_account);
		return user;
	}

	@Override
	public void User_Update(User user) {
		// TODO Auto-generated method stub
		userMapper.User_Update(user);
	}

	@Override
	public List<User> User_SearchAllByNickname(String keyword) {
		// TODO Auto-generated method stub
		return userMapper.User_SearchAllByNickname(keyword);
	}

	@Override
	public List<User> User_SearchAllByAccount(String keyword) {
		// TODO Auto-generated method stub
		return userMapper.User_SearchAllByAccount(keyword);
	}

}
