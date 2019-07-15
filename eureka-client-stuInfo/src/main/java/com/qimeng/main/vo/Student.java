package com.qimeng.main.vo;

public class Student {

	private int id;
	private String name;
	private String code;
	private int points;
	private int bind;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getBind() {
		return bind;
	}
	public void setBind(int bind) {
		this.bind = bind;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", code=" + code + ", points=" + points + ", bind=" + bind
				+ "]";
	}

	
}
