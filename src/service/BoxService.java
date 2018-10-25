package service;

import java.util.List;

import pojo.Box;

public interface BoxService {
	//���պ������Ͳ��Һ���
	public List<Box> GetBoxByType_All(String box_name);
	public List<Box> GetBoxByType_Study(String box_name);
	public List<Box> GetBoxByType_Life(String box_name);
	public List<Box> GetBoxByType_Work(String box_name);
	public List<Box> GetBoxByType_Entertainment(String box_name);
	
	//�����û��˺Ų��Һ���
	public List<Box> GetBoxByUserAccount(String user_account);
	
	//��Ӻ���
	public void AddBox(Box box);
	
	//���º���
	public void UpdateBox(Box box);
	
	//ɾ������
	public void DeleteBox(String boxid);
}
