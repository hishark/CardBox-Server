package service;

import java.util.List;

import pojo.User;
import pojo.UserMessage;

public interface UserMessageService {
	//添加一条聊天记录
	public void AddUserMessage(UserMessage user_message);
	
	//得到该用户发出的所有信息
	public List<UserMessage> GetSendMessageByUserAcocunt(String user_account);

	//得到该用户收到的所有信息
	public List<UserMessage> GetReceiveMessageByUserAcocunt(String user_account);
}
