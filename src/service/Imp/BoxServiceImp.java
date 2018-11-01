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

	@Override
	public List<Box> GetBoxByUserAccount(String user_account) {
		// TODO Auto-generated method stub
		return boxMapper.GetBoxByUserAccount(user_account);
	}

	@Override
	public void AddBox(Box box) {
		// TODO Auto-generated method stub
		boxMapper.AddBox(box);
	}

	@Override
	public List<Box> GetBoxByType_Work(String box_name) {
		// TODO Auto-generated method stub
		return boxMapper.GetBoxByType_Work(box_name);
	}

	@Override
	public List<Box> GetBoxByType_Entertainment(String box_name) {
		// TODO Auto-generated method stub
		return boxMapper.GetBoxByType_Entertainment(box_name);
	}

	@Override
	public void UpdateBox(Box box) {
		// TODO Auto-generated method stub
		boxMapper.UpdateBox(box);
	}

	@Override
	public void DeleteBox(String boxid) {
		// TODO Auto-generated method stub
		boxMapper.DeleteBox(boxid);
	}

	@Override
	public Box GetBoxById(String box_id) {
		// TODO Auto-generated method stub
		return boxMapper.GetBoxById(box_id);
	}

	@Override
	public void UpdateBoxTime(Box box) {
		// TODO Auto-generated method stub
		boxMapper.UpdateBoxTime(box);
	}

	@Override
	public List<Box> GetFavouriteBox(String user_account) {
		// TODO Auto-generated method stub
		return boxMapper.GetFavouriteBox(user_account);
	}

	@Override
	public void ClearBox(String boxid) {
		// TODO Auto-generated method stub
		boxMapper.ClearBox(boxid);
	}

}
