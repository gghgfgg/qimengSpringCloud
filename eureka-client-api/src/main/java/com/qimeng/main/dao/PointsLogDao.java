package com.qimeng.main.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.qimeng.main.entity.PointsLog;

@Mapper
public interface PointsLogDao {

	@Insert("insert into qmhb_jifen(classtype,snum,jifen,adddate,stuid,maid)"
			+ "value(#{pointsLog.wasteType},#{pointsLog.unit},#{pointsLog.points},#{pointsLog.uploadTime},#{pointsLog.stuId},#{pointsLog.machineID})")
	@Options(useGeneratedKeys = true,keyProperty = "pointsLog.id",keyColumn = "nid")
	public int insertPointsLog(@Param("pointsLog")PointsLog pointsLog);
}
