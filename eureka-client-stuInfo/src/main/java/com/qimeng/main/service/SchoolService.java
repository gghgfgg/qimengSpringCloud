package com.qimeng.main.service;
/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月19日 上午11:35:52 
* @version 1.0 
* @parameter  
* @since  
* @return  */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qimeng.main.dao.JoinDao;
import com.qimeng.main.entity.SchoolContacts;
import com.qimeng.main.entity.SchoolContactsType;
import com.qimeng.main.entity.SchoolInform;
import com.qimeng.main.entity.SchoolRecycleCount;
import com.qimeng.main.vo.RecycleCountVo;
import com.qimeng.main.vo.SchoolContactsVo;
import com.qimeng.main.vo.SchoolInfoVo;

@Service
public class SchoolService {
	@Autowired
	SchoolInformService schoolInformService;
	@Autowired
	JoinDao joinDao;
	@Autowired
	SchoolRecycleCountService schoolRecycleCountService;
	@Autowired
	SchoolContactsTypeService schoolContactsTypeService;
	@Autowired
	PostalCodeService postalCodeService;

	public PageInfo<SchoolInfoVo> schoolPageList(Integer pageNum, SchoolInfoVo schoolInfoVo) {
		// TODO Auto-generated method stub
		if (!StringUtils.isEmpty(schoolInfoVo.getPostalCode())) {
			String codeString = postalCodeService.selectPostalCode(schoolInfoVo.getPostalCode());
			System.out.println(codeString);
			schoolInfoVo.setPostalCode(codeString);
		}
		if (!StringUtils.isEmpty(schoolInfoVo.getSchoolName())) {
			String schoolcode = new String();
			List<SchoolInform> schoollist = schoolInformService.selectSchoolInformByName(schoolInfoVo.getSchoolName());
			for (SchoolInform schoolInform : schoollist) {
				schoolcode += schoolInform.getSchoolCode();
				schoolcode += "-";
			}
			if (schoolInfoVo.getSchoolCode() != null) {
				schoolcode = schoolInfoVo.getSchoolCode() + "-" + schoolcode;
			}
			schoolInfoVo.setSchoolCode(schoolcode);
		}
		PageInfo<SchoolInfoVo> schoolPageInfo = selectSchoolPageList(pageNum, schoolInfoVo);
		for (SchoolInfoVo item : schoolPageInfo.getList()) {
			if (!StringUtils.isEmpty(item.getContacts())) {
				List<SchoolContactsVo> schoolContactsVoList = new ArrayList<SchoolContactsVo>();
				List<SchoolContacts> schoolContactslist = JSON.parseObject(item.getContacts(),
						new TypeReference<List<SchoolContacts>>() {
						});
				for (SchoolContacts schoolContacts : schoolContactslist) {
					SchoolContactsType schoolContactsType = schoolContactsTypeService
							.selectSchoolContactsTypeByType(schoolContacts.getType());
					if (schoolContactsType.getWeight() == 0) {
						SchoolContactsVo schoolContactsVo = new SchoolContactsVo();
						schoolContactsVo.setName(schoolContacts.getName());
						schoolContactsVo.setPhone(schoolContacts.getPhone());
						schoolContactsVo.setType(schoolContacts.getType());
						schoolContactsVo.setPosition(schoolContactsType.getPosition());
						schoolContactsVo.setWeight(schoolContactsType.getWeight());
						schoolContactsVoList.add(schoolContactsVo);
					}
				}
				item.setContacts(null);
				item.setListSchoolContactsVo(schoolContactsVoList);
			}
		}
		return schoolPageInfo;
	}

