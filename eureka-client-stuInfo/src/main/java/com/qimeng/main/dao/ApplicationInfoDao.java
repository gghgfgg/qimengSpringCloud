package com.qimeng.main.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qimeng.main.entity.ApplicationInfo;

@Mapper
public interface ApplicationInfoDao {
	
	@Select("select * from qmhb_application_anagement where appID=#{appID}")
	public ApplicationInfo getApplicationInfoByAppID(@Param("appID")String appId);

}
