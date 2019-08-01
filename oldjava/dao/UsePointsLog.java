package com.qimeng.main.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class UsePointsLog {
	private long id;
	private String studentName;
	private String studentCode;
	private long usedPoints;
	@DateTimeFormat(pattern="yyyy-MM-dd HH-mm-ss")//页面写入数据库时格式化
	@JSONField(format="yyyy-MM-dd HH-mm-ss")//数据库导出页面时json格式化
	private Date createTime;
	private String appId;
	private String mark;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentCode() {
		return studentCode;
	}
	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}
	public long getUsedPoints() {
		return usedPoints;
	}
	public void setUsedPoints(long usedPoints) {
		this.usedPoints = usedPoints;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getAppid() {
		return appId;
	}
	public void setAppid(String appId) {
		this.appId = appId;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	@Override
	public String toString() {
		return "UsePointsLogDao [id=" + id + ", studenName=" + studentName + ", studentCode=" + studentCode
				+ ", usedPoints=" + usedPoints + ", creatTime=" + createTime + ", appId=" + appId + ", mark=" + mark
				+ "]";
	}
	
}
