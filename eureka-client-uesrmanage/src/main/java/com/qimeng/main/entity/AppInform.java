package com.qimeng.main.entity;
/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月30日 上午11:03:39 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class AppInform {

	private String  id;
	private String  appid;
	private String  secret;
	private String  apptype;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getApptype() {
		return apptype;
	}
	public void setApptype(String apptype) {
		this.apptype = apptype;
	}
	
}

