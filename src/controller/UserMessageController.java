package controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pojo.User;
import pojo.UserMessage;
import service.UserMessageService;
import service.UserService;

@Controller//����spring mvc����һ����������
@RequestMapping("")//�����û�������
public class UserMessageController {
	
	@Autowired
	UserMessageService userMessageService;
	
	@Autowired
	UserService userService;
	
	/**
	 * �ͻ��� ���һ�������¼
	 * 
	 */
	//�������Կͻ��˵����󣬲���json��ʽ�����ݴ��������ء�
    @RequestMapping("AddUserMessage")
    public void AddUserMessage(HttpServletRequest request, HttpServletResponse response){
         
        String user_sender_account=request.getParameter("user_sender_account");
        String user_receiver_account = request.getParameter("user_receiver_account");
        String message_send_time=request.getParameter("message_send_time");
        String message_content = request.getParameter("message_content");
        
    
        User user_sender = userService.User_GetByAccount(user_sender_account);
        User user_receiver = userService.User_GetByAccount(user_receiver_account);
        
         
        
        long time1 = Long.parseLong(message_send_time);
        Timestamp time = new Timestamp(time1);

        
        UserMessage userMessage = new UserMessage();
        
        userMessage.setMessage_content(message_content);
        userMessage.setMessage_send_time(time);
        userMessage.setUser_receiver(user_receiver);
        userMessage.setUser_sender(user_sender);
       
        userMessageService.AddUserMessage(userMessage);
 
    }

}
