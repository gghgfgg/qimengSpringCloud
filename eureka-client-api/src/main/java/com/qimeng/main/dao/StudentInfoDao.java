package com.qimeng.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qimeng.main.entity.StudentInfo;
import com.qimeng.main.vo.RecycleLogVo;

@Mapper
public interface StudentInfoDao {

	@Select("select nid as id,"
			+ "realname as name,"
			+ "qrid as stuCode,"
			+ "card as stuCard,"
			+ "rootid as bind,"
			+ "hbday as startTime, "
			+ "njf as totalpoints,"
			+ "schoolid from qmhb_student where qrid=#{studcode}")
	public StudentInfo getStudent(@Param("studcode")String studcode);
	
	@Select("select nid as id,"
			+ "realname as name,"
			+ "qrid as stuCode,"
			+ "card as stuCard,"
			+ "rootid as bind,"
			+ "hbday as startTime, "
			+ "njf as totalpoints,"
			+ "schoolid from qmhb_student where nid=#{id}")
	public StudentInfo getStudentByID(int id);
	
	@Select("select nid as id,"
			+ "realname as name,"
			+ "qrid as stuCode,"
			+ "card as stuCard,"
			+ "rootid as bind,"
			+ "hbday as startTime, "
			+ "njf as totalpoints,"
			+ "schoolid from qmhb_student where mobile=#{phone}")
	public StudentInfo getStudentByPhone(String phone);
	
	@Select("select snum as count,"
			+ "jifen as points,"
			+ "adddate as createTime from qmhb_jifen where stuid=#{id} ORDER BY adddate DESC limit 7")
	public List<RecycleLogVo> getRecycleLog(int id);
	
	
	@Update("update qmhb_machine_recycle set rotcode=#{serialNumber} where maid=#{machineID}")
	public int updateMachineID(@Param("machineID")String machineID,@Param("serialNumber")String serialNumber);
}
