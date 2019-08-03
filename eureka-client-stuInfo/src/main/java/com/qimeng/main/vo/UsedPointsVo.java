package com.qimeng.main.vo;
/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月3日 下午4:42:07 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class UsedPointsVo {
	private String stuCode;
	private String stuCard;
	private int usedPoints;
	private String mark;
	private String operator;
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
	public int getUsedPoints() {
		return usedPoints;
	}
	public void setUsedPoints(int usedPoints) {
		this.usedPoints = usedPoints;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	@Override
	public String toString() {
		return "UsedPointsVo [stuCode=" + stuCode + ", stuCard=" + stuCard + ", usedPoints=" + usedPoints + ", mark="
				+ mark + ", operator=" + operator + "]";
	}
	
	
}

