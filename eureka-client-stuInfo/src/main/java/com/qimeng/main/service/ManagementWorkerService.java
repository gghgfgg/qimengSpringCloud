package com.qimeng.main.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qimeng.main.dao.ManagementWorkerDao;
import com.qimeng.main.entity.ManagementWorker;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年10月17日 下午8:39:23 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class ManagementWorkerService {
	private static Logger logger = Logger.getLogger(ManagementWorkerService.class);
	
	@Autowired
	ManagementWorkerDao managementWorkerDao;
	
	@Autowired
	PostalCodeService postalCodeService;
	/**
	 * @param managementWorker
	 * @return
	 */
	public int insertManagementWorker(ManagementWorker managementWorker) {
		try {
			return managementWorkerDao.insertManagementWorker(managementWorker);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("新增回收机工作人员异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @param managementWorker
	 * @return
	 */
	@CacheEvict(value = "ManagementWorker", key = "#p0.uuid")
	public int updateManagementWorker(ManagementWorker managementWorker) {
		try {
			return managementWorkerDao.updateManagementWorker(managementWorker);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新回收机工作人员异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @param managementWorker
	 * @return
	 */
	public List<ManagementWorker> selectManagementWorkerList(ManagementWorker managementWorker) {
		try {
			return managementWorkerDao.selectManagementWorkerList(managementWorker);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查找回收机工作人员异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * 根据uuid查找
	 * 
	 * @param appId
	 * @param active
	 * @return
	 */
	@Cacheable(value = "ManagementWorker", key = "#uuid", unless = "#result == null")
	public ManagementWorker selectManagementWorker(String uuid, Byte active) {
		ManagementWorker managementWorker = new ManagementWorker();
		managementWorker.setUuid(uuid);
		managementWorker.setActive(active);
		List<ManagementWorker> list = selectManagementWorkerList(managementWorker);
		return list.isEmpty() ? null : list.get(0);
	}

	public PageInfo<ManagementWorker> managementWorkerPageList(Integer page, ManagementWorker managementWorker) {
		// TODO Auto-generated method stub
		if (!StringUtils.isEmpty(managementWorker.getPostalCode())) {
			String codeString = postalCodeService.selectPostalCode(managementWorker.getPostalCode());
			managementWorker.setPostalCode(codeString);
		}
		
		PageHelper.startPage(page, 20);
		List<ManagementWorker> news = selectManagementWorkerList(managementWorker);
	    PageInfo<ManagementWorker> workPageInfo = new PageInfo<>(news);
		return workPageInfo;

	}
	
}

