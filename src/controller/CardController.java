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
	 * 客户端，根据盒子ID来查找出所有卡片！
	 */
	@RequestMapping("SearchCardByBoxID")
    public void SearchCardByBoxID(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //获取android客户端传递值的方式：在这里拿到需要插入用户的学号
        String box_id=request.getParameter("box_id");
        
        List<Card> cardList = new ArrayList<Card>();
        cardList = cardService.GetCardByBoxId(box_id);
 
        JSONArray arrayCard = JSONArray.fromObject(cardList);
        System.out.println(arrayCard);
        System.out.println(cardList.get(0).getCard_front().toString());
        System.out.println(cardList.get(0).getCard_back().toString());

        
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
			
			byte[] bytes = arrayCard.toString().getBytes("utf-8");
			
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
	 * 客户端，根据盒子ID来查找出盒子！(先写着还没用过，出错了再找)
	 */
	@RequestMapping("GetCardByCardId")
    public void GetCard_ByCardId(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        //获取android客户端传递值的方式：在这里拿到需要插入用户的学号
        String card_id=request.getParameter("card_id");
        
        Card card = cardService.GetCardByCardId(card_id);
 
        jsonObject.put("SearchCard", card);

        
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
	 * 更新卡片标记信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("UpdateCardMarktype")
    public void UpdateCard_Marktype(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
       
        String card_marktype=request.getParameter("card_marktype");
        String card_id=request.getParameter("card_id");

        System.out.println(card_id+","+card_marktype);
        
        if(card_marktype.equals("未标记")) {
        	cardService.UpdateCardMarktypeUnDone(card_id);
        } else {
        	cardService.UpdateCardMarktypeDone(card_id);
        }
      
    }
	
	/**
	 * 删除卡片
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
	 * 更新卡片信息
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
	 * 客户端 添加卡片
	 * 
	 */
	//处理来自客户端的请求，并将json格式的数据处理结果返回。
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
