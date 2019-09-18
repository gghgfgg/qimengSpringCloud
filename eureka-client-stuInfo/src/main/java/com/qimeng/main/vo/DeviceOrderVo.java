package com.qimeng.main.vo;

import java.io.Serializable;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年9月17日 上午10:43:44 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class DeviceOrderVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String order;
	
	private String qrOrder;
	
	private Byte status;
	
	private Byte orderType;
	
	private Byte recycleType;
	
	private Integer count;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getQrOrder() {
		return qrOrder;
	}

	public void setQrOrder(String qrOrder) {
		this.qrOrder = qrOrder;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getOrderType() {
		return orderType;
	}

	public void setOrderType(Byte orderType) {
		this.orderType = orderType;
	}

	public Byte getRecycleType() {
		return recycleType;
	}

	public void setRecycleType(Byte recycleType) {
		this.recycleType = recycleType;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "DeviceOrderVo [order=" + order + ", qrOrder=" + qrOrder + ", status=" + status + ", orderType="
				+ orderType + ", recycleType=" + recycleType + ", count=" + count + "]";
	}

	
}

