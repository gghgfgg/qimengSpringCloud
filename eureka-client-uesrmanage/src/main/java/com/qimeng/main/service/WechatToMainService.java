package com.qimeng.main.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.WechatToMainDao;
import com.qimeng.main.entity.WecharToMain;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2019年8月30日 下午9:35:12
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Service
public class WechatToMainService {

	private static Logger logger = Logger.getLogger(WechatToMainService.class);
	@Autowired
	WechatToMainDao wechatToMainDao;

	public int inserWechatToMain(WecharToMain wecharToMain) {
		try {
			return wechatToMainDao.inserWechatToMain(wecharToMain);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("新增用户关联信息异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}

	public int updateWechatToMain(WecharToMain wecharToMain) {
		try {
			return wechatToMainDao.updateWechatToMain(wecharToMain);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("新增用户关联信息异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}

	public List<WecharToMain> selecWechatToMainList(WecharToMain wecharToMain) {
		try {
			return wechatToMainDao.selecWechatToMainList(wecharToMain);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("新增用户关联信息异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	public WecharToMain selecWechatToMainByOpendIdAndUnionId(String openid,String unionid) {
		WecharToMain wecharToMain=new WecharToMain();
		wecharToMain.setOpenid(openid);
		wecharToMain.setUnionid(unionid);
		List<WecharToMain> list=selecWechatToMainList(wecharToMain);
		return list.isEmpty()?null:list.get(0);
	}
	
}
