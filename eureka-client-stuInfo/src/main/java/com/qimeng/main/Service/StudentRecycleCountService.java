package com.qimeng.main.Service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.dao.StudentRecycleCountDao;
import com.qimeng.main.entity.StudentRecycleCount;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2019年8月2日 上午10:13:02
 * @version 1.0
 * @parameter
 * @since
 * @return
 */

@Service
public class StudentRecycleCountService {
	private static Logger logger = Logger.getLogger(StudentRecycleCountService.class);

	@Autowired
	StudentRecycleCountDao studentRecycleCountDao;

	/**
	 * 插入更新分类回收数据
	 * @param studentRecycleCount
	 * @return
	 */
	public int insertStudentRecycleCount(StudentRecycleCount studentRecycleCount) {
		try {
//			if (StringUtils.isEmpty(studentRecycleCount.getUuid())) {
//				throw new RuntimeException("uuid不能为空");
//			}
//			if (studentRecycleCount.getType() != null) {
//				throw new RuntimeException("回收类型不能为空");
//			}
//			if (studentRecycleCount.getCount() != null) {
//				throw new RuntimeException("总量不能为空");
//			}
//			if(studentRecycleCount.getPoints()!=null) {
//					throw new RuntimeException("总分不能为空");
//			}
//			if(studentRecycleCount.getRemainder()!=null) {
//				throw new RuntimeException("余数不能为空");
//			}
//			if(studentRecycleCount.getActivityCount()!=null) {
//				throw new RuntimeException("活动总数不能为空");
//			}
		

			return studentRecycleCountDao.insertStudentRecycleCount(studentRecycleCount);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("插入回收数据数据异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * 查找回收数据
	 * @param studentRecycleCount
	 * @return
	 */
	List<StudentRecycleCount> selectStudentRecycleCountList(StudentRecycleCount studentRecycleCount){
		try {
			return studentRecycleCountDao.selectStudentRecycleCountList(studentRecycleCount);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查找回收数据参数异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 根据uuid 查找回收
	 * @param uuid
	 * @return
	 */
	List<StudentRecycleCount> selectStudentRecycleCountByUuid(String uuid){
		StudentRecycleCount studentRecycleCount=new StudentRecycleCount();
		studentRecycleCount.setUuid(uuid);
		return selectStudentRecycleCountList(studentRecycleCount);
	}
	
	/**
	 * 根据回收类型  查找回收
	 * @param type
	 * @return
	 */
	List<StudentRecycleCount> selectStudentRecycleCountByType(byte type){
		StudentRecycleCount studentRecycleCount=new StudentRecycleCount();
		studentRecycleCount.setType(type);
		return selectStudentRecycleCountList(studentRecycleCount);
	}
	
	/**
	 * 根据回收类型uuid  查找回收
	 * @param type
	 * @return
	 */
	StudentRecycleCount selectStudentRecycleCount(String uuid,byte type){
		StudentRecycleCount studentRecycleCount=new StudentRecycleCount();
		studentRecycleCount.setUuid(uuid);
		studentRecycleCount.setType(type);
		List<StudentRecycleCount> list=selectStudentRecycleCountList(studentRecycleCount);
		return list.isEmpty()?null:list.get(0);
	}
}
