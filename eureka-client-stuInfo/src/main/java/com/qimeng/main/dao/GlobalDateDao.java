package com.qimeng.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

	static String tablename="global_data";
	
	static String fields="global_key,global_value,create_time,update_time";
	
	static String item="#{item.globalKey},#{item.globalValue},#{item.createTime},#{item.updateTime}";
	
	@Select("select * from "+tablename)
	List<GlobalDate> selectGlobalDateList();
	
	@Select("select id,"+fields+" from "+tablename+" where id=#{id}")
	GlobalDate selectGlobalDate(Integer id);
	
	@Insert("insert into "+tablename+"("+fields+") values" + "("+item+")")
	@Options(useGeneratedKeys = true,keyProperty = "item.id")
	int insertGlobalDate(@Param("item")GlobalDate globalDate);
	
	@Update("update "+tablename+" set global_key=#{item.globalKey},global_value=#{item.globalValue},update_time=#{item.updateTime} where id=#{item.id}")
	int updateGlobalDate(@Param("item")GlobalDate globalDate);
}

