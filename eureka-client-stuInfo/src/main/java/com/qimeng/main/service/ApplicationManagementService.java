package com.qimeng.main.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.ApplicationManagementDao;
import com.qimeng.main.entity.ApplicationManagement;
import com.qimeng.main.util.EncryptUtil;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月2日 下午11:19:08 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class ApplicationManagementService {
	private static Logger logger = Logger.getLogger(ApplicationManagementService.class);
	@Autowired
	ApplicationManagementDao applicationManagementDao;
	
	/**
	 * @param applicationManagement
	 * @return
	 */
	public int insertApplicationManagement(ApplicationManagement applicationManagement) {
		try {
			return applicationManagementDao.insertApplicationManagement(applicationManagement);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("设置新的app异常");
			logger.error("Error:",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @param applicationManagement
	 * @return
	 */
	public int updateApplicationManagement(ApplicationManagement applicationManagement) {
		try {
			return applicationManagementDao.updateApplicationManagement(applicationManagement);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新app异常");
			logger.error("Error:",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @param applicationManagement
	 * @return
	 */
	public List<ApplicationManagement>  selectApplicationManagementList(ApplicationManagement applicationManagement){
		try {
			return applicationManagementDao.selectApplicationManagementList(applicationManagement);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查找app异常");
			logger.error("Error:",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 根据appid查找
	 * @param appId
	 * @param active
	 * @return
	 */
	public ApplicationManagement selectApplicationManagementByAppId(String appId,byte active){
		ApplicationManagement applicationManagement=new ApplicationManagement();
		applicationManagement.setAppId(appId);
		applicationManagement.setActive(active);
		List<ApplicationManagement> list=selectApplicationManagementList(applicationManagement);
		return list.isEmpty()?null:list.get(0);
	}
	
	public String encodeMessage(ApplicationManagement applicationManagement,String message) 
	{
		try {
			
			EncryptUtil encryptUtil=new EncryptUtil(applicationManagement.getDeskey(),applicationManagement.getIvkey());
			return encryptUtil.encode(message);
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	public String decodeMessage(ApplicationManagement applicationManagement,String message) 
	{
		try {
			
			EncryptUtil encryptUtil=new EncryptUtil(applicationManagement.getDeskey(),applicationManagement.getIvkey());
			return encryptUtil.decode(message);
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
}

