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

@Controller//告诉spring mvc这是一个控制器类
@RequestMapping("")//接收用户的请求
public class UserMessageController {
	
	@Autowired
	UserMessageService userMessageService;
	
	@Autowired
	UserService userService;
	
	/**
	 * 客户端 添加一条聊天记录
	 * 
	 */
	//处理来自客户端的请求，并将json格式的数据处理结果返回。
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
	 * 客户端，查找出当前用户发出的所有信息
	 */
	@RequestMapping("GetSendMessage")
    public void GetSendMessage(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //获取android客户端传递值的方式：在这里拿到需要插入用户账号
        String user_account=request.getParameter("user_account");
 
        
        System.out.println(user_account);
 
        List<UserMessage> msgList = new ArrayList<UserMessage>();
        msgList = userMessageService.GetSendMessageByUserAcocunt(user_account);
        
        
        JSONArray arraySendMsg = JSONArray.fromObject(msgList);
        
        
        /*
		 * 先封装成text即字符串 再转换成JSON
		 * 安卓Activity的中文显示只认UTF-8！GBK不可以哦
		 * 这里是指在客户端显示的编码格式
		 */
		response.setContentType("text/json;charset=utf-8");
		
		/*
		 * 这里是指在网络传输过程中的编码方式
		 */
		response.setCharacterEncoding("utf-8");
		
		/*
		 * 用管道流传东西啦
		 * 把JSON转换成byte再传
		 * 编码方式UTF-8！
		 */
		try {
			
			byte[] bytes = arraySendMsg.toString().getBytes("utf-8");
			
			//把字节数组写入输出流
			response.getOutputStream().write(bytes);
			
			//设置传输内容的长度，方便response处理
			response.setContentLength(bytes.length);
			
			//清空缓存（把缓存里的全部发出去
			response.getOutputStream().flush();
			
			//用完要关啦
			response.getOutputStream().close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

    }
	
	
	/**
	 * 客户端，查找出当前用户收到的所有信息
	 */
	@RequestMapping("GetReceiveMessage")
    public void GetReceiveMessage(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //获取android客户端传递值的方式：在这里拿到需要插入用户账号
        String user_account=request.getParameter("user_account");
 
        
        System.out.println(user_account);
 
        List<UserMessage> msgList = new ArrayList<UserMessage>();
        msgList = userMessageService.GetReceiveMessageByUserAcocunt(user_account);
        
        
        JSONArray arrayrcvMsg = JSONArray.fromObject(msgList);
        
        
        /*
		 * 先封装成text即字符串 再转换成JSON
		 * 安卓Activity的中文显示只认UTF-8！GBK不可以哦
		 * 这里是指在客户端显示的编码格式
		 */
		response.setContentType("text/json;charset=utf-8");
		
		/*
		 * 这里是指在网络传输过程中的编码方式
		 */
		response.setCharacterEncoding("utf-8");
		
		/*
		 * 用管道流传东西啦
		 * 把JSON转换成byte再传
		 * 编码方式UTF-8！
		 */
		try {
			
			byte[] bytes = arrayrcvMsg.toString().getBytes("utf-8");
			
			//把字节数组写入输出流
			response.getOutputStream().write(bytes);
			
			//设置传输内容的长度，方便response处理
			response.setContentLength(bytes.length);
			
			//清空缓存（把缓存里的全部发出去
			response.getOutputStream().flush();
			
			//用完要关啦
			response.getOutputStream().close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

    }

}
