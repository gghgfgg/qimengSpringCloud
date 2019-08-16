package com.qimeng.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.qimeng.main.entity.GlobalDate;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月15日 下午3:40:38 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Mapper
public interface GlobalDateDao {

	@Select("select * from global_data")
	List<GlobalDate> selectGlobalDateList();
}

