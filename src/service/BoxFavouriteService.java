package service;

import pojo.BoxFavourite;

public interface BoxFavouriteService {
	//添加一条喜欢记录
	public void AddBoxFavourite(BoxFavourite boxfavourite);
	
	//删除一条喜欢记录
	public void DeleteBoxFavourite(BoxFavourite boxfavourite);
}
