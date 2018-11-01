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
	 * 客户端 添加关注记录
	 * 
	 */
	//处理来自客户端的请求，并将json格式的数据处理结果返回。
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
	 * 客户端 删除关注记录
	 * 
	 */
	//处理来自客户端的请求，并将json格式的数据处理结果返回。
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
	 * 客户端
	 * 根据用户的账号查找关注数
	 */
	@RequestMapping("GetFollowCount")
    public void GetFollowCount(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //获取android客户端传递值的方式：在这里拿到需要插入用户的学号
        String user_account=request.getParameter("user_account");
        
        int count = userRelationService.GetFollowCount(user_account);
         
        jsonObject.put("FollowCount", count);
 
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
			
			byte[] bytes = jsonObject.toString().getBytes("utf-8");
			
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
	 * 客户端
	 * 根据用户的账号查找粉丝数
	 */
	@RequestMapping("GetFollowerCount")
    public void GetFollowerCount(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //获取android客户端传递值的方式：在这里拿到需要插入用户的学号
        String user_account=request.getParameter("user_account");
        
        int count = userRelationService.GetFollowerCount(user_account);
         
        jsonObject.put("FollowerCount", count);
 
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
			
			byte[] bytes = jsonObject.toString().getBytes("utf-8");
			
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
	 * 客户端
	 * 得到该用户关注的所有人
	 */
	@RequestMapping("GetAllFollow")
    public void GetAllFollow(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //获取android客户端传递值的方式：在这里拿到需要插入用户的学号
        String user_account=request.getParameter("user_account");
        System.out.println(user_account);
        List<User> users = userRelationService.GetAllFollow(user_account);
        JSONArray arrayUsers = JSONArray.fromObject(users);
        
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
			
			byte[] bytes = arrayUsers.toString().getBytes("utf-8");
			
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
	 * 客户端
	 * 得到该用户所有的粉丝
	 */
	@RequestMapping("GetAllFollower")
    public void GetAllFollower(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //获取android客户端传递值的方式：在这里拿到需要插入用户的学号
        String user_account=request.getParameter("user_account");
        System.out.println(user_account);
        List<User> users = userRelationService.GetAllFollower(user_account);
        JSONArray arrayUsers = JSONArray.fromObject(users);
        
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
			
			byte[] bytes = arrayUsers.toString().getBytes("utf-8");
			
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
