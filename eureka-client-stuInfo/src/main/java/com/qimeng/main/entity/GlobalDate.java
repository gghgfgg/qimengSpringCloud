package com.qimeng.main.entity;
/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月15日 下午3:38:34 
* @version 1.0 
* @parameter  
* @since  
* @return  */

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class GlobalDate {
	Integer id;
	String globalKey;
	String globalValue;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss") // 页面写入数据库时格式化
	@JSONField(format = "yyyy-MM-dd HH-mm-ss") // 数据库导出页面时json格式化
	private Date createTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss") // 页面写入数据库时格式化
	@JSONField(format = "yyyy-MM-dd HH-mm-ss") // 数据库导出页面时json格式化
	private Date updateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGlobalKey() {
		return globalKey;
	}
	public void setGlobalKey(String globalKey) {
		this.globalKey = globalKey;
	}
	public String getGlobalValue() {
		return globalValue;
	}
	public void setGlobalValue(String globalValue) {
		this.globalValue = globalValue;
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
