package com.qimeng.main.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.StudentInfoDao;
import com.qimeng.main.entity.StudentInfo;
import com.qimeng.main.vo.RecycleLogVo;

@Service
public class StudentInfoService {
    @Autowired
	StudentInfoDao studentInfoDao;
    
    public StudentInfo getStudent(String studcode)
    {
    	return studentInfoDao.getStudent(studcode);
    }
    
    public StudentInfo getStudentByCodeCard(String studcode,String studcard) {
    	return studentInfoDao.getStudentByCodeCard(studcode,studcard);
    }
    
    public StudentInfo getStudentByCode(String studcode) {
    	return studentInfoDao.getStudentByCode(studcode);
    }
    
    public StudentInfo getStudentByID(int id) {
    	return studentInfoDao.getStudentByID(id);
    }
    
    public StudentInfo getStudentByPhone(String phone)
    {
    	return studentInfoDao.getStudentByPhone(phone);
    }
    
    public List<RecycleLogVo> getRecycleLog(int id) {
    	return studentInfoDao.getRecycleLog(id);
    }
    
    public int updateMachineID(String machineID,String serialNumber)
    {
    	return studentInfoDao.updateMachineID(machineID,serialNumber);
    }
}
