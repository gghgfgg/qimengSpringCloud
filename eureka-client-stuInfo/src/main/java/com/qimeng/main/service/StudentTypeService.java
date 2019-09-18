package com.qimeng.main.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.StudentTypeDao;
import com.qimeng.main.entity.StudentType;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年9月7日 下午10:10:19 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class StudentTypeService {
	private static Logger logger = Logger.getLogger(StudentTypeService.class);
	@Autowired
	StudentTypeDao studentTypeDao;
	@CacheEvict(value="StudentType",key="#p0.type")
	public int insertStudentType(StudentType studentType) {
		try {
			return studentTypeDao.insertStudentType(studentType);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("新增学生类型错误");
			logger.error("Error:",e);
			throw new RuntimeException(e);		
		}
	}
	@CacheEvict(value="StudentType",key="#p0.type")
	public int updateStudentType(StudentType studentType) {
		try {
			return studentTypeDao.updateStudentType(studentType);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新学生类型错误");
			logger.error("Error:",e);
			throw new RuntimeException(e);		
		}
	}
	
	public List<StudentType> selectStudentTypeList(StudentType studentType) {
		try {
			return studentTypeDao.selectStudentTypeList(studentType);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("选择学生类型错误");
			logger.error("Error:",e);
			throw new RuntimeException(e);		
		}
	}
	@Cacheable(value="StudentType",key="#type",unless="#result == null")
	public StudentType selectStudentType(byte type) {
		StudentType studentType=new StudentType();
		studentType.setType(type);
		List<StudentType> list=selectStudentTypeList(studentType);
		return list.isEmpty()?null:list.get(0);
	}
	
	public StudentType selectStudentType(int id) {
		StudentType studentType=new StudentType();
		studentType.setId(id);
		List<StudentType> list=selectStudentTypeList(studentType);
		return list.isEmpty()?null:list.get(0);
	}
}

