package controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pojo.Box;
import pojo.BoxFavourite;
import pojo.User;
import service.BoxFavouriteService;
import service.BoxService;
import service.UserService;

@Controller
@RequestMapping("")
public class BoxFavouriteController {
	@Autowired
	BoxFavouriteService boxFavouriteService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	BoxService boxService;
	
	/**
	 * �ͻ��� ���ϲ����¼
	 * 
	 */
	//�������Կͻ��˵����󣬲���json��ʽ�����ݴ��������ء�
    @RequestMapping("AddBoxFavourite")
    public void AddBoxFavourite(HttpServletRequest request, HttpServletResponse response){
         
        String box_id=request.getParameter("box_id");
        String user_account = request.getParameter("user_account");
        String favourite_time=request.getParameter("favourite_time");
        
    
        User user = userService.User_GetByAccount(user_account);
        Box box = boxService.GetBoxById(box_id);
        
        long time1 = Long.parseLong(favourite_time);
        Timestamp time = new Timestamp(time1);

        
        BoxFavourite boxFavourite = new BoxFavourite();
      
        boxFavourite.setUser(user);
        boxFavourite.setBox(box);
        boxFavourite.setFavourite_time(time);
       
        boxFavouriteService.AddBoxFavourite(boxFavourite);
 
    }
    
    
    /**
	 * �ͻ��� ɾ��ϲ����¼
	 * 
	 */
	//�������Կͻ��˵����󣬲���json��ʽ�����ݴ��������ء�
    @RequestMapping("DeleteFavouriteBox")
    public void DeleteFavouriteBox(HttpServletRequest request, HttpServletResponse response){
         
        String box_id=request.getParameter("box_id");
        String user_account = request.getParameter("user_account");

        System.out.println(box_id+","+user_account);
        
        User user = userService.User_GetByAccount(user_account);
        Box box = boxService.GetBoxById(box_id);
        
     
        BoxFavourite boxFavourite = new BoxFavourite();
      
        boxFavourite.setUser(user);
        boxFavourite.setBox(box);
       
        boxFavouriteService.DeleteBoxFavourite(boxFavourite);
 
    }
	
}
