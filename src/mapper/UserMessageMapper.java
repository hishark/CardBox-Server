package mapper;

import pojo.UserMessage;

public interface UserMessageMapper {
	//添加一条聊天记录
	public void AddUserMessage(UserMessage user_message);
}
