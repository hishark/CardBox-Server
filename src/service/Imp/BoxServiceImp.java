package service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.BoxMapper;
import pojo.Box;
import service.BoxService;

/* Serviceע��
 * ����spring����һ��ʵ���ࣨ��BookService��ʵ���ࣩ
 * ���Բ���Ҫ�Լ�ʵ������
 */
@Service
public class BoxServiceImp implements BoxService {

	
	/* ֻ��Ҫ����ӿڵ����ñ���
	 * spring����ע�����ע��
	 * spring��ܿ�ʵ���Զ�ʵ����
	 */
	@Autowired
	BoxMapper boxMapper;
	
	@Override
	public List<Box> GetBoxByType_All(String box_name) {
		// TODO Auto-generated method stub
		return boxMapper.GetBoxByType_All(box_name);
	}

	@Override
	public List<Box> GetBoxByType_Study(String box_name) {
		// TODO Auto-generated method stub
		return boxMapper.GetBoxByType_Study(box_name);
	}

	@Override
	public List<Box> GetBoxByType_Life(String box_name) {
		// TODO Auto-generated method stub
		return boxMapper.GetBoxByType_Life(box_name);
	}

}
