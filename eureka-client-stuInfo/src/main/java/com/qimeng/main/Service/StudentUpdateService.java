package com.qimeng.main.Service;

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
 * @author  陈泽键
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
	
	public int insertStudentInformList(List<StudentInform> studentInformList,String schoolCode, byte type) {
		try {
			
			for (int i = 0; i < studentInformList.size(); i++) {
				{
					if(StringUtils.isEmpty(studentInformList.get(i).getIdentityCard())&&
					StringUtils.isEmpty(studentInformList.get(i).getStudentCode())) {
						throw new RuntimeException("第"+String.valueOf(i+1)+"行,身份证和学号都为空");
						}
					if(!StringUtils.isEmpty(studentInformList.get(i).getIdentityCard())&&
						studentInformList.get(i).getIdentityCard().length()<15) {
								throw new RuntimeException("第"+String.valueOf(i+1)+"行,身份证长度不对");
								}
					if(!StringUtils.isEmpty(studentInformList.get(i).getStudentCode())&&
							studentInformList.get(i).getStudentCode().length()<15) {
								throw new RuntimeException("第"+String.valueOf(i+1)+"行,学号长度不对");
								}
					if(StringUtils.isEmpty(studentInformList.get(i).getSchoolCode())) {
								throw new RuntimeException("第"+String.valueOf(i+1)+"行,学校编号为空");
					}
					if(!StringUtils.isEmpty(studentInformList.get(i).getSchoolCode())&&
							studentInformList.get(i).getSchoolCode().length()<10) {
						throw new RuntimeException("第"+String.valueOf(i+1)+"行,学校编号长度不对");
						}
				}
			} 
			if(StringUtils.isEmpty(schoolCode)) {
				throw new RuntimeException("请填写学校校区编号");
			}
			
			
			
			studentInformService.insertStudentInformList(studentInformList);
			List<StudentData> list=new ArrayList<StudentData>();
			
			for (StudentInform studentInform : studentInformList) {
				StudentData studentData=new StudentData();
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				studentData.setUuid(uuid);
				studentData.setIdentityCard(studentInform.getIdentityCard());
				studentData.setStudentCode(studentInform.getStudentCode());
				studentData.setCard(uuid.substring(0, 16));
				studentData.setCode(uuid.substring(16));
				studentData.setName(studentInform.getName());
				studentData.setSchoolCode(schoolCode);
				studentData.setType(type);
				Date date=new Date();
				studentData.setCreateTime(date);
				studentData.setUpdateTime(date);
				list.add(studentData);
			}
			return studentDataService.insertStudentDataList(list);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	public int 	insertStudentInform(StudentInform studentInform,String schoolCode,byte type) {
		try {
			
			if (StringUtils.isEmpty(studentInform.getIdentityCard())
					&& StringUtils.isEmpty(studentInform.getStudentCode())) {
				throw new RuntimeException("身份证和学号都为空");
			}
			if (StringUtils.isEmpty(studentInform.getSchoolCode())) {
				throw new RuntimeException("学校编号为空");
			}
			if(StringUtils.isEmpty(schoolCode)) {
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
	
	public int updateStudentInformByIdentityCardOrStudentCode(StudentInform studentInform,String schoolCode,Byte type){
		try {
			if (StringUtils.isEmpty(studentInform.getIdentityCard())
					&& StringUtils.isEmpty(studentInform.getStudentCode())) {
				throw new RuntimeException("身份证和学号都为空");
			}
			if (StringUtils.isEmpty(studentInform.getSchoolCode())) {
				throw new RuntimeException("学校编号为空");
			}
			
			studentInformService.insertStudentInform(studentInform);
		
			StudentData studentData = new StudentData();
			studentData.setIdentityCard(studentInform.getIdentityCard());
			studentData.setStudentCode(studentInform.getStudentCode());
			studentData.setName(studentInform.getName());
			studentData.setSchoolCode(schoolCode);
			studentData.setType(type);
			Date date = new Date();
			studentData.setUpdateTime(date);
					
			return studentDataService.insertStudentData(studentData);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	public int updateStudentInformByIdentityCardOrStudentCode(StudentInform studentInform,String schoolCode) {
		return updateStudentInformByIdentityCardOrStudentCode(studentInform,schoolCode,null);
	}
}

