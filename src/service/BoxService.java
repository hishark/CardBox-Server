package service;

import java.util.List;

import pojo.Box;

public interface BoxService {
	//按照盒子类型查找盒子
	public List<Box> GetBoxByType_All(String box_name);
	public List<Box> GetBoxByType_Study(String box_name);
	public List<Box> GetBoxByType_Life(String box_name);
	public List<Box> GetBoxByType_Work(String box_name);
	public List<Box> GetBoxByType_Entertainment(String box_name);
	
	//按照用户账号查找盒子
	public List<Box> GetBoxByUserAccount(String user_account);
	
	//添加盒子
	public void AddBox(Box box);
	
	//更新盒子
	public void UpdateBox(Box box);
	
	//删除盒子
	public void DeleteBox(String boxid);
}
