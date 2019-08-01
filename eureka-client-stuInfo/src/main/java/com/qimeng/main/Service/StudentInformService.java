package com.qimeng.main.Service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.StudentInformDao;
import com.qimeng.main.entity.StudentInform;

/**
 * @author 陈泽健
 *
 */
@Service
public class StudentInformService {
	private static Logger logger = Logger.getLogger(StudentInformService.class); 
	
	@Autowired
	StudentInformDao studentInformDao;
	
	/**
	 * 批量导入学生原始数据
	 * @param studentInformList
	 * @return 新增加行数  如果更新 返回影响行数*2
	 */
	public int insertStudentInformList(List<StudentInform> studentInformList)
	{
		try {
			return studentInformDao.insertStudentInformList(studentInformList);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("学生原始数据批量导入异常");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 添加学生数据
	 * @param studentInform
	 * @return 新增为1 更新为2
	 */
	public int 	insertStudentInform(StudentInform studentInform) {
		try {
			return studentInformDao.insertStudentInform(studentInform);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("学生原始数据导入异常");
			e.printStackTrace();
			throw new RuntimeException(e);		
		}
	}
	
	
	/**
	 * 更新学生数据
	 * @param studentInform
	 * @return
	 */
	public int updateStudentInformByIdentityCardOrStudentCode(StudentInform studentInform) {
		try {
			return studentInformDao.updateStudentInformByIdentityCardOrStudentCode(studentInform);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新学生原始数据异常,身份证号重复");
			e.printStackTrace();
			throw new RuntimeException(e);		
		}
	}
	
	/**
	 * 查询学生原始数据
	 * @param studentInform
	 * @return
	 */
	public List<StudentInform> selectStudentInform(StudentInform studentInform){
		try {
			return studentInformDao.selectStudentInform(studentInform);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("学生原始数据,查询异常");
			return null;
		}
	}
	
	/**
	 * 通过身份证查找学生
	 * @param identityCard
	 * @return
	 */
	public StudentInform selectStudentInformByIdentityCard(String identityCard) {
		StudentInform studentInform=new StudentInform();
		studentInform.setIdentityCard(identityCard);	
		 List<StudentInform> list=selectStudentInform(studentInform);
		 return list.isEmpty()?null:list.get(0);
	}
	
	/**
	 * 通过学号查找学生
	 * @param studentCode
	 * @return
	 */
	public StudentInform selectStudentInformByStudentCode(String studentCode) {
		StudentInform studentInform=new StudentInform();
		studentInform.setStudentCode(studentCode);
		List<StudentInform> list=selectStudentInform(studentInform);
		return list.isEmpty()?null:list.get(0);
	}
	
	/**
	 * 根据条件查找学生
	 * @param studentInform
	 * @return
	 */
	public List<StudentInform> selectStudentInformList(StudentInform studentInform){
		studentInform.setStudentCode(null);
		studentInform.setIdentityCard(null);
		return selectStudentInform(studentInform);		
	}
}
