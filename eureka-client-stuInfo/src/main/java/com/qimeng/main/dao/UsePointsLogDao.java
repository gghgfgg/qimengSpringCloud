package com.qimeng.main.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;


import com.qimeng.main.entity.UsePointsLog;

@Mapper
public interface UsePointsLogDao {

	@Insert("insert into qmhb_usepoints_log(student_name,student_code,used_points,create_time,app_id,mark)"
			+ "value(#{studentName},#{studentCode},#{usedPoints},#{createTime},#{appId},#{mark})")
	@Options(useGeneratedKeys = true,keyProperty = "id")
	public int insertPointsLog(UsePointsLog usepointsLog);
}
