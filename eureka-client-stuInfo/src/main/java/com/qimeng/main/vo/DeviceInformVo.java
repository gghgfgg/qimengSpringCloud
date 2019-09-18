package com.qimeng.main.vo;

import java.util.Date;

public class DeviceInformVo {
	private String machineID;
	private String serialNumber;
	private String schoolId;
	private String schoolCode;
	private String schoolName;
	private String postalCode;
	private String address;
	private Byte active;
	private Byte status;
	private Byte type;
	private String mark;
	private Date updateTime;
	public String getMachineID() {
		return machineID;
	}
	public void setMachineID(String machineID) {
		this.machineID = machineID;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Byte getActive() {
		return active;
	}
	public void setActive(Byte active) {
		this.active = active;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "DeviceInformVo [machineID=" + machineID + ", serialNumber=" + serialNumber + ", schoolId=" + schoolId
				+ ", schoolCode=" + schoolCode + ", schoolName=" + schoolName + ", postalCode=" + postalCode
				+ ", address=" + address + ", active=" + active + ", status=" + status + ", type=" + type + ", mark="
				+ mark + ", updateTime=" + updateTime + "]";
	}
	
}
