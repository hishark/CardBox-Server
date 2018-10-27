package mapper;

import java.util.List;

import pojo.Card;

public interface CardMapper {
	public List<Card> GetCardByBoxId(String box_id);
}
