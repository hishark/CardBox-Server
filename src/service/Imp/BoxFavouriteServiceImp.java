package service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.BoxFavouriteMapper;
import pojo.BoxFavourite;
import service.BoxFavouriteService;

/* Serviceע��
 * ����spring����һ��ʵ���ࣨ��BookService��ʵ���ࣩ
 * ���Բ���Ҫ�Լ�ʵ������
 */
@Service
public class BoxFavouriteServiceImp implements BoxFavouriteService {

	/* ֻ��Ҫ����ӿڵ����ñ���
	 * spring����ע�����ע��
	 * spring��ܿ�ʵ���Զ�ʵ����
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
