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

}
