package service;

import java.util.List;

import pojo.Card;

public interface CardService {
	public List<Card> GetCardByBoxId(String box_id);

}
