package com.qimeng.main.vo;

import java.io.Serializable;

public class ResponseMessage <E> implements Serializable{
	private static final long serialVersionUID = 123L;
	private int statusCode = 1;
	private boolean success;
	private String message;
	private String accountTonken="";
	private E data;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getAccountTonken() {
		return accountTonken;
	}
	public void setAccountTonken(String accountTonken) {
		this.accountTonken = accountTonken;
	}
	public void setSuccessMessage(String message) {
		this.success = true;
		this.message = message;
	}
	public void setFailedMessage(String message) {
		this.success = true;
		this.message = message;
	}
	@Override
	public String toString() {
		return "ResponseMessage [statusCode=" + statusCode + ", success=" + success + ", message=" + message
				+ ", accountTonken=" + accountTonken + ", data=" + data + "]";
	}

	
}
