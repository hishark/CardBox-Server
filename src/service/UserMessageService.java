package service;

import java.util.List;

import pojo.User;
import pojo.UserMessage;

public interface UserMessageService {
	//���һ�������¼
	public void AddUserMessage(UserMessage user_message);
	
	//�õ����û�������������Ϣ
	public List<UserMessage> GetSendMessageByUserAcocunt(String user_account);

	//�õ����û��յ���������Ϣ
	public List<UserMessage> GetReceiveMessageByUserAcocunt(String user_account);
}
