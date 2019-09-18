package com.qimeng.main.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class StudentInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String stuCode;
	private String schoolId;
	
	//private String usedPoints;
	private BigDecimal totalPoints;
	private String stuCard;
	private int bind;
	private int active;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
	@JSONField(format="yyyy-MM-dd HH:mm:ss")//数据库导出页面时json格式化
	private Date startTime;
	
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
//	public String getUsedPoints() {
//		return usedPoints;
//	}
//	public void setUsedPoints(String usedPoints) {
//		this.usedPoints = usedPoints;
//	}
	public BigDecimal  getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(BigDecimal  totalPoints) {
		this.totalPoints = totalPoints;
	}
	public String getStuCard() {
		return stuCard;
	}
	public void setStuCard(String stuCard) {
		this.stuCard = stuCard;
	}
	public int getBind() {
		return bind;
	}
	public void setBind(int bind) {
		this.bind = bind;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "StudentInfo [id=" + id + ", name=" + name + ", stuCode=" + stuCode + ", schoolId=" + schoolId
				+ ", totalPoints=" + totalPoints + ", stuCard=" + stuCard + ", bind=" + bind + ", active=" + active
				+ ", startTime=" + startTime + "]";
	}
	
	
}
