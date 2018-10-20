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

}
