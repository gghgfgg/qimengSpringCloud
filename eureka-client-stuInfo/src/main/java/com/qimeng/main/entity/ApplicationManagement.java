package com.qimeng.main.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class ApplicationManagement {
    private Integer id;

    private String appId;

    private String deskey;

    private String ivkey;

    private Boolean appType;

    private String appName;

    private Boolean active;

    @DateTimeFormat(pattern="yyyy-MM-dd HH-mm-ss")//页面写入数据库时格式化
	@JSONField(format="yyyy-MM-dd HH-mm-ss")//数据库导出页面时json格式化
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getDeskey() {
        return deskey;
    }

    public void setDeskey(String deskey) {
        this.deskey = deskey == null ? null : deskey.trim();
    }

    public String getIvkey() {
        return ivkey;
    }

    public void setIvkey(String ivkey) {
        this.ivkey = ivkey == null ? null : ivkey.trim();
    }

    public Boolean getAppType() {
        return appType;
    }

    public void setAppType(Boolean appType) {
        this.appType = appType;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}