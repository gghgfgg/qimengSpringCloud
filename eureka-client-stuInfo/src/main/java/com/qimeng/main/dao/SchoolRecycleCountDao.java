package com.qimeng.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import com.qimeng.main.entity.SchoolRecycleCount;
/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月5日 下午2:43:00 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Mapper
public interface SchoolRecycleCountDao {
	static String tablename="kernel_school_recycle_count";
	
	static String fields="school_code,type,count,points,remainder,activity_count,create_time,update_time";
	
	static String item="#{item.schoolCode},#{item.type},#{item.count},#{item.points},#{item.remainder},#{item.activityCount},#{item.createTime},#{item.updateTime}";
	
	static String update="count=VALUES(count),points=VALUES(points),remainder=VALUES(remainder),activity_count=VALUES(activity_count),update_time=VALUES(update_time)";
	
	@Insert("insert into "+tablename+"("+fields+") values" + "("+item+") ON DUPLICATE KEY UPDATE " + update )
	@Options(useGeneratedKeys = true,keyProperty = "id")
	int insertSchoolRecycleCount(@Param("item")SchoolRecycleCount schoolRecycleCount);
	
	@SelectProvider(type = SqlFactory.class,method = "selectSchoolRecycleCount")
	List<SchoolRecycleCount> selectSchoolRecycleCountList(@Param("item")SchoolRecycleCount schoolRecycleCount);
	
	public class SqlFactory extends SQL{
		
		public String selectSchoolRecycleCount(@Param("item")SchoolRecycleCount schoolRecycleCount){
	    	SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
	    	
	    	sql.SELECT("id,"+fields);
	    	sql.FROM(tablename);
	    	if(!StringUtils.isEmpty(schoolRecycleCount.getSchoolCode())){
	            sql.WHERE("school_code=#{item.schoolCode}");
	        }
	    	if(schoolRecycleCount.getType()!=null){
	        	 sql.WHERE("type=#{item.type}");
			}
	    	return sql.toString();
	    }
		
	}
}

