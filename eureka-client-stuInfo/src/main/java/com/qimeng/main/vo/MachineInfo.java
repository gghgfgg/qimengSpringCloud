package com.qimeng.main.vo;

public class MachineInfo {
	private String machineID;
	private String serialNumber;
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
	@Override
	public String toString() {
		return "MachineInfo [machineID=" + machineID + ", serialNumber=" + serialNumber + "]";
	}
	

}
