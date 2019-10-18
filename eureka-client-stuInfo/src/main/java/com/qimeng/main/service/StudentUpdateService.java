package com.qimeng.main.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.qimeng.main.entity.StudentData;
import com.qimeng.main.entity.StudentInform;
import com.qimeng.main.util.IdCardCheck;
import com.qimeng.main.util.StaticGlobal;

/**
 * 学生数据同步更新服务类
 * 
 * @author 陈泽键
 * @date 创建时间：2019年8月1日 下午8:58:59
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Transactional
@Service
public class StudentUpdateService {

	@Autowired
	StudentDataService studentDataService;
	@Autowired
	StudentInformService studentInformService;
	@Autowired
	StudentRankService studentRankService;

	public int insertStudentInformList(List<StudentInform> studentInformList, String schoolCode, Byte type) {
		try {
			boolean errBoolean = false;
			String errString = new String();
			if (!StringUtils.isEmpty(schoolCode)) {
				schoolCode= schoolCode.replaceAll("\\s*", "");
			}
			
			if (StringUtils.isEmpty(schoolCode) || schoolCode.length() < 10) {
				throw new RuntimeException("请填写学校校区编号");
			}

			for (int i = 0; i < studentInformList.size(); i++) {

				if (studentInformList.get(i).getIdentityCard()!=null) {
					String cardString = studentInformList.get(i).getIdentityCard().replaceAll("\\s*", "");

					studentInformList.get(i).setIdentityCard(StringUtils.isEmpty(cardString) ? null : cardString);
				}
				if (studentInformList.get(i).getStudentCode()!=null) {
					String codeString = studentInformList.get(i).getStudentCode().replaceAll("\\s*", "");

					studentInformList.get(i).setStudentCode(StringUtils.isEmpty(codeString) ? null : codeString);
				}
				if (studentInformList.get(i).getName()!=null) {
					String name = studentInformList.get(i).getName().replaceAll("\\s*", "");

					studentInformList.get(i).setName(StringUtils.isEmpty(name) ? null : name);
				}
				if (studentInformList.get(i).getSex()!=null) {
					String sex = studentInformList.get(i).getSex().replaceAll("\\s*", "");

					studentInformList.get(i).setSex(StringUtils.isEmpty(sex) ? null : sex);
				}
				if (studentInformList.get(i).getGrade()!=null) {
					String grade = studentInformList.get(i).getGrade().replaceAll("\\s*", "");

					studentInformList.get(i).setGrade(StringUtils.isEmpty(grade) ? null : grade);
				}
				if (studentInformList.get(i).getClassS()!=null) {
					String classS = studentInformList.get(i).getClassS().replaceAll("\\s*", "");

					studentInformList.get(i).setClassS(StringUtils.isEmpty(classS) ? null : classS);
				}
				if (studentInformList.get(i).getSchoolId()!=null) {
					String schoolId = studentInformList.get(i).getSchoolId().replaceAll("\\s*", "");

					studentInformList.get(i).setSchoolId(StringUtils.isEmpty(schoolId) ? null : schoolId);
				}
				if (studentInformList.get(i).getTeacherPhone()!=null) {
					String teacherPhone = studentInformList.get(i).getTeacherPhone().replaceAll("\\s*", "");

					studentInformList.get(i).setTeacherPhone(StringUtils.isEmpty(teacherPhone) ? null : teacherPhone);
				}
				
				
				if (StringUtils.isEmpty(studentInformList.get(i).getName())
						|| StringUtils.isEmpty(studentInformList.get(i).getSex())) {
					errString += "{第" + String.valueOf(i + 2) + "行,姓名或性别为空},";
					errBoolean = true;
				}
				if (type != null && (type & StaticGlobal.TEACHER) != StaticGlobal.TEACHER) {
					if (StringUtils.isEmpty(studentInformList.get(i).getGrade())
							|| StringUtils.isEmpty(studentInformList.get(i).getClassS())) {
						errString += "{第" + String.valueOf(i + 2) + "行,年级或班级为空},";
						errBoolean = true;
					}
					if (!StringUtils.isEmpty(studentInformList.get(i).getGrade())
							&& studentInformList.get(i).getGrade().indexOf("级") == -1) {
						errString += "{第" + String.valueOf(i + 2) + "行,班级格式不对},";
						errBoolean = true;
					}
				}

				if (StringUtils.isEmpty(studentInformList.get(i).getIdentityCard())
						&& StringUtils.isEmpty(studentInformList.get(i).getStudentCode())) {
					errString += "{第" + String.valueOf(i + 2) + "行,身份证和学号都为空},";
					errBoolean = true;
				}
				if (!StringUtils.isEmpty(studentInformList.get(i).getIdentityCard())
						&&( studentInformList.get(i).getIdentityCard().length() < 5
						|| studentInformList.get(i).getIdentityCard().length() > 18)) {
					errString += "{第" + String.valueOf(i + 2) + "行,身份证长度不对},";
					errBoolean = true;
				}
				if (!StringUtils.isEmpty(studentInformList.get(i).getIdentityCard())
						&& studentInformList.get(i).getIdentityCard().indexOf("E") != -1
						&& studentInformList.get(i).getIdentityCard().indexOf(".") != -1) {
					errString += "{第" + String.valueOf(i + 2) + "行,身份证格式不对},";
					errBoolean = true;
				}
				if (!StringUtils.isEmpty(studentInformList.get(i).getIdentityCard())
						&& studentInformList.get(i).getIdentityCard().endsWith("000")) {
					if(!IdCardCheck.checkIDNo(studentInformList.get(i).getIdentityCard()))
					{
						errString += "{第" + String.valueOf(i + 2) + "行,身份证校验最后3位为0，请确认身份证号有效并单独添加学生},";
					    errBoolean = true;
					}
				}
				if (!StringUtils.isEmpty(studentInformList.get(i).getStudentCode())
						&&( studentInformList.get(i).getStudentCode().length() < 15
						|| studentInformList.get(i).getStudentCode().length() > 19)) {
					errString += "{第" + String.valueOf(i + 2) + "行,学号长度不对},";
					errBoolean = true;
				}

				if (StringUtils.isEmpty(studentInformList.get(i).getSchoolId())) {
					errString += "{第" + String.valueOf(i + 2) + "行,学校编号为空},";
					errBoolean = true;
				}
				if (!StringUtils.isEmpty(studentInformList.get(i).getSchoolId())
						&& studentInformList.get(i).getSchoolId().length() < 10) {
					errString += "{第" + String.valueOf(i + 2) + "行,学校编号长度不对},";
					errBoolean = true;
				}
				if (!StringUtils.isEmpty(studentInformList.get(i).getSchoolId())
						&& schoolCode.indexOf(studentInformList.get(i).getSchoolId()) == -1) {

					errString += "{第" + String.valueOf(i + 2) + "行,学校编号不匹配},";
					errBoolean = true;
				}
				if (type != null && (type & StaticGlobal.PHONE) == StaticGlobal.PHONE) {
					if (StringUtils.isEmpty(studentInformList.get(i).getTeacherPhone())) {
						errString += "{第" + String.valueOf(i + 2) + "行,手机号码为空,添加普通学生以外类型请输入手机号码},";
						errBoolean = true;
					}

					if (!StringUtils.isEmpty(studentInformList.get(i).getTeacherPhone())
							&& studentInformList.get(i).getTeacherPhone().length() < 10) {
						errString += "{第" + String.valueOf(i + 2) + "行,手机号码长度不对},";
						errBoolean = true;
					}
				}
			}
			for (int item = 0; item < studentInformList.size(); item++) {
				int row = item + 1;
				while (row < studentInformList.size()) {
					if (!StringUtils.isEmpty(studentInformList.get(item).getIdentityCard())) {
						if (studentInformList.get(item).getIdentityCard()
								.equals(studentInformList.get(row).getIdentityCard())) {
							errString += "{第" + String.valueOf(item + 2) + "行,与第" + String.valueOf(row + 2)
									+ "行身份证号重复},";
							errBoolean = true;
						}
					}
					if (!StringUtils.isEmpty(studentInformList.get(item).getStudentCode())) {
						if (studentInformList.get(item).getStudentCode()
								.equals(studentInformList.get(row).getStudentCode())) {
							errString += "{第" + String.valueOf(item + 2) + "行,与第" + String.valueOf(row + 2)
									+ "行学号号重复},";
							errBoolean = true;
						}
					}
					row++;
				}
			}
			if (errBoolean) {
				throw new RuntimeException(errString);
			}

			studentInformService.insertStudentInformList(studentInformList);
			List<StudentData> list = new ArrayList<StudentData>();
			Date date = DateUtils.round(new Date(), Calendar.SECOND);
			for (StudentInform studentInform : studentInformList) {
				StudentData studentData = new StudentData();
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				studentData.setUuid(uuid);
				studentData.setIdentityCard(studentInform.getIdentityCard());
				studentData.setStudentCode(studentInform.getStudentCode());
				studentData.setCard(uuid.substring(0, 16));
				studentData.setCode(uuid.substring(16));
				studentData.setName(studentInform.getName());
				studentData.setSchoolCode(schoolCode);
				studentData.setType(type == null ? 0 : type);
				studentData.setCreateTime(date);
				studentData.setUpdateTime(date);
				list.add(studentData);
			}
			studentDataService.insertStudentDataList(list);

			studentRankService.addListSchoolCount(schoolCode);

			return studentDataService.selectStudentCountByUpdata(schoolCode, date);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public int insertStudentInform(StudentInform studentInform, String schoolCode, Byte type) {
		try {
			if (!StringUtils.isEmpty(schoolCode)) {
				schoolCode= schoolCode.replaceAll("\\s*", "");
			}
			if (StringUtils.isEmpty(schoolCode) || schoolCode.length() < 10) {
				throw new RuntimeException("请填写学校校区编号");
			}
			if (studentInform.getIdentityCard()!=null) {
				String cardString = studentInform.getIdentityCard().replaceAll("\\s*", "");

				studentInform.setIdentityCard(StringUtils.isEmpty(cardString) ? null : cardString);
			}
			if (studentInform.getStudentCode()!=null) {
				String codeString = studentInform.getStudentCode().replaceAll("\\s*", "");

				studentInform.setStudentCode(StringUtils.isEmpty(codeString) ? null : codeString);
			}
			if (studentInform.getName()!=null) {
				String name = studentInform.getName().replaceAll("\\s*", "");

				studentInform.setName(StringUtils.isEmpty(name) ? null : name);
			}
			if (studentInform.getSex()!=null) {
				String sex = studentInform.getSex().replaceAll("\\s*", "");

				studentInform.setSex(StringUtils.isEmpty(sex) ? null : sex);
			}
			if (studentInform.getGrade()!=null) {
				String grade = studentInform.getGrade().replaceAll("\\s*", "");

				studentInform.setGrade(StringUtils.isEmpty(grade) ? null : grade);
			}
			if (studentInform.getClassS()!=null) {
				String classS = studentInform.getClassS().replaceAll("\\s*", "");

				studentInform.setClassS(StringUtils.isEmpty(classS) ? null : classS);
			}
			if (studentInform.getSchoolId()!=null) {
				String schoolId = studentInform.getSchoolId().replaceAll("\\s*", "");

				studentInform.setSchoolId(StringUtils.isEmpty(schoolId) ? null : schoolId);
			}
			if (studentInform.getTeacherPhone()!=null) {
				String teacherPhone = studentInform.getTeacherPhone().replaceAll("\\s*", "");

				studentInform.setTeacherPhone(StringUtils.isEmpty(teacherPhone) ? null : teacherPhone);
			}
			
			if (StringUtils.isEmpty(studentInform.getName()) || StringUtils.isEmpty(studentInform.getSex())) {
				throw new RuntimeException("姓名或性别为空");
			}

			if (type != null && (type & StaticGlobal.TEACHER) != StaticGlobal.TEACHER) {
				if (StringUtils.isEmpty(studentInform.getGrade()) || StringUtils.isEmpty(studentInform.getClassS())) {
					throw new RuntimeException("年级或班级为空");
				}
				if (!StringUtils.isEmpty(studentInform.getGrade()) && studentInform.getGrade().indexOf("级") == -1) {
					throw new RuntimeException("班级格式不对");
				}
			}

			if (StringUtils.isEmpty(studentInform.getIdentityCard())
					&& StringUtils.isEmpty(studentInform.getStudentCode())) {
				throw new RuntimeException("身份证和学号都为空");
			}
			if (!StringUtils.isEmpty(studentInform.getIdentityCard()) && (studentInform.getIdentityCard().length() < 5
					|| studentInform.getIdentityCard().length() > 18)) {
				throw new RuntimeException("身份证长度不对");
			}
			if (!StringUtils.isEmpty(studentInform.getStudentCode()) && (studentInform.getStudentCode().length() < 15
					|| studentInform.getStudentCode().length() > 19)) {
				throw new RuntimeException("学号长度不对");
			}

			if (StringUtils.isEmpty(studentInform.getSchoolId())) {
				throw new RuntimeException("学校编号为空");
			}
			if (!StringUtils.isEmpty(studentInform.getSchoolId()) && studentInform.getSchoolId().length() < 10) {
				throw new RuntimeException("学校编号长度不对");
			}
			if (!StringUtils.isEmpty(studentInform.getSchoolId())
					&& schoolCode.indexOf(studentInform.getSchoolId()) == -1) {
				throw new RuntimeException("学校编号不匹配");
			}
			if (type != null && (type & StaticGlobal.PHONE) == StaticGlobal.PHONE) {
				if (StringUtils.isEmpty(studentInform.getTeacherPhone())) {
					throw new RuntimeException("手机号码为空,添加普通学生以外类型请输入手机号码");
				}

				if (!StringUtils.isEmpty(studentInform.getTeacherPhone())
						&& studentInform.getTeacherPhone().length() < 10) {
					throw new RuntimeException("手机号码长度不对");
				}
			}

			studentInformService.insertStudentInform(studentInform);

			StudentData studentData = new StudentData();
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			studentData.setUuid(uuid);
			studentData.setIdentityCard(studentInform.getIdentityCard());
			studentData.setStudentCode(studentInform.getStudentCode());
			studentData.setCard(uuid.substring(0, 16));
			studentData.setCode(uuid.substring(16));
			studentData.setName(studentInform.getName());
			studentData.setSchoolCode(schoolCode);
			studentData.setType(type == null ? 0 : type);
			Date date = new Date();
			studentData.setCreateTime(date);
			studentData.setUpdateTime(date);

			studentDataService.insertStudentData(studentData);
			studentRankService.addSchoolCount(studentData);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public int updateStudentInformByIdentityCardOrStudentCode(StudentInform studentInform, String schoolCode,
			Byte type) {
		try {
			if (!StringUtils.isEmpty(schoolCode)) {
				schoolCode= schoolCode.replaceAll("\\s*", "");
			}
			if (StringUtils.isEmpty(schoolCode) || schoolCode.length() < 10) {
				throw new RuntimeException("请填写学校校区编号");
			}
			if (!StringUtils.isEmpty(studentInform.getIdentityCard())) {
				String cardString = studentInform.getIdentityCard().replaceAll("\\s*", "");

				studentInform.setIdentityCard(StringUtils.isEmpty(cardString) ? null : cardString);
			}
			if (!StringUtils.isEmpty(studentInform.getStudentCode())) {
				String codeString = studentInform.getStudentCode().replaceAll("\\s*", "");

				studentInform.setStudentCode(StringUtils.isEmpty(codeString) ? null : codeString);
			}
			if (!StringUtils.isEmpty(studentInform.getName())) {
				String name = studentInform.getName().replaceAll("\\s*", "");

				studentInform.setName(StringUtils.isEmpty(name) ? null : name);
			}
			if (!StringUtils.isEmpty(studentInform.getSex())) {
				String sex = studentInform.getSex().replaceAll("\\s*", "");

				studentInform.setSex(StringUtils.isEmpty(sex) ? null : sex);
			}
			if (!StringUtils.isEmpty(studentInform.getGrade())) {
				String grade = studentInform.getGrade().replaceAll("\\s*", "");

				studentInform.setGrade(StringUtils.isEmpty(grade) ? null : grade);
			}
			if (!StringUtils.isEmpty(studentInform.getClassS())) {
				String classS = studentInform.getClassS().replaceAll("\\s*", "");

				studentInform.setClassS(StringUtils.isEmpty(classS) ? null : classS);
			}
			if (!StringUtils.isEmpty(studentInform.getSchoolId())) {
				String schoolId = studentInform.getSchoolId().replaceAll("\\s*", "");

				studentInform.setSchoolId(StringUtils.isEmpty(schoolId) ? null : schoolId);
			}
			if (!StringUtils.isEmpty(studentInform.getTeacherPhone())) {
				String teacherPhone = studentInform.getTeacherPhone().replaceAll("\\s*", "");

				studentInform.setTeacherPhone(StringUtils.isEmpty(teacherPhone) ? null : teacherPhone);
			}
			
			if (StringUtils.isEmpty(studentInform.getName()) || StringUtils.isEmpty(studentInform.getSex())) {
				throw new RuntimeException("姓名或性别为空");
			}

			if (type != null && (type & StaticGlobal.TEACHER) != StaticGlobal.TEACHER) {
				if (StringUtils.isEmpty(studentInform.getGrade()) || StringUtils.isEmpty(studentInform.getClassS())) {
					throw new RuntimeException("年级或班级为空");
				}
				if (!StringUtils.isEmpty(studentInform.getGrade()) && studentInform.getGrade().indexOf("级") == -1) {
					throw new RuntimeException("班级格式不对");
				}
			}

			if (StringUtils.isEmpty(studentInform.getIdentityCard())
					&& StringUtils.isEmpty(studentInform.getStudentCode())) {
				throw new RuntimeException("身份证和学号都为空");
			}
			if (!StringUtils.isEmpty(studentInform.getIdentityCard()) && (studentInform.getIdentityCard().length() < 5
					|| studentInform.getIdentityCard().length() > 18)) {
				throw new RuntimeException("身份证长度不对");
			}
			if (!StringUtils.isEmpty(studentInform.getStudentCode()) && (studentInform.getStudentCode().length() < 15
					|| studentInform.getStudentCode().length() > 19)) {
				throw new RuntimeException("学号长度不对");
			}

			if (StringUtils.isEmpty(studentInform.getSchoolId())) {
				throw new RuntimeException("学校编号为空");
			}
			if (!StringUtils.isEmpty(studentInform.getSchoolId()) && studentInform.getSchoolId().length() < 10) {
				throw new RuntimeException("学校编号长度不对");
			}
			if (!StringUtils.isEmpty(studentInform.getSchoolId())
					&& schoolCode.indexOf(studentInform.getSchoolId()) == -1) {
				throw new RuntimeException("学校编号不匹配");
			}
			if (type != null && (type & StaticGlobal.PHONE) == StaticGlobal.PHONE) {
				if (StringUtils.isEmpty(studentInform.getTeacherPhone())) {
					throw new RuntimeException("手机号码为空,添加普通学生以外类型请输入手机号码");
				}

				if (!StringUtils.isEmpty(studentInform.getTeacherPhone())
						&& studentInform.getTeacherPhone().length() < 10) {
					throw new RuntimeException("手机号码长度不对");
				}
			}
			studentInformService.updateStudentInformByIdentityCardOrStudentCode(studentInform);

			StudentData studentData = new StudentData();
			studentData.setIdentityCard(studentInform.getIdentityCard());
			studentData.setStudentCode(studentInform.getStudentCode());
			studentData.setName(studentInform.getName());
			studentData.setSchoolCode(schoolCode);
			studentData.setType(type == null ? 0 : type);
			Date date = new Date();
			studentData.setUpdateTime(date);

			studentDataService.updateStudentDateByIdentityCardOrStrudentCode(studentData);
			studentRankService.addSchoolCount(studentData);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

}
