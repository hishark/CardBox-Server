package mapper;

import java.util.List;

import pojo.Box;

public interface BoxMapper {
	//按照盒子类型查找盒子
	public List<Box> GetBoxByType_All(String box_name);
	public List<Box> GetBoxByType_Study(String box_name);
	public List<Box> GetBoxByType_Life(String box_name);
}
