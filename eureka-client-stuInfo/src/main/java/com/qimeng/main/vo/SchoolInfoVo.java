package com.qimeng.main.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;


/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月19日 上午10:45:41 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class SchoolInfoVo {
	private String schoolName;

    private String schoolId;

    private String schoolCode;

    private String postalCode;

    private String address;

    private String contacts;

    private Boolean active;
    @DateTimeFormat(pattern="yyyy-MM-dd HH-mm-ss")//页面写入数据库时格式化
   	@JSONField(format="yyyy-MM-dd HH-mm-ss")//数据库导出页面时json格式化
    private Date createTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH-mm-ss")//页面写入数据库时格式化
   	@JSONField(format="yyyy-MM-dd HH-mm-ss")//数据库导出页面时json格式化
    private Date updateTime;
    
    private String postalCodename;
    
    private Integer headcount;
    
    private Integer studentcount;
    
    private Integer teachercount;
    
    private Integer studentactcount;
    
    private Integer teacheractcount;
    
    private Byte weight;
    
    List<RecycleCountVo> listRecycleCountVo;

    List<SchoolContactsVo> listSchoolContactsVo;
    
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getHeadcount() {
		return headcount;
	}

	public void setHeadcount(Integer headcount) {
		this.headcount = headcount;
	}

	public Integer getStudentcount() {
		return studentcount;
	}

	public void setStudentcount(Integer studentcount) {
		this.studentcount = studentcount;
	}

	public Integer getTeachercount() {
		return teachercount;
	}

	public void setTeachercount(Integer teachercount) {
		this.teachercount = teachercount;
	}

	public Integer getStudentactcount() {
		return studentactcount;
	}

	public void setStudentactcount(Integer studentactcount) {
		this.studentactcount = studentactcount;
	}

	public Integer getTeacheractcount() {
		return teacheractcount;
	}

	public void setTeacheractcount(Integer teacheractcount) {
		this.teacheractcount = teacheractcount;
	}

	public List<RecycleCountVo> getListRecycleCountVo() {
		return listRecycleCountVo;
	}

	public void setListRecycleCountVo(List<RecycleCountVo> listRecycleCountVo) {
		this.listRecycleCountVo = listRecycleCountVo;
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

	public String getPostalCodename() {
		return postalCodename;
	}

	public void setPostalCodename(String postalCodename) {
		this.postalCodename = postalCodename;
	}

	public List<SchoolContactsVo> getListSchoolContactsVo() {
		return listSchoolContactsVo;
	}

	public void setListSchoolContactsVo(List<SchoolContactsVo> listSchoolContactsVo) {
		this.listSchoolContactsVo = listSchoolContactsVo;
	}

	public Byte getWeight() {
		return weight;
	}

	public void setWeight(Byte weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "SchoolInfoVo [schoolName=" + schoolName + ", schoolId=" + schoolId + ", schoolCode=" + schoolCode
				+ ", postalCode=" + postalCode + ", address=" + address + ", contacts=" + contacts + ", active="
				+ active + ", createTime=" + createTime + ", updateTime=" + updateTime + ", postalCodename="
				+ postalCodename + ", headcount=" + headcount + ", studentcount=" + studentcount + ", teachercount="
				+ teachercount + ", studentactcount=" + studentactcount + ", teacheractcount=" + teacheractcount
				+ ", weight=" + weight + ", listRecycleCountVo=" + listRecycleCountVo + ", listSchoolContactsVo="
				+ listSchoolContactsVo + ", getSchoolName()=" + getSchoolName() + ", getSchoolId()=" + getSchoolId()
				+ ", getSchoolCode()=" + getSchoolCode() + ", getPostalCode()=" + getPostalCode() + ", getAddress()="
				+ getAddress() + ", getContacts()=" + getContacts() + ", getActive()=" + getActive()
				+ ", getHeadcount()=" + getHeadcount() + ", getStudentcount()=" + getStudentcount()
				+ ", getTeachercount()=" + getTeachercount() + ", getStudentactcount()=" + getStudentactcount()
				+ ", getTeacheractcount()=" + getTeacheractcount() + ", getListRecycleCountVo()="
				+ getListRecycleCountVo() + ", getCreateTime()=" + getCreateTime() + ", getUpdateTime()="
				+ getUpdateTime() + ", getPostalCodename()=" + getPostalCodename() + ", getListSchoolContactsVo()="
				+ getListSchoolContactsVo() + ", getWeight()=" + getWeight() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}



	
}

