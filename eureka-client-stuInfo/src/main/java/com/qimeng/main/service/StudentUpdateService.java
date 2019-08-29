package com.qimeng.main.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.qimeng.main.entity.StudentData;
import com.qimeng.main.entity.StudentInform;

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

	public int insertStudentInformList(List<StudentInform> studentInformList, String schoolCode, Byte type) {
		try {

			boolean errBoolean = false;
			String errString = new String();
			for (int i = 0; i < studentInformList.size(); i++) {

				String cardString = studentInformList.get(i).getIdentityCard().replaceAll("\\s*", "");
				;
				studentInformList.get(i).setIdentityCard(StringUtils.isEmpty(cardString) ? null : cardString);

				String codeString = studentInformList.get(i).getStudentCode().replaceAll("\\s*", "");
				;
				studentInformList.get(i).setStudentCode(StringUtils.isEmpty(codeString) ? null : codeString);

				if (StringUtils.isEmpty(studentInformList.get(i).getName())
						|| StringUtils.isEmpty(studentInformList.get(i).getSex())
						|| StringUtils.isEmpty(studentInformList.get(i).getGrade())
						|| StringUtils.isEmpty(studentInformList.get(i).getClassS())) {
					errString += "{第" + String.valueOf(i + 2) + "行,姓名或性别或年级或班级为空},";
					errBoolean = true;
				}
				
				if (StringUtils.isEmpty(studentInformList.get(i).getIdentityCard())
						&& StringUtils.isEmpty(studentInformList.get(i).getStudentCode())) {
					errString += "{第" + String.valueOf(i + 2) + "行,身份证和学号都为空},";
					errBoolean = true;
				}
				if (!StringUtils.isEmpty(studentInformList.get(i).getIdentityCard())
						&& studentInformList.get(i).getIdentityCard().length() < 15) {
					System.out.println(StringUtils.isEmpty(studentInformList.get(i).getIdentityCard()));
					System.out.println(studentInformList.get(i).getIdentityCard().length());
					errString += "{第" + String.valueOf(i + 2) + "行,身份证长度不对},";
					errBoolean = true;
				}
				if (!StringUtils.isEmpty(studentInformList.get(i).getStudentCode())
						&& studentInformList.get(i).getStudentCode().length() < 15) {
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
				if(type!=null&&(type&0x02)!=0) {
					if (StringUtils.isEmpty(studentInformList.get(i).getTeacherPhone())) {
						errString += "{第" + String.valueOf(i + 2) + "行,手机号码为空,添加学生以外类型请输入手机号码},";
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
				int row=item+1;
				while(row<studentInformList.size()) {
					if (!StringUtils.isEmpty(studentInformList.get(item).getIdentityCard())) {
						if(studentInformList.get(item).getIdentityCard().equals(studentInformList.get(row).getIdentityCard())){
							errString += "{第" + String.valueOf(item + 2) + "行,与第"+String.valueOf(row + 2)+"行身份证号重复},";
							errBoolean = true;
						}
					}
					if(!StringUtils.isEmpty(studentInformList.get(item).getStudentCode())){
						if(studentInformList.get(item).getStudentCode().equals(studentInformList.get(row).getStudentCode())) {
							errString += "{第" + String.valueOf(item + 2) + "行,与第"+String.valueOf(row + 2)+"行学号号重复},";
							errBoolean = true;
						}
					}
					row++;
				}
			}
			if (errBoolean) {
				throw new RuntimeException(errString);
			}
			if (StringUtils.isEmpty(schoolCode) || schoolCode.length() < 11) {
				throw new RuntimeException("请填写学校校区编号");
			}
	
			
			studentInformService.insertStudentInformList(studentInformList);
			List<StudentData> list = new ArrayList<StudentData>();
			Date date = new Date();
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
			return studentDataService.selectStudentCountByUpdata(schoolCode, date);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public int insertStudentInform(StudentInform studentInform, String schoolCode, Byte type) {
		try {

			if (StringUtils.isEmpty(studentInform.getIdentityCard())
					&& StringUtils.isEmpty(studentInform.getStudentCode())) {
				throw new RuntimeException("身份证和学号都为空");
			}
			if (StringUtils.isEmpty(studentInform.getSchoolId())) {
				throw new RuntimeException("学校编号为空");
			}
			if (StringUtils.isEmpty(schoolCode)) {
				throw new RuntimeException("请填写学校校区编号");
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
			studentData.setType(type);
			Date date = new Date();
			studentData.setCreateTime(date);
			studentData.setUpdateTime(date);

			return studentDataService.insertStudentData(studentData);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public int updateStudentInformByIdentityCardOrStudentCode(StudentInform studentInform, String schoolCode,
			Byte type) {
		try {
			if (StringUtils.isEmpty(studentInform.getIdentityCard())
					&& StringUtils.isEmpty(studentInform.getStudentCode())) {
				throw new RuntimeException("身份证和学号都为空");
			}
			if (StringUtils.isEmpty(studentInform.getSchoolId())) {
				throw new RuntimeException("学校编号为空");
			}

			studentInformService.updateStudentInformByIdentityCardOrStudentCode(studentInform);

			StudentData studentData = new StudentData();
			studentData.setIdentityCard(studentInform.getIdentityCard());
			studentData.setStudentCode(studentInform.getStudentCode());
			studentData.setName(studentInform.getName());
			studentData.setSchoolCode(schoolCode);
			studentData.setType(type);
			Date date = new Date();
			studentData.setUpdateTime(date);

			return studentDataService.updateStudentDateByIdentityCardOrStrudentCode(studentData);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

}
