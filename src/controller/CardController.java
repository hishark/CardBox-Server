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
import pojo.Card;
import pojo.User;
import service.BoxService;
import service.CardService;

@Controller
@RequestMapping("")
public class CardController {
	@Autowired
	CardService cardService;
	
	@Autowired
	BoxService boxService;
	
	/**
	 * �ͻ��ˣ����ݺ���ID�����ҳ����п�Ƭ��
	 */
	@RequestMapping("SearchCardByBoxID")
    public void SearchCardByBoxID(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //��ȡandroid�ͻ��˴���ֵ�ķ�ʽ���������õ���Ҫ�����û���ѧ��
        String box_id=request.getParameter("box_id");
        
        List<Card> cardList = new ArrayList<Card>();
        cardList = cardService.GetCardByBoxId(box_id);
 
        JSONArray arrayCard = JSONArray.fromObject(cardList);
        System.out.println(arrayCard);
        System.out.println(cardList.get(0).getCard_front().toString());
        System.out.println(cardList.get(0).getCard_back().toString());

        
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
			
			byte[] bytes = arrayCard.toString().getBytes("utf-8");
			
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
	 * �ͻ��ˣ����ݺ���ID�����ҳ����ӣ�(��д�Ż�û�ù�������������)
	 */
	@RequestMapping("GetCardByCardId")
    public void GetCard_ByCardId(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //��ȡandroid�ͻ��˴���ֵ�ķ�ʽ���������õ���Ҫ�����û���ѧ��
        String card_id=request.getParameter("card_id");
        
        Card card = cardService.GetCardByCardId(card_id);
 
        jsonObject.put("SearchCard", card);

        
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
	 * ���¿�Ƭ�����Ϣ
	 * @param request
	 * @param response
	 */
	@RequestMapping("UpdateCardMarktype")
    public void UpdateCard_Marktype(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
       
        String card_marktype=request.getParameter("card_marktype");
        String card_id=request.getParameter("card_id");

        System.out.println(card_id+","+card_marktype);
        
        if(card_marktype.equals("δ���")) {
        	cardService.UpdateCardMarktypeUnDone(card_id);
        } else {
        	cardService.UpdateCardMarktypeDone(card_id);
        }
      
    }
	
	/**
	 * ɾ����Ƭ
	 * @param request
	 * @param response
	 */
	@RequestMapping("DeleteCardById")
    public void DeleteCardById(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
       
     
        String card_id=request.getParameter("card_id");

        System.out.println(card_id);
        
        cardService.DeleteCardById(card_id);
      
    }
	
	/**
	 * ���¿�Ƭ��Ϣ
	 * @param request
	 * @param response
	 */
	@RequestMapping("UpdateCard")
    public void UpdateCard(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
       
        String card_front=request.getParameter("card_front");
        String card_back = request.getParameter("card_back");
        String card_id=request.getParameter("card_id");

        System.out.println(card_id);
        
        Card card = cardService.GetCardByCardId(card_id);
        card.setCard_back(card_back);
        card.setCard_front(card_front);
        
        cardService.UpdateCard(card);
    }
	
	/**
	 * �ͻ��� ��ӿ�Ƭ
	 * 
	 */
	//�������Կͻ��˵����󣬲���json��ʽ�����ݴ��������ء�
    @RequestMapping("AddCard")
    public void AddCard(HttpServletRequest request, HttpServletResponse response){
         
        String card_id=request.getParameter("card_id");
        String box_id=request.getParameter("box_id");
        String card_type = request.getParameter("card_type");
        String card_front = request.getParameter("card_front");
        String card_back = request.getParameter("card_back");
        String card_create_time = request.getParameter("card_create_time");
        String card_marktype = request.getParameter("card_marktype");
         
        Box box = boxService.GetBoxById(box_id);
         
        System.out.println(card_id);
        long time1 = Long.parseLong(card_create_time);
        Timestamp create_time = new Timestamp(time1);
        
        Card card = new Card();
        card.setBox(box);
        card.setCard_back(card_back);
        card.setCard_front(card_front);
        card.setCard_create_time(create_time);
        card.setCard_id(card_id);
        card.setCard_type(card_type);
        card.setCard_marktype(card_marktype);
        

        cardService.AddCard(card);
        
 
    }
}
