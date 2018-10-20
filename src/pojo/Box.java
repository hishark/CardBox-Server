package pojo;

import java.sql.Timestamp;

public class Box {
	String box_id;
	String box_name;
	User user;
	String box_type;
	Timestamp box_create_time;
	int box_love;
	String box_side;
	public String getBox_id() {
		return box_id;
	}
	public void setBox_id(String box_id) {
		this.box_id = box_id;
	}
	public String getBox_name() {
		return box_name;
	}
	public void setBox_name(String box_name) {
		this.box_name = box_name;
	}
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getBox_type() {
		return box_type;
	}
	public void setBox_type(String box_type) {
		this.box_type = box_type;
	}
	public Timestamp getBox_create_time() {
		return box_create_time;
	}
	public void setBox_create_time(Timestamp box_create_time) {
		this.box_create_time = box_create_time;
	}
	public int getBox_love() {
		return box_love;
	}
	public void setBox_love(int box_love) {
		this.box_love = box_love;
	}
	public String getBox_side() {
		return box_side;
	}
	public void setBox_side(String box_side) {
		this.box_side = box_side;
	}
	
	
}
