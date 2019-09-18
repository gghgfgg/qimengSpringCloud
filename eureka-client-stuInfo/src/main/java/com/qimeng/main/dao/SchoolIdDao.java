package com.qimeng.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qimeng.main.entity.SchoolId;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年9月5日 下午3:50:02 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Mapper
public interface SchoolIdDao {
	static String tablename="auxiliary_school_id";
	
	static String fields="school_id,school_name,create_time,update_time";
	
	static String item="#{item.schoolId},#{item.schoolName},#{item.createTime},#{item.updateTime}";
	
	@Select("select id,"+fields+" from "+tablename)
	List<SchoolId> selectSchoolIdList();
	
	@Select("select id,"+fields+" from "+tablename+" where id=#{id}")
	SchoolId selectSchoolId(Integer id);
	
	@Insert("insert into "+tablename+"("+fields+") values" + "("+item+")")
	@Options(useGeneratedKeys = true,keyProperty = "item.id")
	int insertSchoolId(@Param("item")SchoolId schoolId);
	
	@Update("update "+tablename+" set school_id=#{item.schoolId},school_name=#{item.schoolName},update_time=#{item.updateTime} where id=#{item.id}")
	int updateSchoolId(@Param("item")SchoolId schoolId);
}

