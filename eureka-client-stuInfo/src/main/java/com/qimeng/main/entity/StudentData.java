package com.qimeng.main.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class StudentData {
    private Integer id;

    private String uuid;

    private String identityCard;

    private String studentCode;

    private String card;

    private String code;

    private String name;

    private Boolean type;

    private String schoolCode;

    private Boolean binding;

    private Boolean active;
    @DateTimeFormat(pattern="yyyy-MM-dd HH-mm-ss")//页面写入数据库时格式化
   	@JSONField(format="yyyy-MM-dd HH-mm-ss")//数据库导出页面时json格式化
    private Date createTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH-mm-ss")//页面写入数据库时格式化
   	@JSONField(format="yyyy-MM-dd HH-mm-ss")//数据库导出页面时json格式化
    private Date updateTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH-mm-ss")//页面写入数据库时格式化
   	@JSONField(format="yyyy-MM-dd HH-mm-ss")//数据库导出页面时json格式化
    private Date firstTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH-mm-ss")//页面写入数据库时格式化
   	@JSONField(format="yyyy-MM-dd HH-mm-ss")//数据库导出页面时json格式化
    private Date lastTime;

    private Integer activityCount;

    private Integer totalPoints;

    private Integer usedPoints;

    private Integer deductPoints;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard == null ? null : identityCard.trim();
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode == null ? null : studentCode.trim();
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card == null ? null : card.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode == null ? null : schoolCode.trim();
    }

    public Boolean getBinding() {
        return binding;
    }

    public void setBinding(Boolean binding) {
        this.binding = binding;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(Date firstTime) {
        this.firstTime = firstTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getActivityCount() {
        return activityCount;
    }

    public void setActivityCount(Integer activityCount) {
        this.activityCount = activityCount;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Integer getUsedPoints() {
        return usedPoints;
    }

    public void setUsedPoints(Integer usedPoints) {
        this.usedPoints = usedPoints;
    }

    public Integer getDeductPoints() {
        return deductPoints;
    }

    public void setDeductPoints(Integer deductPoints) {
        this.deductPoints = deductPoints;
    }
}