	private PageInfo<SchoolInfoVo> selectSchoolPageList(Integer pageNum, SchoolInfoVo schoolInfoVo) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, 20);
		List<SchoolInfoVo> news = joinDao.selectSchoolInformVosList(schoolInfoVo);
		PageInfo<SchoolInfoVo> devPageInfo = new PageInfo<>(news);
		return devPageInfo;
	}

	public SchoolInfoVo schoolInform(SchoolInfoVo schoolInfoVo) {
		// TODO Auto-generated method stub

		SchoolInform schoolInform = schoolInformService.selectSchoolInformBySchoolCode(schoolInfoVo.getSchoolCode());
		schoolInfoVo.setActive(schoolInfoVo.getActive());
		schoolInfoVo.setAddress(schoolInfoVo.getAddress());
		schoolInfoVo.setContacts(schoolInfoVo.getContacts());
		schoolInfoVo.setPostalCode(schoolInfoVo.getPostalCode());
		schoolInfoVo.setSchoolCode(schoolInfoVo.getSchoolCode());
		schoolInfoVo.setSchoolId(schoolInfoVo.getSchoolId());
		schoolInfoVo.setSchoolName(schoolInfoVo.getSchoolName());

		schoolInfoVo.setCreateTime(schoolInfoVo.getCreateTime());
		schoolInfoVo.setUpdateTime(schoolInfoVo.getUpdateTime());
		schoolInfoVo.setPostalCodename(postalCodeService.selectPostalCodeName(schoolInform.getPostalCode()));

		List<SchoolRecycleCount> list = schoolRecycleCountService
				.selectSchoolRecycleCountBySchoolCode(schoolInfoVo.getSchoolCode());
		List<RecycleCountVo> listRecycleCountVo = new ArrayList<RecycleCountVo>();
		
		for (SchoolRecycleCount schoolRecycleCount : list) {
			RecycleCountVo recycleCountVo = new RecycleCountVo();
			recycleCountVo.setType(schoolRecycleCount.getType());
			recycleCountVo.setActivityCount(schoolRecycleCount.getActivityCount());
			recycleCountVo.setCount(schoolRecycleCount.getCount());
			recycleCountVo.setRemainder(schoolRecycleCount.getRemainder());
			recycleCountVo.setPoints(schoolRecycleCount.getPoints());
			listRecycleCountVo.add(recycleCountVo);
		}
		if (!StringUtils.isEmpty(schoolInform.getContacts())) {
			List<SchoolContactsVo> schoolContactsVoList = new ArrayList<SchoolContactsVo>();
			List<SchoolContacts> schoolContactslist = JSON.parseObject(schoolInform.getContacts(),
					new TypeReference<List<SchoolContacts>>() {
					});
			for (SchoolContacts schoolContacts : schoolContactslist) {
				SchoolContactsType schoolContactsType = schoolContactsTypeService
						.selectSchoolContactsTypeByType(schoolContacts.getType());
				if(schoolContactsType==null) {
					throw new RuntimeException("没有当前联系人类型");
				}
				if (schoolContactsType.getWeight() == 0|| (schoolContactsType.getWeight()&schoolInfoVo.getWeight())!=0) {
					SchoolContactsVo schoolContactsVo = new SchoolContactsVo();
					schoolContactsVo.setName(schoolContacts.getName());
					schoolContactsVo.setPhone(schoolContacts.getPhone());
					schoolContactsVo.setType(schoolContacts.getType());
					schoolContactsVo.setPosition(schoolContactsType.getPosition());
					schoolContactsVo.setWeight(schoolContactsType.getWeight());
					schoolContactsVoList.add(schoolContactsVo);
				}
			}
			schoolInfoVo.setContacts(null);
			schoolInfoVo.setListSchoolContactsVo(schoolContactsVoList);
		}
		schoolInfoVo.setListRecycleCountVo(listRecycleCountVo);

		SchoolInfoVo countInfoVo=joinDao.SchoolCount(schoolInfoVo.getSchoolCode());
		schoolInfoVo.setHeadcount(countInfoVo.getHeadcount());
		schoolInfoVo.setStudentcount(countInfoVo.getStudentcount());
		schoolInfoVo.setStudentactcount(countInfoVo.getStudentactcount());
		schoolInfoVo.setTeachercount(countInfoVo.getTeachercount());
		schoolInfoVo.setTeacheractcount(countInfoVo.getTeacheractcount());
		return schoolInfoVo;
	}

	public int saveSchoolinfo(SchoolInfoVo schoolInfoVo) {
		// TODO Auto-generated method stub
		SchoolInform schoolInform=new SchoolInform();
		schoolInform.setActive(schoolInfoVo.getActive());
		schoolInform.setAddress(schoolInfoVo.getAddress());
		schoolInform.setPostalCode(schoolInfoVo.getPostalCode());
		schoolInform.setSchoolCode(schoolInfoVo.getSchoolCode());
		schoolInform.setSchoolName(schoolInfoVo.getSchoolName());
		schoolInform.setSchoolId(schoolInfoVo.getSchoolId());
		Date date=new Date();
		schoolInform.setCreateTime(date);
		schoolInform.setUpdateTime(date);
		List<SchoolContacts> schoolContactslist=new ArrayList<SchoolContacts>();
		List<SchoolContactsVo> schoolContactsVoList=schoolInfoVo.getListSchoolContactsVo();
		if(schoolContactsVoList!=null&&!schoolContactsVoList.isEmpty()) {
			for (SchoolContactsVo schoolContactsVo : schoolContactsVoList) {
				SchoolContacts schoolContacts=new SchoolContacts();
				schoolContacts.setName(schoolContactsVo.getName());
				schoolContacts.setPhone(schoolContactsVo.getPhone());
				schoolContacts.setType(schoolContactsVo.getType());
				schoolContactslist.add(schoolContacts);
			}
		}
		String contacts=null;
		if(!schoolContactslist.isEmpty()) {
			contacts=JSON.toJSONString(schoolContactslist);
		}	
		schoolInform.setContacts(contacts);
		return schoolInformService.insertSchoolInform(schoolInform);
	}

	public int updateSchoolinfo(SchoolInfoVo schoolInfoVo) {
		// TODO Auto-generated method stub
		SchoolInform schoolInform=new SchoolInform();
		schoolInform.setActive(schoolInfoVo.getActive());
		schoolInform.setAddress(schoolInfoVo.getAddress());
		schoolInform.setPostalCode(schoolInfoVo.getPostalCode());
		schoolInform.setSchoolCode(schoolInfoVo.getSchoolCode());
		schoolInform.setSchoolName(schoolInfoVo.getSchoolName());
		schoolInform.setSchoolId(schoolInfoVo.getSchoolId());
		Date date=new Date();
		schoolInform.setUpdateTime(date);
		List<SchoolContacts> schoolContactslist=new ArrayList<SchoolContacts>();
		List<SchoolContactsVo> schoolContactsVoList=schoolInfoVo.getListSchoolContactsVo();
		if(schoolContactsVoList!=null&&!schoolContactsVoList.isEmpty()) {
			for (SchoolContactsVo schoolContactsVo : schoolContactsVoList) {
				SchoolContacts schoolContacts=new SchoolContacts();
				schoolContacts.setName(schoolContactsVo.getName());
				schoolContacts.setPhone(schoolContactsVo.getPhone());
				schoolContacts.setType(schoolContactsVo.getType());
				schoolContactslist.add(schoolContacts);
			}
		}
		String contacts=null;
		if(!schoolContactslist.isEmpty()) {
			contacts=JSON.toJSONString(schoolContactslist);
		}
		schoolInform.setContacts(contacts);
		return schoolInformService.updateSchoolInform(schoolInform);
	}

}
