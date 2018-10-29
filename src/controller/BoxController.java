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
import service.BoxService;
import service.UserService;

@Controller
@RequestMapping("")
public class BoxController {
	
	@Autowired
	BoxService boxService;
	
	@Autowired
	UserService userService;
	
	/**
	 * 客户端，根据盒子名字以及类型查找出盒子！
	 */
	@RequestMapping("SearchBoxByType")
    public void SearchBoxByType(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //获取android客户端传递值的方式：在这里拿到需要插入用户的学号
        String box_name=request.getParameter("search_box_name");
        String search_type=request.getParameter("search_type");
        
        System.out.println(box_name);
        System.out.println(search_type);
        
        List<Box> boxList = new ArrayList<Box>();
        if (search_type.equals("所有")) {
        	boxList = boxService.GetBoxByType_All(box_name);
        } else if (search_type.equals("学习")) {
        	boxList = boxService.GetBoxByType_Study(box_name);
        } else if (search_type.equals("生活")) {
        	boxList = boxService.GetBoxByType_Life(box_name);
        } else if (search_type.equals("工作")) {
        	boxList = boxService.GetBoxByType_Work(box_name);
        } else if (search_type.equals("娱乐")) {
        	boxList = boxService.GetBoxByType_Entertainment(box_name);
        } 
        
        
        JSONArray arrayBox = JSONArray.fromObject(boxList);
        
        
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
			
			byte[] bytes = arrayBox.toString().getBytes("utf-8");
			
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
	 * 客户端，根据用户账号来查找出盒子！
	 */
	@RequestMapping("searchBoxByUserAccount")
    public void searchBoxByUserAccount(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //获取android客户端传递值的方式：在这里拿到需要插入用户的学号
        String user_account=request.getParameter("user_account");
        
        List<Box> boxList = new ArrayList<Box>();
        boxList = boxService.GetBoxByUserAccount(user_account);
        System.out.println(boxList.get(0).getBox_authority());
        JSONArray arrayBox = JSONArray.fromObject(boxList);
        System.out.println(boxList.get(0).getBox_create_time().toString());
        System.out.println(boxList.get(0).getBox_update_time().toString());
        
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
			
			byte[] bytes = arrayBox.toString().getBytes("utf-8");
			
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
	 * 客户端 添加卡盒
	 * 
	 */
	//处理来自客户端的请求，并将json格式的数据处理结果返回。
    @RequestMapping("AddBox")
    public void AddBox(HttpServletRequest request, HttpServletResponse response){
         
        String box_id=request.getParameter("box_id");
        String box_name=request.getParameter("box_name");
        String user_account = request.getParameter("user_account");
        String box_type = request.getParameter("box_type");
        String box_create_time = request.getParameter("box_create_time");
        String box_update_time = request.getParameter("box_update_time");
        String box_side = request.getParameter("box_side");
        String box_authority = request.getParameter("box_authority");
        
        
      
        User user = userService.User_GetByAccount(user_account);
        
        long time1 = Long.parseLong(box_create_time);
        Timestamp create_time = new Timestamp(time1);
        
        long time2 = Long.parseLong(box_update_time);
        Timestamp update_time = new Timestamp(time2);
      
        Box box = new Box();
        box.setBox_id(box_id);
        box.setBox_name(box_name);
        box.setUser(user);
        box.setBox_type(box_type);
        box.setBox_create_time(create_time);
        box.setBox_side(box_side);
        box.setBox_authority(box_authority);
        box.setBox_update_time(update_time);

        boxService.AddBox(box);
        
 
    }
    
    /**
	 * 更新卡盒信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("UpdateBox")
    public void UpdateBox(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
       
        String box_id=request.getParameter("box_id");
        String box_name = request.getParameter("box_name");
        String box_type = request.getParameter("box_type");
        //String box_update_time = request.getParameter("box_update_time");
        String box_authority = request.getParameter("box_authority");

        System.out.println(box_id);
        
        //long time1 = Long.parseLong(box_update_time);
        //Timestamp update_time = new Timestamp(time1);
      
        Box box = new Box();
        box.setBox_id(box_id);
        box.setBox_name(box_name);
        box.setBox_type(box_type);
        //box.setBox_update_time(update_time);
        box.setBox_authority(box_authority);
      
        
        boxService.UpdateBox(box);
    }
	
	
	/**
	 * 删除卡盒
	 * @param request
	 * @param response
	 */
	@RequestMapping("DeleteBox")
    public void DeleteBox(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
       
        String box_id=request.getParameter("box_id");
        System.out.println(box_id+"到这啦");

        boxService.DeleteBox(box_id);
    }

	
	/**
	 * 更新卡盒时间
	 * @param request
	 * @param response
	 */
	@RequestMapping("UpdateBoxTime")
    public void UpdateBoxTime(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
       
        String box_id=request.getParameter("box_id");
        String box_update_time = request.getParameter("box_update_time");

        System.out.println(box_update_time);
        
        long time1 = Long.parseLong(box_update_time);
        Timestamp update_time = new Timestamp(time1);
      
        Box box = new Box();
        box.setBox_id(box_id);
        box.setBox_update_time(update_time);
       
     
        boxService.UpdateBoxTime(box);
    }
	
	
	/**
	 * 客户端，查找出当前用户喜欢的盒子列表
	 */
	@RequestMapping("GetFavouriteBox")
    public void GetFavouriteBox(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //获取android客户端传递值的方式：在这里拿到需要插入用户的学号
        String user_account=request.getParameter("user_account");
 
        
        System.out.println(user_account);
 
        List<Box> boxList = new ArrayList<Box>();
        boxList = boxService.GetFavouriteBox(user_account);
        
        
        JSONArray arrayBox = JSONArray.fromObject(boxList);
        
        
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
			
			byte[] bytes = arrayBox.toString().getBytes("utf-8");
			
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
