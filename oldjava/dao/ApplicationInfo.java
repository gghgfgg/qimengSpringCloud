package com.qimeng.main.entity;

public class ApplicationInfo {

	private int id;
	private String appID;
	private String desKey;
	private String ivKey;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public String getDesKey() {
		return desKey;
	}
	public void setDesKey(String desKey) {
		this.desKey = desKey;
	}
	public String getIvKey() {
		return ivKey;
	}
	public void setIvKey(String ivKey) {
		this.ivKey = ivKey;
	}
	@Override
	public String toString() {
		return "ApplicationInfo [id=" + id + ", appID=" + appID + ", desKey=" + desKey + ", ivKey=" + ivKey + "]";
	}
	
}
