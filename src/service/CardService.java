package service;

import java.util.List;

import pojo.Card;

public interface CardService {
	public List<Card> GetCardByBoxId(String box_id);
	public Card GetCardByCardId(String card_id);
	//更新卡片标记
	public void UpdateCardMarktypeDone(String card_id);
	public void UpdateCardMarktypeUnDone(String card_id);

	//根据id删除卡片
	public void DeleteCardById(String card_id);
	
	//更新卡片信息
	public void UpdateCard(Card card);
	
	//添加一张卡片
	public void AddCard(Card card);
}
