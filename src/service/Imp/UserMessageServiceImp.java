package service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.UserMessageMapper;
import pojo.UserMessage;
import service.UserMessageService;

@Service
public class UserMessageServiceImp implements UserMessageService {

	@Autowired
	UserMessageMapper userMessageMapper;
	
	
	@Override
	public void AddUserMessage(UserMessage user_message) {
		// TODO Auto-generated method stub
		userMessageMapper.AddUserMessage(user_message);
	}

}
