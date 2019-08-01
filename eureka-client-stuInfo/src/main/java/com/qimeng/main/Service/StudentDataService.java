package com.qimeng.main.Service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.StudentDataDao;
import com.qimeng.main.entity.StudentData;

/**
 * @author 陈泽键
 *
 */
@Service
public class StudentDataService {
	private static Logger logger = Logger.getLogger(StudentDataService.class); 
	
	@Autowired
	StudentDataDao studentDataDao;
	
	/**
	 * 批量导入学生数据
	 * @param studentDataList
	 * @return 新增加行数  如果更新 返回影响行数*2
	 */
	public int insertStudentDataList(List<StudentData> studentDataList) {
		try {
			return studentDataDao.insertStudentDataList(studentDataList);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("学生数据批量导入异常");
			e.printStackTrace();
			throw new RuntimeException(e);		
		}
	}
	
	/**
	 * 添加学生数据
	 * @param studentData
	 * @return 新增为1 更新为2
	 */
	public int insertStudentData(StudentData studentData) {
		try {
			return studentDataDao.insertStudentData(studentData);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("学生数据导入异常");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 更新学生数据
	 * @param studentData
	 * @return
	 */
	public int updateStudentDateByIdentityCardOrStrudentCode(StudentData studentData) {
		try {
			return studentDataDao.updateStudentDateByIdentityCardOrStrudentCode(studentData);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新学生数据异常,身份证号重复");
			e.printStackTrace();
			throw new RuntimeException(e);		
		}
	}
	
	/**
	 * 更新学生绑定信息
	 * @param studentData
	 * @return
	 */
	public int updateStudentBindingByIdentityCardOrStrudentCode(StudentData studentData) {
		StudentData stuData=new StudentData();
		stuData.setIdentityCard(studentData.getIdentityCard());
		stuData.setStudentCode(studentData.getStudentCode());
		stuData.setBinding(studentData.getBinding());
		stuData.setUpdateTime(studentData.getUpdateTime());
		return studentDataDao.updateStudentDateByIdentityCardOrStrudentCode(studentData);
	}
	
	/**
	 * 更新学生环保信息
	 * @param studentData
	 * @return
	 */
	public int updateStudentDateByUuid(StudentData studentData) {
		try {
			return studentDataDao.updateStudentDateByUuid(studentData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("找不到当前uuid");
			return -1;
		}
	}
	
	/**
	 * 更新学生环保活动信息
	 * @param studentData
	 * @return
	 */
	public int updateStudentActiveByUuid(StudentData studentData) {
		StudentData stuData=new StudentData();
		stuData.setUuid(studentData.getUuid());
		stuData.setActive(studentData.getActive());
		stuData.setUpdateTime(studentData.getUpdateTime());
		return updateStudentDateByUuid(studentData);
	}
	/**
	 * 更新学生首次活动信息
	 * @param studentData
	 * @return
	 */
	public int setStudentActiveByUuid(StudentData studentData) {
		StudentData stuData=new StudentData();
		stuData.setUuid(studentData.getUuid());
		stuData.setActive((byte) 1);
		stuData.setActivityCount(1);
		stuData.setTotalPoints(studentData.getTotalPoints());
		stuData.setFirstTime(studentData.getFirstTime());
		stuData.setUpdateTime(studentData.getUpdateTime());
		stuData.setLastTime(studentData.getLastTime());
		return updateStudentDateByUuid(stuData);
	}
	
	/**
	 * 更新学生积分信息
	 * @param studentData
	 * @return
	 */
	public int updateStudentTotalPoints(StudentData studentData) {
		StudentData stuData=new StudentData();
		stuData.setUuid(studentData.getUuid());
		stuData.setActivityCount(studentData.getActivityCount());
		stuData.setTotalPoints(studentData.getTotalPoints());
		stuData.setLastTime(studentData.getLastTime());
		return updateStudentDateByUuid(stuData);
	}
	
	/**
	 * 更新学生使用积分信息
	 * @param studentData
	 * @return
	 */
	public int updateStudentUsedPoints(StudentData studentData) {
		StudentData stuData=new StudentData();
		stuData.setUuid(studentData.getUuid());
		stuData.setUsedPoints(studentData.getUsedPoints());
		return updateStudentDateByUuid(stuData);
	}
	
	/**
	 * 更新学生积分异常修正信息
	 * @param studentData
	 * @return
	 */
	public int updateStudentDeductPoints(StudentData studentData) {
		StudentData stuData=new StudentData();
		stuData.setUuid(studentData.getUuid());
		stuData.setDeductPoints(studentData.getDeductPoints());
		return updateStudentDateByUuid(stuData);
	}
	
	/**
	 * 查询学生数据
	 * @param studentInform
	 * @return
	 */
	public List<StudentData> selectStudentData(StudentData studentData) {
		try {
			return studentDataDao.selectStudentData(studentData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("学生数据,查询异常");
			return null;
		}
	}
	
	
	/**
	 * 通过身份证查找学生
	 * @param identityCard
	 * @return
	 */
	public StudentData selectStudentDataByIdentityCard(String identityCard) {
		StudentData studentData=new StudentData();
		studentData.setIdentityCard(identityCard);
		List<StudentData> list=selectStudentData(studentData);
		return list.isEmpty()?null:list.get(0);
	}
	
	/**
	 * 通过学号查找学生
	 * @param identityCard
	 * @return
	 */
	public StudentData selectStudentDataByStudentCode(String studentCode) {
		StudentData studentData=new StudentData();
		studentData.setStudentCode(studentCode);
		List<StudentData> list=selectStudentData(studentData);
		return list.isEmpty()?null:list.get(0);
	}
	
	/**
	 * 通过uuid查找学生
	 * @param identityCard
	 * @return
	 */
	public StudentData selectStudentDataByUuid(String uuid) {
		StudentData studentData=new StudentData();
		studentData.setUuid(uuid);
		List<StudentData> list=selectStudentData(studentData);
		return list.isEmpty()?null:list.get(0);
	}
	
	/**
	 * 通过code和card查找学生
	 * @param identityCard
	 * @return
	 */
	public StudentData selectStudentDataByCodeAndCard(String code,String card) {
		StudentData studentData=new StudentData();
		studentData.setCard(card);
		studentData.setCode(code);
		List<StudentData> list=selectStudentData(studentData);
		return list.isEmpty()?null:list.get(0);
	}
	
	/**
	 * 根据条件查找学生
	 * @param identityCard
	 * @return
	 */
	public List<StudentData> selectStudentDataList(StudentData studentData) {
		studentData.setIdentityCard(null);
		studentData.setStudentCode(null);
		studentData.setUuid(null);
		studentData.setCard(null);
		studentData.setCode(null);
		return selectStudentData(studentData);
	}
}
