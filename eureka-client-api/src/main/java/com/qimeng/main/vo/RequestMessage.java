package com.qimeng.main.vo;

import java.io.Serializable;

public class RequestMessage<E> implements Serializable{
	private static final long serialVersionUID = 123L;
	
	private String appID;
	private String machineID;
	private String accountTonken;
	private String operator;
	private E data;
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public String getMachineID() {
		return machineID;
	}
	public void setMachineID(String machineID) {
		this.machineID = machineID;
	}
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}
	public String getAccountTonken() {
		return accountTonken;
	}
	public void setAccountTonken(String accountTonken) {
		this.accountTonken = accountTonken;
	}

	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	@Override
	public String toString() {
		return "RequestMessage [appID=" + appID + ", machineID=" + machineID + ", accountTonken=" + accountTonken
				+ ", operator=" + operator + ", data=" + data + "]";
	}
	

}
