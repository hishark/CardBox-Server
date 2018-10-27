package service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.CardMapper;
import pojo.Card;
import service.CardService;
/* Service注解
 * 告诉spring这是一个实现类（是BookService的实现类）
 * 所以不需要自己实例化啦
 */
@Service
public class CardServiceImp implements CardService {

	/* 只需要定义接口的引用变量
	 * spring利用注解进行注入
	 * spring框架可实现自动实例化
	 */
	@Autowired
	CardMapper cardMapper;
	
	@Override
	public List<Card> GetCardByBoxId(String box_id) {
		// TODO Auto-generated method stub
		return cardMapper.GetCardByBoxId(box_id);
	}

}
