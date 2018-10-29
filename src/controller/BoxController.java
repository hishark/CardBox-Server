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
	 * �ͻ��ˣ����ݺ��������Լ����Ͳ��ҳ����ӣ�
	 */
	@RequestMapping("SearchBoxByType")
    public void SearchBoxByType(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //��ȡandroid�ͻ��˴���ֵ�ķ�ʽ���������õ���Ҫ�����û���ѧ��
        String box_name=request.getParameter("search_box_name");
        String search_type=request.getParameter("search_type");
        
        System.out.println(box_name);
        System.out.println(search_type);
        
        List<Box> boxList = new ArrayList<Box>();
        if (search_type.equals("����")) {
        	boxList = boxService.GetBoxByType_All(box_name);
        } else if (search_type.equals("ѧϰ")) {
        	boxList = boxService.GetBoxByType_Study(box_name);
        } else if (search_type.equals("����")) {
        	boxList = boxService.GetBoxByType_Life(box_name);
        } else if (search_type.equals("����")) {
        	boxList = boxService.GetBoxByType_Work(box_name);
        } else if (search_type.equals("����")) {
        	boxList = boxService.GetBoxByType_Entertainment(box_name);
        } 
        
        
        JSONArray arrayBox = JSONArray.fromObject(boxList);
        
        
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
			
			byte[] bytes = arrayBox.toString().getBytes("utf-8");
			
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
	 * �ͻ��ˣ������û��˺������ҳ����ӣ�
	 */
	@RequestMapping("searchBoxByUserAccount")
    public void searchBoxByUserAccount(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //��ȡandroid�ͻ��˴���ֵ�ķ�ʽ���������õ���Ҫ�����û���ѧ��
        String user_account=request.getParameter("user_account");
        
        List<Box> boxList = new ArrayList<Box>();
        boxList = boxService.GetBoxByUserAccount(user_account);
        System.out.println(boxList.get(0).getBox_authority());
        JSONArray arrayBox = JSONArray.fromObject(boxList);
        System.out.println(boxList.get(0).getBox_create_time().toString());
        System.out.println(boxList.get(0).getBox_update_time().toString());
        
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
			
			byte[] bytes = arrayBox.toString().getBytes("utf-8");
			
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
	 * �ͻ��� ��ӿ���
	 * 
	 */
	//�������Կͻ��˵����󣬲���json��ʽ�����ݴ��������ء�
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
	 * ���¿�����Ϣ
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
	 * ɾ������
	 * @param request
	 * @param response
	 */
	@RequestMapping("DeleteBox")
    public void DeleteBox(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
       
        String box_id=request.getParameter("box_id");
        System.out.println(box_id+"������");

        boxService.DeleteBox(box_id);
    }

	
	/**
	 * ���¿���ʱ��
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
	 * �ͻ��ˣ����ҳ���ǰ�û�ϲ���ĺ����б�
	 */
	@RequestMapping("GetFavouriteBox")
    public void GetFavouriteBox(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //��ȡandroid�ͻ��˴���ֵ�ķ�ʽ���������õ���Ҫ�����û���ѧ��
        String user_account=request.getParameter("user_account");
 
        
        System.out.println(user_account);
 
        List<Box> boxList = new ArrayList<Box>();
        boxList = boxService.GetFavouriteBox(user_account);
        
        
        JSONArray arrayBox = JSONArray.fromObject(boxList);
        
        
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
			
			byte[] bytes = arrayBox.toString().getBytes("utf-8");
			
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
