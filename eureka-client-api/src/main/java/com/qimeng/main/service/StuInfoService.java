package com.qimeng.main.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.qimeng.main.dao.StuInfoFeigen;

@Service
public class StuInfoService {
	@Autowired
	StuInfoFeigen stuinfo;
	
    public String index()
    {
    	return stuinfo.index();
    }
    
	public String getStudent(JSONObject message) 
	{
		return stuinfo.getStudent(message);
	}
	
	public String upLoadPoints(JSONObject message)
	{
		return stuinfo.upLoadPoints(message);
	}
	
	public String updateMachineID(JSONObject message)
	{
		return stuinfo.updateMachineID(message);
	}
	
	public String usedPoints(JSONObject message) {
		return stuinfo.usedPoints(message);
	}
	
	public String deductPoints(JSONObject message) {
		return stuinfo.deductPoints(message);
	}
	
	public String updateDevStatus(JSONObject message) {
		return stuinfo.updateDevState(message);
	}
	
	public String updateDev(JSONObject message) {
		return stuinfo.updateDev(message);
	}
	
	public String getDevList(Integer page,JSONObject message) {
		return stuinfo.getDevList(page,message);
	}
	
	public String getDevive(JSONObject message) {
		return stuinfo.getDevive(message);
	}
	
	public String saveDev(JSONObject message) {
		return stuinfo.saveDev(message);
	}
	
	public String getStudentDataList(Integer page,JSONObject message) {
		return stuinfo.getStudentDataList(page,message);
	}
	
	public String getStudentInfo(JSONObject message) {
		return stuinfo.getStudentInfo(message);
	}
	
	public String uploadStudentList(String message,MultipartFile file) {
		return stuinfo.uploadStudentList(message, file);
	}
	
	public String saveStudentData(JSONObject message) {
		return stuinfo.saveStudentData(message);
	}
	
	public String updateStudentData(JSONObject message) {
		return stuinfo.updateStudentData(message);
	}
	
	public String updateStudentActive(JSONObject message) {
		return stuinfo.updateStudentActive(message);
	}
	
	public String exportStudentDataList(JSONObject message,HttpServletResponse response) {
		return stuinfo.exportStudentDataList(message, response);
	}
	
	public String getSchoolList(Integer page,JSONObject message){
		return stuinfo.getSchoolList(page,message);
	}
	
	public String getSchoolinfo(JSONObject message) {
		return stuinfo.getSchoolinfo(message);
	}
	
	public String saveSchoolinfo(JSONObject message) {
		return stuinfo.saveSchoolinfo(message);
	}
	
	public String updateSchool(JSONObject message) {
		return stuinfo.updateSchool(message);
	}
	
	public String devStateList(Integer page, JSONObject message) {
		return stuinfo.devStateList(page, message);
	}
	
	public String recycleTypeList(Integer page, JSONObject message) {
		return stuinfo.recycleTypeList(page, message);
	}
	
	public String contactsTypeList(Integer page,JSONObject message) {
		return stuinfo.contactsTypeList(page, message);
	}
	
	public String devState(JSONObject message) {
		return stuinfo.devState(message);
	}
	
	public String recycleType(JSONObject message) {
		return stuinfo.recycleType(message);
	}
	
	public String contactsType(JSONObject message) {
		return stuinfo.contactsType(message);
	}
	
	public String saveDevState(JSONObject message) {
		return stuinfo.saveDevState(message);
	}
	
	public String saveRecycleType(JSONObject message) {
		return stuinfo.saveRecycleType(message);
	}
	
	public String saveContactsType(JSONObject message) {
		return stuinfo.saveContactsType(message);
	}
	
	public String updateDevState(JSONObject message) {
		return stuinfo.updateDevState(message);
	}
	
	public String updateRecycleType(JSONObject message) {
		return stuinfo.updateRecycleType(message);
	}
	
	public String updateContactsType(JSONObject message) {
		return stuinfo.updateContactsType(message);
	}
	
	public String getAppInformList(Integer page,JSONObject message) {
		return stuinfo.getAppInformList(page, message);
	}
	
	public String getAppInform(JSONObject message) {
		return stuinfo.getAppInform(message);
	}
	
	public String updateAppInform(JSONObject message) {
		return stuinfo.updateAppInform(message);
	}
	
	public String saveAppInform(JSONObject message) {
		return stuinfo.saveAppInform(message);
	}
	
	
}
