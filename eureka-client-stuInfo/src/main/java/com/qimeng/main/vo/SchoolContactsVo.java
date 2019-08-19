package com.qimeng.main.vo;
/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月19日 下午3:33:42 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class SchoolContactsVo {
	private Byte type;
	private String name;
	private String phone;
	private String position;
    private Byte weight;
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Byte getWeight() {
		return weight;
	}
	public void setWeight(Byte weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "SchoolContactsVo [type=" + type + ", name=" + name + ", phone=" + phone + ", position=" + position
				+ ", weight=" + weight + "]";
	}
    
}

