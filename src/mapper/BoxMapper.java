package mapper;

import java.util.List;

import pojo.Box;

public interface BoxMapper {
	//���պ������Ͳ��Һ���
	public List<Box> GetBoxByType_All(String box_name);
	public List<Box> GetBoxByType_Study(String box_name);
	public List<Box> GetBoxByType_Life(String box_name);
}
