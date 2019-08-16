package com.qimeng.main.vo;
/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月8日 下午1:53:21 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class AppInformVo {
	
    private String appId;

    private String deskey;

    private String ivkey;

    private Byte appType;

    private String appName;

    private Byte active;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getDeskey() {
		return deskey;
	}

	public void setDeskey(String deskey) {
		this.deskey = deskey;
	}

	public String getIvkey() {
		return ivkey;
	}

	public void setIvkey(String ivkey) {
		this.ivkey = ivkey;
	}

	public Byte getAppType() {
		return appType;
	}

	public void setAppType(Byte appType) {
		this.appType = appType;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Byte getActive() {
		return active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "AppInformVo [appId=" + appId + ", deskey=" + deskey + ", ivkey=" + ivkey + ", appType="
				+ appType + ", appName=" + appName + ", active=" + active + "]";
	}
    
}

