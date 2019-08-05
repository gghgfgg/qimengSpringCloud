package com.qimeng.main.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.SchoolInformDao;
import com.qimeng.main.entity.SchoolInform;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月5日 下午11:24:04 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class SchoolInformService {
	private static Logger logger = Logger.getLogger(SchoolInformService.class);
	
	@Autowired
	SchoolInformDao schoolInformDao;
	
	public int insertSchoolInform(SchoolInform schoolInform)
	{
		try {
			return schoolInformDao.insertSchoolInform(schoolInform);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("学校信息导入异常");
			logger.error("Error:",e);
			throw new RuntimeException(e);		
		}
	}
	
	public int updateSchoolInform(SchoolInform schoolInform) {
		try {
			return schoolInformDao.updateSchoolInform(schoolInform);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("学校信息更新异常");
			logger.error("Error:",e);
			throw new RuntimeException(e);		
		}
	}
	
	public List<SchoolInform> selectSchoolInformList(SchoolInform schoolInform){
		try {
			return schoolInformDao.selectSchoolInformList(schoolInform);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("学校信息查询异常");
			logger.error("Error:",e);
			throw new RuntimeException(e);		
		}
	}
	
	public List<SchoolInform> selectSchoolInformList(Boolean active){
		SchoolInform schoolInform=new SchoolInform();
		schoolInform.setActive(active);
		return selectSchoolInformList(schoolInform);
	}
	
	public SchoolInform selectSchoolInformBySchoolCode(String schoolCode) {
		SchoolInform schoolInform=new SchoolInform();
		schoolInform.setSchoolCode(schoolCode);
		List<SchoolInform> list=selectSchoolInformList(schoolInform);
		return list.isEmpty()?null:list.get(0);
	}
	public SchoolInform selectSchoolInformBySchoolCode(String schoolCode,Boolean active) {
		SchoolInform schoolInform=new SchoolInform();
		schoolInform.setSchoolCode(schoolCode);
		schoolInform.setActive(active);
		List<SchoolInform> list=selectSchoolInformList(schoolInform);
		return list.isEmpty()?null:list.get(0);
	}
	

}

