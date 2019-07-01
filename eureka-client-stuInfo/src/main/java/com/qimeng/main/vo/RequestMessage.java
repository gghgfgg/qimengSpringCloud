package com.qimeng.main.vo;

import java.io.Serializable;

public class RequestMessage<E> implements Serializable{
	private static final long serialVersionUID = 123L;
	
	private String appID;
	private String machineNumber;
	private E data;
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public String getMachineNumber() {
		return machineNumber;
	}
	public void setMachineNumber(String machineNumber) {
		this.machineNumber = machineNumber;
	}
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "RequestMessage [appID=" + appID + ", machineNumber=" + machineNumber + ", data=" + data + "]";
	}
	

}
