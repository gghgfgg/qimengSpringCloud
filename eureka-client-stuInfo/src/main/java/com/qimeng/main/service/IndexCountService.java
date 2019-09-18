package com.qimeng.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.JoinDao;
import com.qimeng.main.entity.SchoolInform;
import com.qimeng.main.util.StaticGlobal;
import com.qimeng.main.vo.DeviceInformVo;
import com.qimeng.main.vo.IndexCountVo;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年9月10日 下午4:07:27 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class IndexCountService {

	@Autowired
	StudentRankService studentRankService;
	@Autowired
	SchoolService schoolService;
	@Autowired
	GlobalDateService globalDateService;
	@Autowired
	SchoolRecycleCountService schoolRecycleCountService;
	@Autowired
	JoinDao joinDao;
	public IndexCountVo getCount(IndexCountVo indexCountVo) {

		
		List<SchoolInform> schoollist=schoolService.getSchoolInformList(indexCountVo.getSchoolCode(),indexCountVo.getSchoolId(),indexCountVo.getPostalCode(),indexCountVo.getSchoolName());
		if(schoollist==null) {
			indexCountVo.setSchoolCode("0");
			indexCountVo.setDevActCount(0);
			indexCountVo.setDevCount(0);
			indexCountVo.setHeadActCount(0);
			indexCountVo.setHeadCount(0);
			indexCountVo.setTotalNumber(0);
			indexCountVo.setTotalPoints(0);
			indexCountVo.setTotalWeight(0);
			}
		else
		{   
			String schoolcode ="";
			if(schoollist.isEmpty()) {
				indexCountVo.setHeadActCount(Integer.parseInt(String.valueOf(studentRankService.StudentActCount(schoolcode)+studentRankService.TeacherActCount(schoolcode))));
				indexCountVo.setHeadCount(Integer.parseInt(String.valueOf(studentRankService.SchoolCount(schoolcode))));
			}
			
			for (SchoolInform schoolInform : schoollist) {
				schoolcode += schoolInform.getSchoolCode();
				schoolcode += "-";
			}
			indexCountVo.setSchoolCode(schoolcode);
		}
		indexCountVo.setTotalWeight(schoolRecycleCountService.selectRecycleCount(indexCountVo.getSchoolCode(),(byte)2));
		indexCountVo.setTotalNumber(schoolRecycleCountService.selectRecycleCount(indexCountVo.getSchoolCode(),(byte)1));
		indexCountVo.setTotalPoints(schoolRecycleCountService.selectPointsCount(indexCountVo.getSchoolCode(),null));
		
		DeviceInformVo deviceInformVo=new DeviceInformVo();
		deviceInformVo.setActive(StaticGlobal.ACTIVE);
		deviceInformVo.setSchoolCode(indexCountVo.getSchoolCode());
		List<DeviceInformVo> list=joinDao.selectDeviceInformVosList(deviceInformVo);
		indexCountVo.setDevCount(list.size());
		deviceInformVo.setStatus(StaticGlobal.ACTIVE);
		List<DeviceInformVo> listact=joinDao.selectDeviceInformVosList(deviceInformVo);
		indexCountVo.setDevActCount(listact.size());
		indexCountVo.setRunTime(globalDateService.getRunTime());
		return indexCountVo;
	}
}

