package com.qimeng.main.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.SchoolContactsTypeDao;
import com.qimeng.main.entity.SchoolContactsType;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月5日 下午9:30:27 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class SchoolContactsTypeService {
	private static Logger logger = Logger.getLogger(SchoolContactsTypeService.class);
	
	@Autowired
	SchoolContactsTypeDao schoolContactsTypeDao;
	
	/**
	 * @param recycleType
	 * @return
	 */
	public int insertSchoolContactsType(SchoolContactsType schoolContactsType) {
		try {
			return schoolContactsTypeDao.insertSchoolContactsType(schoolContactsType);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("插入新的联系人类型异常");
			logger.error("Error:",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @param recycleType
	 * @return
	 */
	public int updateSchoolContactsType(SchoolContactsType schoolContactsType) {
		try {
			return schoolContactsTypeDao.insertSchoolContactsType(schoolContactsType);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新联系人类型异常");
			logger.error("Error:",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @param recycleType
	 * @return
	 */
	public SchoolContactsType selectSchoolContactsType(SchoolContactsType schoolContactsType) {
		try {
			return schoolContactsTypeDao.selectSchoolContactsType(schoolContactsType);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查找联系人类型异常");
			logger.error("Error:",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 通过id 查找类型
	 * @param id
	 * @return
	 */
	public SchoolContactsType selectSchoolContactsTypeById(int id) {
		SchoolContactsType schoolContactsType=new SchoolContactsType();
		schoolContactsType.setId(id);
		return selectSchoolContactsType(schoolContactsType);
	}
	
	/**
	 * 通过type 查找类型
	 * @param type
	 * @return
	 */
	public SchoolContactsType selectSchoolContactsTypeByType(byte type) {
		SchoolContactsType schoolContactsType=new SchoolContactsType();
		schoolContactsType.setType(type);
		return selectSchoolContactsType(schoolContactsType);
	}
}

