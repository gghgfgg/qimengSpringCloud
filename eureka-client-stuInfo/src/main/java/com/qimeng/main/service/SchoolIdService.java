package com.qimeng.main.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qimeng.main.dao.SchoolIdDao;
import com.qimeng.main.entity.SchoolId;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年9月5日 下午3:59:24 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class SchoolIdService {
	private static Logger logger = Logger.getLogger(SchoolIdService.class);
	
	@Autowired
	SchoolIdDao SchoolIdDao;

	@Cacheable(value = "SchoolId", unless = "#result == null")
	public List<SchoolId> selectSchoolIdList() {
		try {
			return SchoolIdDao.selectSchoolIdList();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取学校id列表异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	@CacheEvict(value = "SchoolId", allEntries=true)
	public int insertSchoolId(SchoolId schoolId) {
		try {
			return SchoolIdDao.insertSchoolId(schoolId);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("新增学校id异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	@CacheEvict(value = "SchoolId", allEntries=true)
	public int updateSchoolId(SchoolId schoolId) {
		try {
			return SchoolIdDao.updateSchoolId(schoolId);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新学校id异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}

	public SchoolId selectSchoolId(Integer id) {
		try {
			return SchoolIdDao.selectSchoolId(id);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取学校id异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	public PageInfo<SchoolId> selectSchoolIdPageList(Integer pageNum) {
		PageHelper.startPage(pageNum, 20);
		List<SchoolId> news = selectSchoolIdList();
		PageInfo<SchoolId> appsPageInfo = new PageInfo<>(news);
		return appsPageInfo;
	}
}

