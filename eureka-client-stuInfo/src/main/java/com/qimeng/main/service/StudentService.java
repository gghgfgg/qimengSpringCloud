package com.qimeng.main.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qimeng.main.dao.JoinDao;
import com.qimeng.main.entity.SchoolInform;
import com.qimeng.main.entity.StudentData;
import com.qimeng.main.entity.StudentInform;
import com.qimeng.main.entity.StudentType;
import com.qimeng.main.vo.StudentVo;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2019年8月15日 上午9:27:07
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Service
public class StudentService {
	@Autowired
	GlobalDateService globalDateService;
	@Autowired
	StudentDataService studentDataService;
	@Autowired
	SchoolInformService schoolInformService;
	@Autowired
	PostalCodeService postalCodeService;
	@Autowired
	StudentInformService studentInformService;
	@Autowired
	StudentUpdateService studentUpdateService;
	@Autowired
	JoinDao joinDao;
	@Autowired
	SchoolService schoolService;
	@Autowired
	StudentTypeService studentTypeService;
	
	public PageInfo<StudentVo> selectStudentPageList(Integer pageNum, StudentVo studentVo) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, 20);
		List<StudentVo> news = joinDao.selectStudentVosList(studentVo);
		PageInfo<StudentVo> stuPageInfo = new PageInfo<>(news);
		return stuPageInfo;
	}

	public PageInfo<StudentVo> StudentPageList(Integer page, StudentVo studentVo) {
		// TODO Auto-generated method stub
		List<SchoolInform> schoollist = schoolService.getSchoolInformList(studentVo.getSchoolCode(),
				studentVo.getSchoolId(), studentVo.getPostalCode(), studentVo.getSchoolName());
		if (schoollist == null) {
			studentVo.setSchoolCode("0");
		} else {
			String schoolcode = "";
			for (SchoolInform schoolInform : schoollist) {
				schoolcode += schoolInform.getSchoolCode();
				schoolcode += "-";
			}
			studentVo.setSchoolCode(schoolcode);
		}

		PageInfo<StudentVo> stuPageInfo = selectStudentPageList(page, studentVo);
		for (StudentVo item : stuPageInfo.getList()) {
//			StudentData studentData = studentDataService.selectStudentDataByUuid(item.getUuid());

//			item.setActive(studentData.getActive());
//			item.setActivityCount(studentData.getActivityCount());
//			item.setBinding(studentData.getBinding());
//			item.setDeductPoints(studentData.getDeductPoints());
//			item.setUsedPoints(studentData.getUsedPoints());
//			item.setTotalPoints(studentData.getTotalPoints());
//			item.setIdentityCard(studentData.getIdentityCard());
//			item.setStudentCode(studentData.getStudentCode());
//			item.setName(studentData.getName());
//			item.setType(studentData.getType());
//			item.setUuid(studentData.getUuid());
//			item.setFirstTime(studentData.getFirstTime());
//			item.setLastTime(studentData.getLastTime());
			StudentType studentType=studentTypeService.selectStudentType(item.getType());
			item.setTypeName(studentType==null?null:studentType.getMark());
			String qrcode = globalDateService.getGlobalKeyString("qrUrl") + "?code=" + item.getCode() + "&card="
					+ item.getCard();
			item.setQrcode(qrcode);
			SchoolInform schoolInform = schoolInformService.selectSchoolInformBySchoolCode(item.getSchoolCode());
			if (schoolInform != null) {
				item.setSchoolName(schoolInform.getSchoolName());
				item.setPostalCode(schoolInform.getPostalCode());
				item.setPostalCodename(postalCodeService.selectPostalCodeName(schoolInform.getPostalCode()));
				item.setPostalCode(schoolInform.getPostalCode());
			}
		}
		return stuPageInfo;
	}

	public StudentVo selectStudent(StudentVo studentVo) {
		// TODO Auto-generated method stub

		StudentData studentData = studentDataService.selectStudentDataByUuid(studentVo.getUuid());

		StudentInform studentInform = studentInformService.selectStudentInformByStudentCodeOrIdentityCard(
				studentData.getStudentCode(), studentData.getIdentityCard());
		if (studentInform == null) {
			throw new RuntimeException("当前身份证和学籍号找不到学生");
		}

		studentVo.setActive(studentData.getActive());
		studentVo.setActivityCount(studentData.getActivityCount());
		studentVo.setBinding(studentData.getBinding());
		studentVo.setDeductPoints(studentData.getDeductPoints());
		studentVo.setSchoolCode(studentData.getSchoolCode());
		studentVo.setTotalPoints(studentData.getTotalPoints());
		studentVo.setType(studentData.getType());
		studentVo.setUsedPoints(studentData.getUsedPoints());
		studentVo.setUuid(studentData.getUuid());
		studentVo.setFirstTime(studentData.getFirstTime());
		studentVo.setLastTime(studentData.getLastTime());

		String qrcode = globalDateService.getGlobalKeyString("qrUrl") + "?code=" + studentData.getCode() + "&card="
				+ studentData.getCard();
		studentVo.setQrcode(qrcode);

		studentVo.setAddress(studentInform.getAddress());
		studentVo.setAuxiliaryNumber(studentInform.getAuxiliaryNumber());
		studentVo.setBirthday(studentInform.getBirthday());
		studentVo.setBirthplace(studentInform.getBirthplace());
		studentVo.setBoarderAlimony(studentInform.getBoarderAlimony());
		studentVo.setCensusRegister(studentInform.getCensusRegister());
		studentVo.setCensusRegisterType(studentInform.getCensusRegisterType());
		studentVo.setClassS(studentInform.getClassS());
		studentVo.setContactAddress(studentInform.getContactAddress());
		studentVo.setDisabilityTypes(studentInform.getDisabilityTypes());
		studentVo.setEnrollmentTime(studentInform.getEnrollmentTime());
		studentVo.setEntranceWay(studentInform.getEntranceWay());

		studentVo.setFamilyCensusRegister(studentInform.getFamilyCensusRegister());
		studentVo.setFamilyName(studentInform.getFamilyName());
		studentVo.setFamilyPhone(studentInform.getFamilyPhone());
		studentVo.setFamilyRelationship(studentInform.getFamilyRelationship());
		studentVo.setFamilyResidence(studentInform.getFamilyResidence());
		studentVo.setFamilyWork(studentInform.getFamilyWork());
		studentVo.setFunding(studentInform.getFunding());
		studentVo.setGrade(studentInform.getGrade());
		studentVo.setHealth(studentInform.getHealth());
		studentVo.setIdentityCard(studentInform.getIdentityCard());
		studentVo.setIdentityType(studentInform.getIdentityType());

		studentVo.setLearningClass(studentInform.getLearningClass());
		studentVo.setLeftoverChildren(studentInform.getLeftoverChildren());
		studentVo.setMartyrChildren(studentInform.getMartyrChildren());
		studentVo.setName(studentInform.getName());
		studentVo.setNation(studentInform.getNation());
		studentVo.setNationality(studentInform.getNationality());
		studentVo.setNativePlace(studentInform.getNativePlace());
		studentVo.setOnlyChild(studentInform.getOnlyChild());
		studentVo.setOrphan(studentInform.getOrphan());
		studentVo.setOverseasChinese(studentInform.getOverseasChinese());

		studentVo.setPhone(studentInform.getPhone());
		studentVo.setPoliticsStatus(studentInform.getPoliticsStatus());
		studentVo.setPostalCode(studentInform.getPostalCode());
		studentVo.setPreschoolEducation(studentInform.getPreschoolEducation());
		studentVo.setResidence(studentInform.getResidence());
		studentVo.setSchoolId(studentInform.getSchoolId());
		studentVo.setSchoolName(studentInform.getSchoolName());
		studentVo.setSex(studentInform.getSex());
		studentVo.setStudentCode(studentInform.getStudentCode());
		studentVo.setStudentNumber(studentInform.getStudentNumber());
		studentVo.setStudentSource(studentInform.getStudentSource());
		studentVo.setStudyingWay(studentInform.getStudyingWay());
		studentVo.setWorkersChildren(studentInform.getWorkersChildren());

		return studentVo;
	}

	public int updataStudentActive(StudentVo studentVo) {
		// TODO Auto-generated method stub
		StudentData studentData = new StudentData();
		studentData.setUuid(studentVo.getUuid());
		studentData.setActive(studentVo.getActive());
		studentData.setUpdateTime(new Date());
		return studentDataService.updateStudentActiveByUuid(studentData);

	}

	public int savestudentData(StudentVo studentVo) {
		// TODO Auto-generated method stub
		if (!StringUtils.isEmpty(studentVo.getSchoolCode())) {
			studentVo.setSchoolCode(studentVo.getSchoolId() + "00");
		}
		StudentInform studentInform = new StudentInform();
		studentInform.setAddress(studentVo.getAddress());
		studentInform.setAuxiliaryNumber(studentVo.getAuxiliaryNumber());
		studentInform.setBirthday(studentVo.getBirthday());
		studentInform.setBirthplace(studentVo.getBirthplace());
		studentInform.setBoarderAlimony(studentVo.getBoarderAlimony());
		studentInform.setCensusRegister(studentVo.getCensusRegister());
		studentInform.setCensusRegisterType(studentVo.getCensusRegisterType());
		studentInform.setClassS(studentVo.getClassS());
		studentInform.setContactAddress(studentVo.getContactAddress());
		studentInform.setDisabilityTypes(studentVo.getDisabilityTypes());
		studentInform.setEnrollmentTime(studentVo.getEnrollmentTime());
		studentInform.setEntranceWay(studentVo.getEntranceWay());

		studentInform.setFamilyCensusRegister(studentVo.getFamilyCensusRegister());
		studentInform.setFamilyName(studentVo.getFamilyName());
		studentInform.setFamilyPhone(studentVo.getFamilyPhone());
		studentInform.setFamilyRelationship(studentVo.getFamilyRelationship());
		studentInform.setFamilyResidence(studentVo.getFamilyResidence());
		studentInform.setFamilyWork(studentVo.getFamilyWork());
		studentInform.setFunding(studentVo.getFunding());
		studentInform.setGrade(studentVo.getGrade());
		studentInform.setHealth(studentVo.getHealth());
		studentInform.setIdentityCard(studentVo.getIdentityCard());
		studentInform.setIdentityType(studentVo.getIdentityType());

		studentInform.setLearningClass(studentVo.getLearningClass());
		studentInform.setLeftoverChildren(studentVo.getLeftoverChildren());
		studentInform.setMartyrChildren(studentVo.getMartyrChildren());
		studentInform.setName(studentVo.getName());
		studentInform.setNation(studentVo.getNation());
		studentInform.setNationality(studentVo.getNationality());
		studentInform.setNativePlace(studentVo.getNativePlace());
		studentInform.setOnlyChild(studentVo.getOnlyChild());
		studentInform.setOrphan(studentVo.getOrphan());
		studentInform.setOverseasChinese(studentVo.getOverseasChinese());

		studentInform.setPhone(studentVo.getPhone());
		studentInform.setPoliticsStatus(studentVo.getPoliticsStatus());
		studentInform.setPostalCode(studentVo.getPostalCode());
		studentInform.setPreschoolEducation(studentVo.getPreschoolEducation());
		studentInform.setResidence(studentVo.getResidence());
		studentInform.setSchoolId(studentVo.getSchoolId());
		studentInform.setSchoolName(studentVo.getSchoolName());
		studentInform.setSex(studentVo.getSex());
		studentInform.setStudentCode(studentVo.getStudentCode());
		studentInform.setStudentNumber(studentVo.getStudentNumber());
		studentInform.setStudentSource(studentVo.getStudentSource());
		studentInform.setStudyingWay(studentVo.getStudyingWay());
		studentInform.setWorkersChildren(studentVo.getWorkersChildren());
		studentInform.setTeacherPhone(studentVo.getTeacherPhone());
		return studentUpdateService.insertStudentInform(studentInform, studentVo.getSchoolCode(), studentVo.getType());
	}

	public int updatestudentData(StudentVo studentVo) {
		// TODO Auto-generated method stub
		StudentInform studentInform = new StudentInform();

		studentInform.setAddress(studentVo.getAddress());
		studentInform.setAuxiliaryNumber(studentVo.getAuxiliaryNumber());
		studentInform.setBirthday(studentVo.getBirthday());
		studentInform.setBirthplace(studentVo.getBirthplace());
		studentInform.setBoarderAlimony(studentVo.getBoarderAlimony());
		studentInform.setCensusRegister(studentVo.getCensusRegister());
		studentInform.setCensusRegisterType(studentVo.getCensusRegisterType());
		studentInform.setClassS(studentVo.getClassS());
		studentInform.setContactAddress(studentVo.getContactAddress());
		studentInform.setDisabilityTypes(studentVo.getDisabilityTypes());
		studentInform.setEnrollmentTime(studentVo.getEnrollmentTime());
		studentInform.setEntranceWay(studentVo.getEntranceWay());

		studentInform.setFamilyCensusRegister(studentVo.getFamilyCensusRegister());
		studentInform.setFamilyName(studentVo.getFamilyName());
		studentInform.setFamilyPhone(studentVo.getFamilyPhone());
		studentInform.setFamilyRelationship(studentVo.getFamilyRelationship());
		studentInform.setFamilyResidence(studentVo.getFamilyResidence());
		studentInform.setFamilyWork(studentVo.getFamilyWork());
		studentInform.setFunding(studentVo.getFunding());
		studentInform.setGrade(studentVo.getGrade());
		studentInform.setHealth(studentVo.getHealth());
		studentInform.setIdentityCard(studentVo.getIdentityCard());
		studentInform.setIdentityType(studentVo.getIdentityType());

		studentInform.setLearningClass(studentVo.getLearningClass());
		studentInform.setLeftoverChildren(studentVo.getLeftoverChildren());
		studentInform.setMartyrChildren(studentVo.getMartyrChildren());
		studentInform.setName(studentVo.getName());
		studentInform.setNation(studentVo.getNation());
		studentInform.setNationality(studentVo.getNationality());
		studentInform.setNativePlace(studentVo.getNativePlace());
		studentInform.setOnlyChild(studentVo.getOnlyChild());
		studentInform.setOrphan(studentVo.getOrphan());
		studentInform.setOverseasChinese(studentVo.getOverseasChinese());

		studentInform.setPhone(studentVo.getPhone());
		studentInform.setPoliticsStatus(studentVo.getPoliticsStatus());
		studentInform.setPostalCode(studentVo.getPostalCode());
		studentInform.setPreschoolEducation(studentVo.getPreschoolEducation());
		studentInform.setResidence(studentVo.getResidence());
		studentInform.setSchoolId(studentVo.getSchoolId());
		studentInform.setSchoolName(studentVo.getSchoolName());
		studentInform.setSex(studentVo.getSex());
		studentInform.setStudentCode(studentVo.getStudentCode());
		studentInform.setStudentNumber(studentVo.getStudentNumber());
		studentInform.setStudentSource(studentVo.getStudentSource());
		studentInform.setStudyingWay(studentVo.getStudyingWay());
		studentInform.setWorkersChildren(studentVo.getWorkersChildren());
		studentInform.setTeacherPhone(studentVo.getTeacherPhone());
		return studentUpdateService.updateStudentInformByIdentityCardOrStudentCode(studentInform,
				studentVo.getSchoolCode(), studentVo.getType());
	}

	public List<StudentVo> StudentList(StudentVo studentVo) {
		// TODO Auto-generated method stub
		List<SchoolInform> schoollist = schoolService.getSchoolInformList(studentVo.getSchoolCode(),
				studentVo.getSchoolId(), studentVo.getPostalCode(), studentVo.getSchoolName());
		if (schoollist == null) {
			studentVo.setSchoolCode("0");
		} else {
			String schoolcode = "";
			for (SchoolInform schoolInform : schoollist) {
				schoolcode += schoolInform.getSchoolCode();
				schoolcode += "-";
			}
			studentVo.setSchoolCode(schoolcode);
		}

		List<StudentVo> studentVolist = joinDao.selectStudentVosList(studentVo);

		for (StudentVo item : studentVolist) {
//			StudentData studentData = studentDataService.selectStudentDataByUuid(item.getUuid());
//
//			item.setActive(studentData.getActive());
//			item.setActivityCount(studentData.getActivityCount());
//			item.setBinding(studentData.getBinding());
//			item.setDeductPoints(studentData.getDeductPoints());
//			item.setUsedPoints(studentData.getUsedPoints());
//			item.setTotalPoints(studentData.getTotalPoints());
//			item.setIdentityCard(studentData.getIdentityCard());
//			item.setStudentCode(studentData.getStudentCode());
//			item.setName(studentData.getName());
//			item.setType(studentData.getType());
//			item.setUuid(studentData.getUuid());
//			item.setFirstTime(studentData.getFirstTime());
//			item.setLastTime(studentData.getLastTime());
			StudentType studentType=studentTypeService.selectStudentType(item.getType());
			item.setTypeName(studentType==null?null:studentType.getMark());
			String qrcode = globalDateService.getGlobalKeyString("qrUrl") + "?code=" + item.getCode() + "&card="
					+ item.getCard();
			item.setQrcode(qrcode);
			SchoolInform schoolInform = schoolInformService.selectSchoolInformBySchoolCode(item.getSchoolCode());
			if (schoolInform != null) {
				item.setSchoolName(schoolInform.getSchoolName());
				item.setPostalCode(schoolInform.getPostalCode());
				item.setPostalCodename(postalCodeService.selectPostalCodeName(schoolInform.getPostalCode()));
				item.setPostalCode(schoolInform.getPostalCode());
			}
		}
		return studentVolist;
	}

	public StudentData selectStudentDataByPhone(String phone) {
		// TODO Auto-generated method stub
		StudentInform studentInform = studentInformService.selectStudentInformByPhone(phone);
		if (studentInform == null) {
			return null;
		}
		if (!StringUtils.isEmpty(studentInform.getIdentityCard())) {
			return studentDataService.selectStudentDataByIdentityCard(studentInform.getIdentityCard());
		}
		if (!StringUtils.isEmpty(studentInform.getStudentCode())) {
			return studentDataService.selectStudentDataByStudentCode(studentInform.getStudentCode());
		}
		return null;
	}

}
