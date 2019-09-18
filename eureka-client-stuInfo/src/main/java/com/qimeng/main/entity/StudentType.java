package com.qimeng.main.entity;

import java.io.Serializable;
import java.util.Date;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年9月7日 下午9:22:23 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class StudentType implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Byte type;

    private Byte bPhone;

    private Byte bTeacher;
    
    private String mark;

    private Date createTime;

    private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Byte getbPhone() {
		return bPhone;
	}

	public void setbPhone(Byte bPhone) {
		this.bPhone = bPhone;
	}

	public Byte getbTeacher() {
		return bTeacher;
	}

	public void setbTeacher(Byte bTeacher) {
		this.bTeacher = bTeacher;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
}

