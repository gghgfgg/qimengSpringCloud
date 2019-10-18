package com.qimeng.main.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年10月18日 下午5:19:59 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class ExceptionLog {

	private Integer id;
	
	private String uuid;

    private Byte type;

    private Integer count;

    private Integer exCount;
    
    private Integer activityCount;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
   	@JSONField(format="yyyy-MM-dd HH:mm:ss")//数据库导出页面时json格式化
    private Date createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getExCount() {
		return exCount;
	}
	public void setExCount(Integer exCount) {
		this.exCount = exCount;
	}
	public Integer getActivityCount() {
		return activityCount;
	}
	public void setActivityCount(Integer activityCount) {
		this.activityCount = activityCount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}

