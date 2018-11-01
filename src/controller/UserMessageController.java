package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.Box;
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
        
    
        System.out.println(message_content);
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
    
    
    /**
	 * �ͻ��ˣ����ҳ���ǰ�û�������������Ϣ
	 */
	@RequestMapping("GetSendMessage")
    public void GetSendMessage(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //��ȡandroid�ͻ��˴���ֵ�ķ�ʽ���������õ���Ҫ�����û��˺�
        String user_account=request.getParameter("user_account");
 
        
        System.out.println(user_account);
 
        List<UserMessage> msgList = new ArrayList<UserMessage>();
        msgList = userMessageService.GetSendMessageByUserAcocunt(user_account);
        
        
        JSONArray arraySendMsg = JSONArray.fromObject(msgList);
        
        
        /*
		 * �ȷ�װ��text���ַ��� ��ת����JSON
		 * ��׿Activity��������ʾֻ��UTF-8��GBK������Ŷ
		 * ������ָ�ڿͻ�����ʾ�ı����ʽ
		 */
		response.setContentType("text/json;charset=utf-8");
		
		/*
		 * ������ָ�����紫������еı��뷽ʽ
		 */
		response.setCharacterEncoding("utf-8");
		
		/*
		 * �ùܵ�����������
		 * ��JSONת����byte�ٴ�
		 * ���뷽ʽUTF-8��
		 */
		try {
			
			byte[] bytes = arraySendMsg.toString().getBytes("utf-8");
			
			//���ֽ�����д�������
			response.getOutputStream().write(bytes);
			
			//���ô������ݵĳ��ȣ�����response����
			response.setContentLength(bytes.length);
			
			//��ջ��棨�ѻ������ȫ������ȥ
			response.getOutputStream().flush();
			
			//����Ҫ����
			response.getOutputStream().close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

    }
	
	
	/**
	 * �ͻ��ˣ����ҳ���ǰ�û��յ���������Ϣ
	 */
	@RequestMapping("GetReceiveMessage")
    public void GetReceiveMessage(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //��ȡandroid�ͻ��˴���ֵ�ķ�ʽ���������õ���Ҫ�����û��˺�
        String user_account=request.getParameter("user_account");
 
        
        System.out.println(user_account);
 
        List<UserMessage> msgList = new ArrayList<UserMessage>();
        msgList = userMessageService.GetReceiveMessageByUserAcocunt(user_account);
        
        
        JSONArray arrayrcvMsg = JSONArray.fromObject(msgList);
        
        
        /*
		 * �ȷ�װ��text���ַ��� ��ת����JSON
		 * ��׿Activity��������ʾֻ��UTF-8��GBK������Ŷ
		 * ������ָ�ڿͻ�����ʾ�ı����ʽ
		 */
		response.setContentType("text/json;charset=utf-8");
		
		/*
		 * ������ָ�����紫������еı��뷽ʽ
		 */
		response.setCharacterEncoding("utf-8");
		
		/*
		 * �ùܵ�����������
		 * ��JSONת����byte�ٴ�
		 * ���뷽ʽUTF-8��
		 */
		try {
			
			byte[] bytes = arrayrcvMsg.toString().getBytes("utf-8");
			
			//���ֽ�����д�������
			response.getOutputStream().write(bytes);
			
			//���ô������ݵĳ��ȣ�����response����
			response.setContentLength(bytes.length);
			
			//��ջ��棨�ѻ������ȫ������ȥ
			response.getOutputStream().flush();
			
			//����Ҫ����
			response.getOutputStream().close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

    }

}
