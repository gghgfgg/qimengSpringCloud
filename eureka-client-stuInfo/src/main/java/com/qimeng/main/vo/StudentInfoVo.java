package com.qimeng.main.vo;


/**
 * @author admin
 *
 */
public class StudentInfoVo {
	private String name;
	private String stuCode;
	private String stuCard;
	private int wasteType;
	private int unit;
	private int point;
	private int bind;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getWasteType() {
		return wasteType;
	}

	public void setWasteType(int wasteType) {
		this.wasteType = wasteType;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getBind() {
		return bind;
	}

	public void setBind(int bind) {
		this.bind = bind;
	}

	@Override
	public String toString() {
		return "StudentInfoVo [name=" + name + ", stuCode=" + stuCode + ", stuCard=" + stuCard + ", wasteType="
				+ wasteType + ", unit=" + unit + ", point=" + point + ", bind=" + bind + "]";
	}
	

}
