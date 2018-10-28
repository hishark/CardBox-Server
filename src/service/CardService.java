package service;

import java.util.List;

import pojo.Card;

public interface CardService {
	public List<Card> GetCardByBoxId(String box_id);
	public Card GetCardByCardId(String card_id);
	//���¿�Ƭ���
	public void UpdateCardMarktypeDone(String card_id);
	public void UpdateCardMarktypeUnDone(String card_id);

	//����idɾ����Ƭ
	public void DeleteCardById(String card_id);
	
	//���¿�Ƭ��Ϣ
	public void UpdateCard(Card card);
	
	//���һ�ſ�Ƭ
	public void AddCard(Card card);
}
