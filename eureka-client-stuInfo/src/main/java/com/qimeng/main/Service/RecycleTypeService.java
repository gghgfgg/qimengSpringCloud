package com.qimeng.main.Service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
	public RecycleType selectRecycleType(RecycleType recycleType) {
		try {
			return recycleTypeDao.selectRecycleType(recycleType);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查找垃圾类型异常");
			logger.error("Error:",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 通过id 查找类型
	 * @param id
	 * @return
	 */
	public RecycleType selectRecycleTypeById(int id) {
		RecycleType recycleType=new RecycleType();
		recycleType.setId(id);
		return selectRecycleType(recycleType);
	}
	
	/**
	 * 通过type 查找类型
	 * @param type
	 * @return
	 */
	public RecycleType selectRecycleTypeByType(byte type) {
		RecycleType recycleType=new RecycleType();
		recycleType.setType(type);
		return selectRecycleType(recycleType);
	}
}

