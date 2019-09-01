package com.qimeng.main.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月30日 下午4:07:09 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class WecharUser {
	private Integer id;
	
	private String openid;
	
	private String unionid;
	
	private String mail;
	
	private String nickName;
	
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
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	@Override
	public String toString() {
		return "WecharUser [id=" + id + ", openid=" + openid + ", unionid=" + unionid + ", mail=" + mail + ", nick_ame="
				+ nickName + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}

