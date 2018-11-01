package service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.BoxMapper;
import pojo.Box;
import service.BoxService;

/* Service注解
 * 告诉spring这是一个实现类（是BookService的实现类）
 * 所以不需要自己实例化啦
 */
@Service
public class BoxServiceImp implements BoxService {

	
	/* 只需要定义接口的引用变量
	 * spring利用注解进行注入
	 * spring框架可实现自动实例化
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
