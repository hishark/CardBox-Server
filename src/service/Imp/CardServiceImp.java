package service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.CardMapper;
import pojo.Card;
import service.CardService;
/* Serviceע��
 * ����spring����һ��ʵ���ࣨ��BookService��ʵ���ࣩ
 * ���Բ���Ҫ�Լ�ʵ������
 */
@Service
public class CardServiceImp implements CardService {

	/* ֻ��Ҫ����ӿڵ����ñ���
	 * spring����ע�����ע��
	 * spring��ܿ�ʵ���Զ�ʵ����
	 */
	@Autowired
	CardMapper cardMapper;
	
	@Override
	public List<Card> GetCardByBoxId(String box_id) {
		// TODO Auto-generated method stub
		return cardMapper.GetCardByBoxId(box_id);
	}

	@Override
	public Card GetCardByCardId(String card_id) {
		// TODO Auto-generated method stub
		return cardMapper.GetCardByCardId(card_id);
	}

	@Override
	public void UpdateCardMarktypeDone(String card_id) {
		// TODO Auto-generated method stub
		cardMapper.UpdateCardMarktypeDone(card_id);
	}

	@Override
	public void UpdateCardMarktypeUnDone(String card_id) {
		// TODO Auto-generated method stub
		cardMapper.UpdateCardMarktypeUnDone(card_id);
	}

	@Override
	public void DeleteCardById(String card_id) {
		// TODO Auto-generated method stub
		cardMapper.DeleteCardById(card_id);
	}

	@Override
	public void UpdateCard(Card card) {
		// TODO Auto-generated method stub
		cardMapper.UpdateCard(card);
	}

	@Override
	public void AddCard(Card card) {
		// TODO Auto-generated method stub
		cardMapper.AddCard(card);
	}

}
