package com.qimeng.main.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.ApplicationInfoDao;
import com.qimeng.main.entity.ApplicationInfo;
import com.qimeng.main.util.EncryptUtil;
@Service
public class ApplicationInfoService {
	
	@Autowired
	ApplicationInfoDao applicationInfoDao;


	public ApplicationInfo getApplicationInfo(String appId)
	{
		return applicationInfoDao.getApplicationInfoByAppID(appId);
	}
	public int updateMachineID(String machineID,String serialNumber)
	{
		return applicationInfoDao.updateMachineID(machineID, serialNumber);
	}
	
	public String encodeMessage(ApplicationInfo appInfo,String message) 
	{
		try {
			
			EncryptUtil encryptUtil=new EncryptUtil(appInfo.getDesKey(),appInfo.getIvKey());
			return encryptUtil.encode(message);
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	public String decodeMessage(ApplicationInfo appInfo,String message) 
	{
		try {
			
			EncryptUtil encryptUtil=new EncryptUtil(appInfo.getDesKey(),appInfo.getIvKey());
			return encryptUtil.decode(message);
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
}
