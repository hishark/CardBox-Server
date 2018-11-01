package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.Box;
import pojo.BoxFavourite;
import pojo.User;
import pojo.UserRelation;
import service.UserRelationService;
import service.UserService;

@Controller
@RequestMapping("")
public class UserRelationController {
	
	@Autowired
	UserRelationService userRelationService;
	
	@Autowired
	UserService userService;
	
	/**
	 * �ͻ��� ��ӹ�ע��¼
	 * 
	 */
	//�������Կͻ��˵����󣬲���json��ʽ�����ݴ��������ء�
    @RequestMapping("AddUserRelation")
    public void AddUserRelation(HttpServletRequest request, HttpServletResponse response){
         
        String user_follow_account=request.getParameter("user_follow_account");
        String user_befollowed_account = request.getParameter("user_befollowed_account");
        String follow_time=request.getParameter("follow_time");
        
    
        User user_follow = userService.User_GetByAccount(user_follow_account);
        User user_befollowed = userService.User_GetByAccount(user_befollowed_account);
        
         
        
        long time1 = Long.parseLong(follow_time);
        Timestamp time = new Timestamp(time1);

        
        UserRelation userRelation = new UserRelation();
      
        userRelation.setFollow_time(time);
        userRelation.setUser_follow(user_follow);
        userRelation.setUser_befollowed(user_befollowed);
        
       
        userRelationService.AddUserRelation(userRelation);
 
    }
    
    
    /**
	 * �ͻ��� ɾ����ע��¼
	 * 
	 */
	//�������Կͻ��˵����󣬲���json��ʽ�����ݴ��������ء�
    @RequestMapping("DeleteUserRelation")
    public void DeleteUserRelation(HttpServletRequest request, HttpServletResponse response){
         
    	String user_follow_account=request.getParameter("user_follow_account");
        String user_befollowed_account = request.getParameter("user_befollowed_account");
        
        System.out.println(user_follow_account+","+user_befollowed_account);
        
        User user_follow = userService.User_GetByAccount(user_follow_account);
        User user_befollowed = userService.User_GetByAccount(user_befollowed_account);
     
        UserRelation userRelation = new UserRelation();
        

        userRelation.setUser_follow(user_follow);
        userRelation.setUser_befollowed(user_befollowed);
        
        userRelationService.DeleteUserRelation(userRelation);
 
    }
    
    
    /**
	 * �ͻ���
	 * �����û����˺Ų��ҹ�ע��
	 */
	@RequestMapping("GetFollowCount")
    public void GetFollowCount(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //��ȡandroid�ͻ��˴���ֵ�ķ�ʽ���������õ���Ҫ�����û���ѧ��
        String user_account=request.getParameter("user_account");
        
        int count = userRelationService.GetFollowCount(user_account);
         
        jsonObject.put("FollowCount", count);
 
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
			
			byte[] bytes = jsonObject.toString().getBytes("utf-8");
			
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
	 * �ͻ���
	 * �����û����˺Ų��ҷ�˿��
	 */
	@RequestMapping("GetFollowerCount")
    public void GetFollowerCount(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //��ȡandroid�ͻ��˴���ֵ�ķ�ʽ���������õ���Ҫ�����û���ѧ��
        String user_account=request.getParameter("user_account");
        
        int count = userRelationService.GetFollowerCount(user_account);
         
        jsonObject.put("FollowerCount", count);
 
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
			
			byte[] bytes = jsonObject.toString().getBytes("utf-8");
			
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
	 * �ͻ���
	 * �õ����û���ע��������
	 */
	@RequestMapping("GetAllFollow")
    public void GetAllFollow(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //��ȡandroid�ͻ��˴���ֵ�ķ�ʽ���������õ���Ҫ�����û���ѧ��
        String user_account=request.getParameter("user_account");
        System.out.println(user_account);
        List<User> users = userRelationService.GetAllFollow(user_account);
        JSONArray arrayUsers = JSONArray.fromObject(users);
        
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
			
			byte[] bytes = arrayUsers.toString().getBytes("utf-8");
			
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
	 * �ͻ���
	 * �õ����û����еķ�˿
	 */
	@RequestMapping("GetAllFollower")
    public void GetAllFollower(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //��ȡandroid�ͻ��˴���ֵ�ķ�ʽ���������õ���Ҫ�����û���ѧ��
        String user_account=request.getParameter("user_account");
        System.out.println(user_account);
        List<User> users = userRelationService.GetAllFollower(user_account);
        JSONArray arrayUsers = JSONArray.fromObject(users);
        
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
			
			byte[] bytes = arrayUsers.toString().getBytes("utf-8");
			
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
