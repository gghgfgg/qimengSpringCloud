package com.qimeng.main.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qimeng.main.entity.ApplicationInfo;

@Mapper
public interface ApplicationInfoDao {
	
	@Select("select * from qmhb_application_anagement where appID=#{appID}")
	public ApplicationInfo getApplicationInfoByAppID(@Param("appID")String appId);

	
	@Update("update qmhb_machine_recycle set rotcode=#{serialNumber} where maid=#{machineID}")
	public int updateMachineID(@Param("machineID")String machineID,@Param("serialNumber")String serialNumber);
}
