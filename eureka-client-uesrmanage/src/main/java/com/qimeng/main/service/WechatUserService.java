package com.qimeng.main.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.WecharUserDao;
import com.qimeng.main.entity.WecharUser;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月30日 下午4:29:06 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class WechatUserService {
	private static Logger logger = Logger.getLogger(WechatUserService.class);
	@Autowired
	WecharUserDao wechatUserDao;
	
	public int insertWecharUser(WecharUser wecharUser) {
		try {
			return wechatUserDao.insertWecharUser(wecharUser);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("新增微信用户信息异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}

	public int updateWecharUser(WecharUser wecharUser) {
		try {
			return wechatUserDao.updateWecharUser(wecharUser);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新微信用户信息异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	public List<WecharUser> selectWecharUserList(WecharUser wecharUser) {
		try {
			return wechatUserDao.selectWecharUserList(wecharUser);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查找微信用户信息异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	public WecharUser selectWecharUserByOpenidOrUnionid(String openid,String unionid) {
		WecharUser wecharUser=new WecharUser();
		wecharUser.setOpenid(openid);
		wecharUser.setUnionid(unionid);
		List<WecharUser> list=selectWecharUserList(wecharUser);
		return list.isEmpty()?null:list.get(0);
	}
}

