package com.qimeng.main.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.qimeng.main.entity.DecviceOpenCodeLog;


/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年9月18日 下午8:46:36 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Mapper
public interface DecviceOpenCodeLogDao {
	@Insert("insert into machine_device_opencode_log (uuid,create_time) values("
			+ "#{item.uuid},#{item.createTime})")
	@Options(useGeneratedKeys = true,keyProperty = "item.id")
	int  insertDecviceOpenCodeLog(@Param("item")DecviceOpenCodeLog decviceOpenCodeLog);
}

