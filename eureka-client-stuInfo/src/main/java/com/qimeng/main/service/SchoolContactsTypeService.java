package com.qimeng.main.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
	@CacheEvict(value="SchoolContactsType",key="#p0.type")
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
	@CacheEvict(value="SchoolContactsType",key="#p0.type")
	public int updateSchoolContactsType(SchoolContactsType schoolContactsType) {
		try {
			return schoolContactsTypeDao.updateSchoolContactsType(schoolContactsType);
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
	public List<SchoolContactsType> selectSchoolContactsType(SchoolContactsType schoolContactsType) {
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
		List<SchoolContactsType> list=selectSchoolContactsType(schoolContactsType);
		return list.isEmpty()?null:list.get(0);
	}
	
	/**
	 * 通过type 查找类型
	 * @param type
	 * @return
	 */
	
	@Cacheable(value="SchoolContactsType",key="#type",unless="#result == null")
	public SchoolContactsType selectSchoolContactsTypeByType(byte type) {
		SchoolContactsType schoolContactsType=new SchoolContactsType();
		schoolContactsType.setType(type);
		List<SchoolContactsType> list=selectSchoolContactsType(schoolContactsType);
		return list.isEmpty()?null:list.get(0);
	}
}

