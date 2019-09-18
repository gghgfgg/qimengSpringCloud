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
	@Autowired
	StudentRankService studentRankService;

	public PageInfo<SchoolInfoVo> schoolPageList(Integer pageNum, SchoolInfoVo schoolInfoVo) {
		// TODO Auto-generated method stub
		
		List<SchoolInform> schoollist=getSchoolInformList(schoolInfoVo.getSchoolCode(),schoolInfoVo.getSchoolId(),schoolInfoVo.getPostalCode(),schoolInfoVo.getSchoolName());
		if(schoollist==null) {
			schoolInfoVo.setSchoolCode("0");
		}
		else
		{   
			String schoolcode ="";
			for (SchoolInform schoolInform : schoollist) {
				schoolcode += schoolInform.getSchoolCode();
				schoolcode += "-";
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
					if (schoolContactsType == null) {
						schoolContactsType = new SchoolContactsType();
						schoolContactsType.setPosition("");
						schoolContactsType.setWeight((byte) 0);
					}
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
			item.setHeadcount((int) studentRankService.SchoolCount(item.getSchoolCode()));
			item.setStudentcount((int) studentRankService.StudentCount(item.getSchoolCode()));
			item.setStudentactcount((int) studentRankService.StudentActCount(item.getSchoolCode()));
			item.setTeachercount((int) studentRankService.TeacherCount(item.getSchoolCode()));
			item.setTeacheractcount((int) studentRankService.TeacherActCount(item.getSchoolCode()));
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

		List<RecycleCountVo> listRecycleCountVo = new ArrayList<RecycleCountVo>();
		List<SchoolContactsVo> schoolContactsVoList = new ArrayList<SchoolContactsVo>();

		List<SchoolRecycleCount> list = schoolRecycleCountService
				.selectSchoolRecycleCountBySchoolCode(schoolInfoVo.getSchoolCode());
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

			List<SchoolContacts> schoolContactslist = JSON.parseObject(schoolInform.getContacts(),
					new TypeReference<List<SchoolContacts>>() {
					});
			for (SchoolContacts schoolContacts : schoolContactslist) {
				SchoolContactsType schoolContactsType = schoolContactsTypeService
						.selectSchoolContactsTypeByType(schoolContacts.getType());
				if (schoolContactsType == null) {
					schoolContactsType = new SchoolContactsType();
					schoolContactsType.setPosition("");
					schoolContactsType.setWeight((byte) 0);
				}
				if (schoolContactsType.getWeight() == 0
						|| (schoolContactsType.getWeight() & schoolInfoVo.getWeight()) != 0) {
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

		}
		schoolInfoVo.setListSchoolContactsVo(schoolContactsVoList);
		schoolInfoVo.setListRecycleCountVo(listRecycleCountVo);

		// SchoolInfoVo countInfoVo=joinDao.SchoolCount(schoolInfoVo.getSchoolCode());

		schoolInfoVo.setHeadcount((int) studentRankService.SchoolCount(schoolInfoVo.getSchoolCode()));
		schoolInfoVo.setStudentcount((int) studentRankService.StudentCount(schoolInfoVo.getSchoolCode()));
		schoolInfoVo.setStudentactcount((int) studentRankService.StudentActCount(schoolInfoVo.getSchoolCode()));
		schoolInfoVo.setTeachercount((int) studentRankService.TeacherCount(schoolInfoVo.getSchoolCode()));
		schoolInfoVo.setTeacheractcount((int) studentRankService.TeacherActCount(schoolInfoVo.getSchoolCode()));
		return schoolInfoVo;
	}

	public int saveSchoolinfo(SchoolInfoVo schoolInfoVo) {
		// TODO Auto-generated method stub
		SchoolInform schoolInform = new SchoolInform();
		schoolInform.setActive(schoolInfoVo.getActive());
		schoolInform.setAddress(schoolInfoVo.getAddress());
		schoolInform.setPostalCode(schoolInfoVo.getPostalCode());
		schoolInform.setSchoolCode(schoolInfoVo.getSchoolCode());
		schoolInform.setSchoolName(schoolInfoVo.getSchoolName());
		schoolInform.setSchoolId(schoolInfoVo.getSchoolId());
		Date date = new Date();
		schoolInform.setCreateTime(date);
		schoolInform.setUpdateTime(date);
		List<SchoolContacts> schoolContactslist = new ArrayList<SchoolContacts>();
		List<SchoolContactsVo> schoolContactsVoList = schoolInfoVo.getListSchoolContactsVo();
		if (schoolContactsVoList != null && !schoolContactsVoList.isEmpty()) {
			for (SchoolContactsVo schoolContactsVo : schoolContactsVoList) {
				SchoolContacts schoolContacts = new SchoolContacts();
				schoolContacts.setName(schoolContactsVo.getName());
				schoolContacts.setPhone(schoolContactsVo.getPhone());
				schoolContacts.setType(schoolContactsVo.getType());
				schoolContactslist.add(schoolContacts);
			}
		}
		String contacts = null;
		if (!schoolContactslist.isEmpty()) {
			contacts = JSON.toJSONString(schoolContactslist);
		}
		schoolInform.setContacts(contacts);
		return schoolInformService.insertSchoolInform(schoolInform);
	}

	public int updateSchoolinfo(SchoolInfoVo schoolInfoVo) {
		// TODO Auto-generated method stub
		SchoolInform schoolInform = new SchoolInform();
		schoolInform.setActive(schoolInfoVo.getActive());
		schoolInform.setAddress(schoolInfoVo.getAddress());
		schoolInform.setPostalCode(schoolInfoVo.getPostalCode());
		schoolInform.setSchoolCode(schoolInfoVo.getSchoolCode());
		schoolInform.setSchoolName(schoolInfoVo.getSchoolName());
		schoolInform.setSchoolId(schoolInfoVo.getSchoolId());
		Date date = new Date();
		schoolInform.setUpdateTime(date);
		List<SchoolContacts> schoolContactslist = new ArrayList<SchoolContacts>();
		List<SchoolContactsVo> schoolContactsVoList = schoolInfoVo.getListSchoolContactsVo();
		if (schoolContactsVoList != null && !schoolContactsVoList.isEmpty()) {
			for (SchoolContactsVo schoolContactsVo : schoolContactsVoList) {
				SchoolContacts schoolContacts = new SchoolContacts();
				schoolContacts.setName(schoolContactsVo.getName());
				schoolContacts.setPhone(schoolContactsVo.getPhone());
				schoolContacts.setType(schoolContactsVo.getType());
				schoolContactslist.add(schoolContacts);
			}
		}
		String contacts = null;
		if (!schoolContactslist.isEmpty()) {
			contacts = JSON.toJSONString(schoolContactslist);
		}
		schoolInform.setContacts(contacts);
		return schoolInformService.updateSchoolInform(schoolInform);
	}

	public List<SchoolInform> getSchoolInformList(String schoolCode, String schoolId, String postalCode,
			String schoolName) {
		List<SchoolInform> list = new ArrayList<SchoolInform>();
		if (!StringUtils.isEmpty(schoolCode)) {
			SchoolInform schoolInform = schoolInformService.selectSchoolInformBySchoolCode(schoolCode);
			if (schoolInform == null) {
				return null;
			}
			list.add(schoolInform);
		}
		if (!StringUtils.isEmpty(schoolId)) {
			return schoolInformService.selectSchoolInformBySchoolId(schoolId);
		}

		if (!StringUtils.isEmpty(postalCode)) {
			String codeString = postalCodeService.selectPostalCode(postalCode);
			List<SchoolInform> tempInforms = schoolInformService.selectSchoolCodeList(codeString);
			if (tempInforms.isEmpty() && StringUtils.isEmpty(schoolName)) {
				return null;
			}
			if (!tempInforms.isEmpty()) {
				list.addAll(tempInforms);
			}
		}
		if (!StringUtils.isEmpty(schoolName)) {

			List<SchoolInform> schoollistbyname = schoolInformService.selectSchoolInformByName(schoolName);
			if (!list.isEmpty()) {
				schoollistbyname.retainAll(list);
			}
			if (!schoollistbyname.isEmpty()) {
				return schoollistbyname;
			}
			if (list.isEmpty()) {
				return null;
			}
		}
		return list;
	}

}
