package com.qimeng.main.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.qimeng.main.entity.AppInform;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月30日 上午11:06:01 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Mapper
public interface AppInformDao {

	@Select("select * from app")
	AppInform selectAppInform();
	
}

