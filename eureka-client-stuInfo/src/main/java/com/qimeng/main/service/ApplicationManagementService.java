package com.qimeng.main.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qimeng.main.dao.ApplicationManagementDao;
import com.qimeng.main.entity.ApplicationManagement;
import com.qimeng.main.util.EncryptUtil;
import com.qimeng.main.util.StaticGlobal;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2019年8月2日 下午11:19:08
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Service
public class ApplicationManagementService {
	private static Logger logger = Logger.getLogger(ApplicationManagementService.class);
	@Autowired
	ApplicationManagementDao applicationManagementDao;

	/**
	 * @param applicationManagement
	 * @return
	 */
	@CacheEvict(value = "ApplicationManagement", key = "#p0.appId")
	public int insertApplicationManagement(ApplicationManagement applicationManagement) {
		try {
			return applicationManagementDao.insertApplicationManagement(applicationManagement);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("设置新的app异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}

	@CacheEvict(value = "ApplicationManagement", key = "#appId")
	public int insertApplicationManagement(String appId, String appName, Byte active, Byte appType, String desky,
			String ivkey) {
		ApplicationManagement applicationManagement = new ApplicationManagement();

		applicationManagement.setAppId(appId);
		applicationManagement.setActive(active);
		applicationManagement.setAppType(appType);
		applicationManagement.setAppName(appName);
		applicationManagement.setDeskey(desky);
		applicationManagement.setIvkey(ivkey);
		Date date = new Date();
		applicationManagement.setUpdateTime(date);
		applicationManagement.setCreateTime(date);
		return insertApplicationManagement(applicationManagement);
	}

	/**
	 * @param applicationManagement
	 * @return
	 */
	@CacheEvict(value = "ApplicationManagement", key = "#p0.appId")
	public int updateApplicationManagement(ApplicationManagement applicationManagement) {
		try {
			return applicationManagementDao.updateApplicationManagement(applicationManagement);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新app异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}

	@CacheEvict(value = "ApplicationManagement", key = "#appId")
	public int updateApplicationManagement(String appId, String appName, Byte active, Byte appType, String desky,
			String ivkey) {
		ApplicationManagement applicationManagement = new ApplicationManagement();

		applicationManagement.setAppId(appId);
		applicationManagement.setActive(active);
		applicationManagement.setAppType(appType);
		applicationManagement.setAppName(appName);
		applicationManagement.setDeskey(desky);
		applicationManagement.setIvkey(ivkey);
		applicationManagement.setUpdateTime(new Date());

		return updateApplicationManagement(applicationManagement);
	}

	/**
	 * @param applicationManagement
	 * @return
	 */
	public List<ApplicationManagement> selectApplicationManagementList(ApplicationManagement applicationManagement) {
		try {
			return applicationManagementDao.selectApplicationManagementList(applicationManagement);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查找app异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}

	public List<ApplicationManagement> selectApplicationManagementList(String appName, Byte active, Byte appType) {
		ApplicationManagement applicationManagement = new ApplicationManagement();
		applicationManagement.setActive(active);
		applicationManagement.setAppType(appType);
		applicationManagement.setAppName(appName);
		return selectApplicationManagementList(applicationManagement);
	}

	/**
	 * 根据appid查找
	 * 
	 * @param appId
	 * @param active
	 * @return
	 */
	@Cacheable(value = "ApplicationManagement", key = "#appId", unless = "#result == null")
	public ApplicationManagement selectApplicationManagementByAppId(String appId, Byte active) {
		ApplicationManagement applicationManagement = new ApplicationManagement();
		applicationManagement.setAppId(appId);
		applicationManagement.setActive(active);
		List<ApplicationManagement> list = selectApplicationManagementList(applicationManagement);
		return list.isEmpty() ? null : list.get(0);
	}

	public String encodeMessage(ApplicationManagement applicationManagement, String message) {
		try {
			EncryptUtil encryptUtil = new EncryptUtil(applicationManagement.getDeskey(),
					applicationManagement.getIvkey());
			return encryptUtil.encode(message);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public String decodeMessage(ApplicationManagement applicationManagement, String message) {
		try {
			EncryptUtil encryptUtil = new EncryptUtil(applicationManagement.getDeskey(),
					applicationManagement.getIvkey());
			return encryptUtil.decode(message);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public PageInfo<ApplicationManagement> selectAppPageList(Integer pageNum, String appName, Byte active,
			Byte appType) {
		PageHelper.startPage(pageNum, 20);
		List<ApplicationManagement> news = selectApplicationManagementList(appName, active, appType);
		PageInfo<ApplicationManagement> appsPageInfo = new PageInfo<>(news);
		return appsPageInfo;
	}
	
	public ApplicationManagement checkApplicationAuthority(String appId,Byte appType) {
		ApplicationManagement applicationManagement=selectApplicationManagementByAppId(appId,StaticGlobal.ACTIVE);
		if(applicationManagement!=null)
		{
			if((applicationManagement.getAppType()&appType)!=appType) {
				return null;
			}
		}
		return applicationManagement;
	}
}
