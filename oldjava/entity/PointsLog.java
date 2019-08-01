package com.qimeng.main.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class PointsLog {
	private int id;
	private int wasteType;
	private int unit;
	@DateTimeFormat(pattern="yyyy-MM-dd HH-mm-ss")//页面写入数据库时格式化
	@JSONField(format="yyyy-MM-dd HH-mm-ss")//数据库导出页面时json格式化
	private Date uploadTime;
	private int points;
	private int stuId;
	private String machineID;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public String getMachineID() {
		return machineID;
	}
	public void setMachineID(String machineID) {
		this.machineID = machineID;
	}
	@Override
	public String toString() {
		return "PointsLog [id=" + id + ", wasteType=" + wasteType + ", unit=" + unit + ", uploadTime=" + uploadTime
				+ ", points=" + points + ", stuId=" + stuId + ", machineID=" + machineID + "]";
	}
	
}
