package com.qimeng.main.vo;
/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月4日 上午12:26:19 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class DeviceStatusVo {

	private String serialNumber;
	
	private Byte status;

	private Byte statusType;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getStatusType() {
		return statusType;
	}

	public void setStatusType(Byte statusType) {
		this.statusType = statusType;
	}

	@Override
	public String toString() {
		return "DeviceStatusVo [serialNumber=" + serialNumber + ", status=" + status + ", statusType=" + statusType
				+ "]";
	}
	
}

