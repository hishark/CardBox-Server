package service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.UserMapper;
import pojo.User;
import service.UserService;

/* Service注解
 * 告诉spring这是一个实现类（是BookService的实现类）
 * 所以不需要自己实例化啦
 */
@Service
public class UserServiceImp implements UserService {

	/* 只需要定义接口的引用变量
	 * spring利用注解进行注入
	 * spring框架可实现自动实例化
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
