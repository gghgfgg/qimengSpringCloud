package com.qimeng.main.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class DeviceRecycleLog {
    private Integer id;

    private String machineId;

    private String schoolidOfDevice;

    private String uuid;

    private String schoolidOfStudent;

    private Byte recycleType;

    private Byte identityType;

    private Integer count;

    private Integer lastRemainder;

    private Integer factor;

    private Integer points;

    private Integer remainder;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
   	@JSONField(format="yyyy-MM-dd HH:mm:ss")//数据库导出页面时json格式化
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId == null ? null : machineId.trim();
    }

    public String getSchoolidOfDevice() {
        return schoolidOfDevice;
    }

    public void setSchoolidOfDevice(String schoolidOfDevice) {
        this.schoolidOfDevice = schoolidOfDevice == null ? null : schoolidOfDevice.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getSchoolidOfStudent() {
        return schoolidOfStudent;
    }

    public void setSchoolidOfStudent(String schoolidOfStudent) {
        this.schoolidOfStudent = schoolidOfStudent == null ? null : schoolidOfStudent.trim();
    }

    public Byte getRecycleType() {
        return recycleType;
    }

    public void setRecycleType(Byte recycleType) {
        this.recycleType = recycleType;
    }

    public Byte getIdentityType() {
        return identityType;
    }

    public void setIdentityType(Byte identityType) {
        this.identityType = identityType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getLastRemainder() {
        return lastRemainder;
    }

    public void setLastRemainder(Integer lastRemainder) {
        this.lastRemainder = lastRemainder;
    }

    public Integer getFactor() {
        return factor;
    }

    public void setFactor(Integer factor) {
        this.factor = factor;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getRemainder() {
        return remainder;
    }

    public void setRemainder(Integer remainder) {
        this.remainder = remainder;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}