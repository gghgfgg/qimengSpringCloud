package com.qimeng.main.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qimeng.main.dao.GlobalDateDao;
import com.qimeng.main.entity.GlobalDate;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2019年8月15日 下午3:43:36
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Service
public class GlobalDateService {
	private static Logger logger = Logger.getLogger(GlobalDateService.class);
	public static Map<String, String> global = new HashMap<String, String>();
	@Autowired
	GlobalDateDao globalDateDao;

	public List<GlobalDate> selectGlobalDateList() {
		try {
			return globalDateDao.selectGlobalDateList();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取全局变量异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}

	public int insertGlobalDate(GlobalDate globalDate) {
		try {
			globalDateDao.insertGlobalDate(globalDate);
			if (global.isEmpty()) {
				global.clear();
			}
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("新增全局变量异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}

	public int updateGlobalDate(GlobalDate globalDate) {
		try {
			globalDateDao.updateGlobalDate(globalDate);
			if (global.isEmpty()) {
				global.clear();
			}
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新全局变量异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}

	public String getGlobalKeyString(String key) {
		if (global.get(key) == null) {
			List<GlobalDate> list = selectGlobalDateList();
			for (GlobalDate globalDate : list) {
				global.put(globalDate.getGlobalKey(), globalDate.getGlobalValue());
			}
		}
		logger.debug(global.get(key));
		return global.get(key);
	}
	
	public GlobalDate selectGlobalDate(Integer id) {
		try {
			return globalDateDao.selectGlobalDate(id);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取全局变量id异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	public PageInfo<GlobalDate> selectGlobalDatePageList(Integer pageNum) {
		PageHelper.startPage(pageNum, 20);
		List<GlobalDate> news = selectGlobalDateList();
		PageInfo<GlobalDate> appsPageInfo = new PageInfo<>(news);
		return appsPageInfo;
	}
	
	public Integer getRunTime() {
		long between_days = 0L;
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(getGlobalKeyString("startTime")));
            long time1 = cal.getTimeInMillis();
            cal.setTime(new Date());
            long time2 = cal.getTimeInMillis();
            between_days=(time2-time1)/(1000*3600*24);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return Integer.parseInt(String.valueOf(between_days));
	}
}
