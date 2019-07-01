package com.qimeng.main.vo;

public class OldResponseJson {
	 private int msg;
	 private String xm;
	 private boolean success;
	 private int jf;
	public int getMsg() {
		return msg;
	}
	public void setMsg(int msg) {
		this.msg = msg;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getJf() {
		return jf;
	}
	public void setJf(int jf) {
		this.jf = jf;
	}
	@Override
	public String toString() {
		return "OldResponseJson [msg=" + msg + ", xm=" + xm + ", success=" + success + ", jf=" + jf + "]";
	}
	
}
