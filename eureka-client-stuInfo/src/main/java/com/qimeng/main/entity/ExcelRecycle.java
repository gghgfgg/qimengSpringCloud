package com.qimeng.main.entity;

import java.util.Date;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年9月26日 上午11:07:37 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class ExcelRecycle {

    private String machineId;

    private String qrid;

    private Integer recycleType;
    
    private Integer count;
    
    private Integer points;
    
    private Date createTime;

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getQrid() {
		return qrid;
	}

	public void setQrid(String qrid) {
		this.qrid = qrid;
	}

	public Integer getRecycleType() {
		return recycleType;
	}

	public void setRecycleType(Integer recycleType) {
		this.recycleType = recycleType;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
    
}

