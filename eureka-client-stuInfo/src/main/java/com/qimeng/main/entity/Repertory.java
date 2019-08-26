package com.qimeng.main.entity;
/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月26日 下午4:01:02 
* @version 1.0 
* @parameter  
* @since  
* @return  */

import java.util.Date;

public class Repertory {
	private Integer dpcode;
	private Integer proid;
	private Integer kucun;
	private Integer sale;
	private Date lastdate;
	public Integer getKucun() {
		return kucun;
	}
	public void setKucun(Integer kucun) {
		this.kucun = kucun;
	}
	public Integer getSale() {
		return sale;
	}
	public void setSale(Integer sale) {
		this.sale = sale;
	}
	public Date getLastdate() {
		return lastdate;
	}
	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}
	
	public Integer getDpcode() {
		return dpcode;
	}
	public void setDpcode(Integer dpcode) {
		this.dpcode = dpcode;
	}
	public Integer getProid() {
		return proid;
	}
	public void setProid(Integer proid) {
		this.proid = proid;
	}
	@Override
	public String toString() {
		return "Repertory [dpcode=" + dpcode + ", proid=" + proid + ", kucun=" + kucun + ", sale=" + sale
				+ ", lastdate=" + lastdate + "]";
	}

	
}

