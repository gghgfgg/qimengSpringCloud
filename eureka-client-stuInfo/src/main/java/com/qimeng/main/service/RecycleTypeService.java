package com.qimeng.main.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.RecycleTypeDao;
import com.qimeng.main.entity.RecycleType;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月2日 下午2:29:40 
* @version 1.0 
* @parameter  
* @since  
* @return  */

@Service
public class RecycleTypeService {

	private static Logger logger = Logger.getLogger(StudentInformService.class); 
	@Autowired
	RecycleTypeDao recycleTypeDao;
	
	
	/**
	 * @param recycleType
	 * @return
	 */
	@CacheEvict(value="RecycleType",key="#p0.type")
	public int insertRecycleType(RecycleType recycleType) {
		try {
			return recycleTypeDao.insertRecycleType(recycleType);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("插入新的垃圾类型异常");
			logger.error("Error:",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @param recycleType
	 * @return
	 */
	@CacheEvict(value="RecycleType",key="#p0.type")
	public int updateRecycleType(RecycleType recycleType) {
		try {
			return recycleTypeDao.updateRecycleType(recycleType);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新垃圾类型异常");
			logger.error("Error:",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @param recycleType
	 * @return
	 */
	public List<RecycleType> selectRecycleTypeList(RecycleType recycleType) {
		try {
			return recycleTypeDao.selectRecycleTypeList(recycleType);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查找垃圾类型异常");
			logger.error("Error:",e);
			throw new RuntimeException(e);
		}
	}
	public List<RecycleType> selectRecycleTypeList() {
		RecycleType recycleType=new RecycleType();
		return selectRecycleTypeList(recycleType);
	}
	
	/**
	 * 通过id 查找类型
	 * @param id
	 * @return
	 */
	public RecycleType selectRecycleTypeById(int id) {
		RecycleType recycleType=new RecycleType();
		recycleType.setId(id);
		List<RecycleType> list = selectRecycleTypeList(recycleType);
		return list.isEmpty()?null:list.get(0);
	}
	
	/**
	 * 通过type 查找类型
	 * @param type
	 * @return
	 */
	@Cacheable(value="RecycleType",key="#type",unless="#result == null")
	public RecycleType selectRecycleTypeByType(byte type) {
		RecycleType recycleType=new RecycleType();
		recycleType.setType(type);
		List<RecycleType> list = selectRecycleTypeList(recycleType);
		return list.isEmpty()?null:list.get(0);
	}
}

