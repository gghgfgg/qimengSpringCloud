package com.qimeng.main.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.StudentInfoDao;
import com.qimeng.main.entity.StudentInfo;

@Service
public class StudentInfoService {
    @Autowired
	StudentInfoDao studentInfoDao;
    
    public StudentInfo getStudent(String studcode)
    {
    	return studentInfoDao.getStudent(studcode);
    }
    
    public int UpdataStudentInfo(StudentInfo stuInfo)
    {
    	return studentInfoDao.UpdataStudentInfo(stuInfo);
    }
}
