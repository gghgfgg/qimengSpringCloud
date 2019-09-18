package com.qimeng.main.vo;
/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月28日 下午9:20:20 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class StudentBindVo {

	private String name;
	
	private String phone;
	
	private String stuCode;
	
	private String stuCard;

	private Byte bind;
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

	public String getStuCode() {
		return stuCode;
	}

	public void setStuCode(String stuCode) {
		this.stuCode = stuCode;
	}

	public String getStuCard() {
		return stuCard;
	}

	public void setStuCard(String stuCard) {
		this.stuCard = stuCard;
	}

	public Byte getBind() {
		return bind;
	}

	public void setBind(Byte bind) {
		this.bind = bind;
	}

	@Override
	public String toString() {
		return "StudentBindVo [name=" + name + ", phone=" + phone + ", stuCode=" + stuCode + ", stuCard=" + stuCard
				+ ", bind=" + bind + "]";
	}

	
}

