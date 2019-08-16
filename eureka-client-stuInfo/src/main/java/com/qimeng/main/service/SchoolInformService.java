package com.qimeng.main.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
	
	@CacheEvict(value="SchoolInform",key="#p0.schoolCode")
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
	
	@CacheEvict(value="SchoolInform",key="#p0.schoolCode")
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
	@Cacheable(value="SchoolInform",key="#schoolCode")
	public SchoolInform selectSchoolInformBySchoolCode(String schoolCode) {
		SchoolInform schoolInform=new SchoolInform();
		schoolInform.setSchoolCode(schoolCode);
		List<SchoolInform> list=selectSchoolInformList(schoolInform);
		return list.isEmpty()?null:list.get(0);
	}
	@Cacheable(value="SchoolInform",key="#schoolCode")
	public SchoolInform selectSchoolInformBySchoolCode(String schoolCode,Boolean active) {
		SchoolInform schoolInform=new SchoolInform();
		schoolInform.setSchoolCode(schoolCode);
		schoolInform.setActive(active);
		List<SchoolInform> list=selectSchoolInformList(schoolInform);
		return list.isEmpty()?null:list.get(0);
	}
	
	public List<SchoolInform> selectSchoolInformByName(String schoolName) {
		SchoolInform schoolInform=new SchoolInform();
		schoolInform.setSchoolName(schoolName);
		return selectSchoolInformList(schoolInform);
	}
	
	public List<SchoolInform> selectSchoolCodeList(String postalCode){
		try {
			return schoolInformDao.selectSchoolCodeList(postalCode);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("学校代码查询异常");
			logger.error("Error:",e);
			throw new RuntimeException(e);		
		}
	}
}

