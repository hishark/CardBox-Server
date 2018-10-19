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

@Controller//����spring mvc����һ����������
@RequestMapping("")//�����û�������
public class UserController {
	//����Ҫnew��spring�Զ�ʵ�������ǵü���ע��
	@Autowired
	UserService userService;

	@RequestMapping("User_GetAll")
	public ModelAndView User_GetAll(){
		ModelAndView mv = new ModelAndView();
		List<User> allUser = userService.User_GetAll();
		//��cs����ҳ����ȥ
		//mv���ת���Ĳ�����Ҫת����JSP�����ݣ�
		mv.addObject("alluser",allUser);
		//���뽫Ҫ��ת����JSP�ļ���������Ҫ��׺���������ļ����ٽ�������
		mv.setViewName("UserCURD/UserList");
        //return��springMVC��� Ȼ��ͻ��Զ�ƥ����תҳ��
		System.out.println("�����ˣ�");
		return mv;
	}
	
	
	//JSPҳ�����ͨ��User_Add������Ӧ�ķ������ã���һ������Ĳ�������
	@RequestMapping("User_Add")
	public ModelAndView User_Add(User user) {
		ModelAndView mv = new ModelAndView();
		userService.User_Add(user);
		List<User> allUser = userService.User_GetAll();
		
		//��cs����ҳ����ȥ
		//mv���ת���Ĳ�����Ҫת����JSP�����ݣ�
		mv.addObject("alluser",allUser);
		
		//���뽫Ҫ��ת����JSP�ļ���������Ҫ��׺���������ļ����ٽ�������
		mv.setViewName("UserCURD/UserList");
		
		//return��springMVC��� Ȼ��ͻ��Զ�ƥ����תҳ��
	    return mv;
	}
	
	
	/**
	 * �ͻ��˽���
	 */
	
	/**
	 * �ͻ���
	 * ����һ���û���¼��
	 */
	@RequestMapping("Add_User")
    public void insertUser(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        System.out.println("��������");
        //��ȡandroid�ͻ��˴���ֵ�ķ�ʽ���������õ���Ҫ�����û���ѧ��
        String user_account=request.getParameter("user_account");
        String user_password=request.getParameter("user_password");
        User user=new User();
        user.setUser_account(user_account);
        user.setUser_password(user_password);
        user.setUser_avatar("");
        user.setUser_nickname("");
        userService.User_Add(user);
        
        
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
			jsonObject.put("UserAddSignal", "User_Add_Success");
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
	 * �����û����˺Ų����û�
	 */
	@RequestMapping("SearchUserByAccount")
    public void SearchUserByAccount(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //��ȡandroid�ͻ��˴���ֵ�ķ�ʽ���������õ���Ҫ�����û���ѧ��
        String user_account=request.getParameter("user_account");
        
        int count;
        User user = userService.User_GetByAccount(user_account);
        if(user!=null) {
        	System.out.println("catch you!");
        	count = 1;
        }else {
        	System.out.println("û�鵽�û�Ү");
        	count = 0;
        }
        jsonObject.put("UserSearchSignal", "User_Search_Success");
        jsonObject.put("SearchUserSum", count);
        jsonObject.put("SearchUser", user);
        
        
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
	 * �����û�ͷ��
	 * @param request
	 * @param response
	 */
	@RequestMapping("updateUserAvatar")
    public void updateUserAvatar(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
       
        //��ȡandroid�ͻ��˴���ֵ�ķ�ʽ���������õ���Ҫ�����û���ѧ��
        String user_account=request.getParameter("user_account");
        System.out.println(user_account);
        User user = userService.User_GetByAccount(user_account);
        
        String user_avatar=request.getParameter("user_avatar");
        user.setUser_avatar(user_avatar);
        
        userService.User_Update(user);
    }
	
	/**
	 * �����û��ǳ�
	 * @param request
	 * @param response
	 */
	@RequestMapping("updateUserNickname")
    public void updateUserNickname(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
       
        //��ȡandroid�ͻ��˴���ֵ�ķ�ʽ���������õ���Ҫ�����û���ѧ��
        String user_account=request.getParameter("user_account");
        System.out.println(user_account);
        User user = userService.User_GetByAccount(user_account);
        
        String user_nickname=request.getParameter("user_nickname");
        System.out.println(user_nickname);
        user.setUser_nickname(user_nickname);
        
        userService.User_Update(user);
    }
	
}
