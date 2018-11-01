package service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.UserMessageMapper;
import pojo.User;
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


	@Override
	public List<UserMessage> GetSendMessageByUserAcocunt(String user_account) {
		// TODO Auto-generated method stub
		return userMessageMapper.GetSendMessageByUserAcocunt(user_account);
	}


	@Override
	public List<UserMessage> GetReceiveMessageByUserAcocunt(String user_account) {
		// TODO Auto-generated method stub
		return userMessageMapper.GetReceiveMessageByUserAcocunt(user_account);
	}


	

}
