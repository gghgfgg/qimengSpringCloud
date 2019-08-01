package com.qimeng.main.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qimeng.main.entity.StudentInfo;

@Mapper
public interface StudentInfoDao {

	@Select("select nid as id,"
			+ "realname as name,"
			+ "qrid as stuCode,"
			+ "hbday as startTime, "
			+ "ajf as totalpoints,"
			+ "schoolid from qmhb_student where qrid=#{studcode}")
	public StudentInfo getStudent(@Param("studcode")String studcode);
	
	@Update("update qmhb_student set hbday = #{stuInfo.startTime} where qrid = #{stuInfo.stuCode}")
	public int UpdataStudentInfo(@Param("stuInfo")StudentInfo stuInfo);
}
