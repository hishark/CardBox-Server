package controller;

import java.io.IOException;
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
import service.BoxService;

@Controller
@RequestMapping("")
public class BoxController {
	
	@Autowired
	BoxService boxService;
	
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

}
