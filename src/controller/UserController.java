package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;
import pojo.User;
import service.UserService;

@Controller//告诉spring mvc这是一个控制器类
@RequestMapping("")//接收用户的请求
public class UserController {
	//不需要new！spring自动实例化。记得加上注解
	@Autowired
	UserService userService;

	@RequestMapping("User_GetAll")
	public ModelAndView User_GetAll(){
		ModelAndView mv = new ModelAndView();
		List<User> allUser = userService.User_GetAll();
		//把cs带到页面上去
		//mv存放转发的参数（要转发到JSP的数据）
		mv.addObject("alluser",allUser);
		//放入将要跳转到的JSP文件名，不需要后缀名，配置文件中再进行配置
		mv.setViewName("UserCURD/UserList");
        //return给springMVC框架 然后就会自动匹配跳转页面
		System.out.println("到这了！");
		return mv;
	}
	
	
	//JSP页面就是通过User_Add进行相应的方法调用，是一个对外的操作名。
	@RequestMapping("User_Add")
	public ModelAndView User_Add(User user) {
		ModelAndView mv = new ModelAndView();
		userService.User_Add(user);
		List<User> allUser = userService.User_GetAll();
		
		//把cs带到页面上去
		//mv存放转发的参数（要转发到JSP的数据）
		mv.addObject("alluser",allUser);
		
		//放入将要跳转到的JSP文件名，不需要后缀名，配置文件中再进行配置
		mv.setViewName("UserCURD/UserList");
		
		//return给springMVC框架 然后就会自动匹配跳转页面
	    return mv;
	}
	
	
	/**
	 * 客户端交互
	 */
	
	/**
	 * 客户端
	 * 插入一条用户记录！
	 */
	@RequestMapping("Add_User")
    public void insertUser(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        System.out.println("进来啦！");
        //获取android客户端传递值的方式：在这里拿到需要插入用户的学号
        String user_account=request.getParameter("user_account");
        String user_password=request.getParameter("user_password");
        User user=new User();
        user.setUser_account(user_account);
        user.setUser_password(user_password);
        user.setUser_avatar("");
        user.setUser_nickname("");
        userService.User_Add(user);
        
        
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
			jsonObject.put("UserAddSignal", "User_Add_Success");
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
	 * 根据用户的账号查找用户
	 */
	@RequestMapping("SearchUserByAccount")
    public void SearchUserByAccount(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //获取android客户端传递值的方式：在这里拿到需要插入用户的学号
        String user_account=request.getParameter("user_account");
        
        int count;
        User user = userService.User_GetByAccount(user_account);
        if(user!=null) {
        	System.out.println("catch you!");
        	count = 1;
        }else {
        	System.out.println("没查到用户耶");
        	count = 0;
        }
        jsonObject.put("UserSearchSignal", "User_Search_Success");
        jsonObject.put("SearchUserSum", count);
        jsonObject.put("SearchUser", user);
        
        
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
	 * 更新用户头像
	 * @param request
	 * @param response
	 */
	@RequestMapping("updateUserAvatar")
    public void updateUserAvatar(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
       
        //获取android客户端传递值的方式：在这里拿到需要插入用户的学号
        String user_account=request.getParameter("user_account");
        System.out.println(user_account);
        User user = userService.User_GetByAccount(user_account);
        
        String user_avatar=request.getParameter("user_avatar");
        user.setUser_avatar(user_avatar);
        
        userService.User_Update(user);
    }
	
	/**
	 * 更新用户昵称
	 * @param request
	 * @param response
	 */
	@RequestMapping("updateUserNickname")
    public void updateUserNickname(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
       
        //获取android客户端传递值的方式：在这里拿到需要插入用户的学号
        String user_account=request.getParameter("user_account");
        System.out.println(user_account);
        User user = userService.User_GetByAccount(user_account);
        
        String user_nickname=request.getParameter("user_nickname");
        System.out.println(user_nickname);
        user.setUser_nickname(user_nickname);
        
        userService.User_Update(user);
    }
	
}
