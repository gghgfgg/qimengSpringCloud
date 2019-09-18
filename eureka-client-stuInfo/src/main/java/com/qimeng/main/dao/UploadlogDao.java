package com.qimeng.main.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.qimeng.main.entity.Uploadlog;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月16日 下午12:25:09 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Mapper
public interface UploadlogDao {

	@Insert("insert into upload_log (name,file,app_id,operator,create_time) values(#{item.appId},#{item.name},"
			+ "#{item.file},#{item.operator},#{item.createTime})")
	@Options(useGeneratedKeys = true,keyProperty = "item.id")
	int  insertUploadlog(@Param("item")Uploadlog uploadlog);
}

