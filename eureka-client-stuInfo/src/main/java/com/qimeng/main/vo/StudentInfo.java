package com.qimeng.main.vo;


public class StudentInfo {
	private String name;
	private String stuCode;
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

	@Override
	public String toString() {
		return "StudentInfo [name=" + name + ", stuCode=" + stuCode + "]";
	}
	
}
