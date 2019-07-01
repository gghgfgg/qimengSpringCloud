package com.qimeng.main.vo;


public class StudentInfo {
	private String name;
	private String stuCode;
	private int  wasteType;
	private int unit;
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

	@Override
	public String toString() {
		return "StudentInfo [name=" + name + ", stuCode=" + stuCode + ", wasteType=" + wasteType + ", unit=" + unit
				+ "]";
	}

	
	
}
