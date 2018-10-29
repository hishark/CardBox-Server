package service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.BoxFavouriteMapper;
import pojo.BoxFavourite;
import service.BoxFavouriteService;

/* Service注解
 * 告诉spring这是一个实现类（是BookService的实现类）
 * 所以不需要自己实例化啦
 */
@Service
public class BoxFavouriteServiceImp implements BoxFavouriteService {

	/* 只需要定义接口的引用变量
	 * spring利用注解进行注入
	 * spring框架可实现自动实例化
	 */
	@Autowired
	BoxFavouriteMapper boxFavouriteMapper;
	
	@Override
	public void AddBoxFavourite(BoxFavourite boxfavourite) {
		// TODO Auto-generated method stub
		boxFavouriteMapper.AddBoxFavourite(boxfavourite);
	}

	@Override
	public void DeleteBoxFavourite(BoxFavourite boxfavourite) {
		// TODO Auto-generated method stub
		boxFavouriteMapper.DeleteBoxFavourite(boxfavourite);
	}

}
