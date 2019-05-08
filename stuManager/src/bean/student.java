package bean;

import java.io.Serializable;

public class student implements Serializable{
	private int id;
	private String stu_name;
	private int stu_age;
	private String stu_sex;
	private int stu_numb;	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public int getStu_age() {
		return stu_age;
	}
	public void setStu_age(int stu_age) {
		this.stu_age = stu_age;
	}
	public String getStu_sex() {
		return stu_sex;
	}
	public void setStu_sex(String stu_sex) {
		this.stu_sex = stu_sex;
	}
	public int getStu_numb() {
		return stu_numb;
	}
	
	public void setStu_numb(int stu_numb) {
		this.stu_numb = stu_numb;
	}
	@Override
	public String toString() {
		return "student [id=" + id + ", stu_name=" + stu_name + ", stu_age=" + stu_age + ", stu_sex=" + stu_sex
				+ ", stu_numb=" + stu_numb + "]";
	}

}
