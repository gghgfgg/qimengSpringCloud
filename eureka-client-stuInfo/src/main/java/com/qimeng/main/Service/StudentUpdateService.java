package com.qimeng.main.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	public int insertStudentInformList(List<StudentInform> studentInformList,byte type) {
		try {
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
				studentData.setSchoolCode(studentInform.getSchoolCode());
				studentData.setType(type);
				Date date=new Date();
				studentData.setCreateTime(date);
				studentData.setUpdateTime(date);
				list.add(studentData);
			}
			return studentDataService.insertStudentDataList(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public int 	insertStudentInform(StudentInform studentInform,byte type) {
		try {
			studentInformService.insertStudentInform(studentInform);
		
			StudentData studentData = new StudentData();
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			studentData.setUuid(uuid);
			studentData.setIdentityCard(studentInform.getIdentityCard());
			studentData.setStudentCode(studentInform.getStudentCode());
			studentData.setCard(uuid.substring(0, 16));
			studentData.setCode(uuid.substring(16));
			studentData.setName(studentInform.getName());
			studentData.setSchoolCode(studentInform.getSchoolCode());
			studentData.setType(type);
			Date date = new Date();
			studentData.setCreateTime(date);
			studentData.setUpdateTime(date);
					
			return studentDataService.insertStudentData(studentData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public int updateStudentInformByIdentityCardOrStudentCode(StudentInform studentInform,Byte type){
		try {
			studentInformService.insertStudentInform(studentInform);
		
			StudentData studentData = new StudentData();
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			studentData.setUuid(uuid);
			studentData.setIdentityCard(studentInform.getIdentityCard());
			studentData.setStudentCode(studentInform.getStudentCode());
			studentData.setName(studentInform.getName());
			studentData.setSchoolCode(studentInform.getSchoolCode());
			studentData.setType(type);
			Date date = new Date();
			studentData.setUpdateTime(date);
					
			return studentDataService.insertStudentData(studentData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public int updateStudentInformByIdentityCardOrStudentCode(StudentInform studentInform) {
		return updateStudentInformByIdentityCardOrStudentCode(studentInform,null);
	}
}

