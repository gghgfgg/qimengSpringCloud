package com.qimeng.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import com.qimeng.main.entity.StudentRecycleCount;

/** 
* @author  陈泽键: 
* @date 创建时间：2019年8月2日 上午9:11:04 
* @version 1.0 
* @parameter  
* @since  
* @return  */

@Mapper
public interface StudentRecycleCountDao {
	static String tablename="kernel_student_recycle_count";
	
	static String fields="uuid,type,count,points,remainder,activity_count,create_time,update_time";
	
	static String item="#{item.uuid},#{item.type},#{item.count},#{item.points},#{item.remainder},#{item.activityCount},#{item.createTime},#{item.updateTime}";
	
	static String update="count=VALUES(count),points=VALUES(points),remainder=VALUES(remainder),activity_count=VALUES(activity_count),update_time=VALUES(update_time)";
	
	@Insert("insert into "+tablename+"("+fields+") values" + "("+item+") ON DUPLICATE KEY UPDATE " + update )
	int insertStudentRecycleCount(@Param("item")StudentRecycleCount studentRecycleCount);
	
	@SelectProvider(type = SqlFactory.class,method = "selectStudentRecycleCount")
	List<StudentRecycleCount> selectStudentRecycleCountList(@Param("item")StudentRecycleCount studentRecycleCount);
	
	
	public class SqlFactory extends SQL{
		
		public String selectStudentRecycleCount(@Param("item")StudentRecycleCount studentRecycleCount){
	    	SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
	    	
	    	sql.SELECT(fields);
	    	sql.FROM(tablename);
	    	if(!StringUtils.isEmpty(studentRecycleCount.getUuid())){
	            sql.WHERE("uuid=#{item.uuid}");
	        }
	    	if(studentRecycleCount.getType()!=null){
	        	 sql.WHERE("type=#{item.type}");
			}
	    	return sql.toString();
	    }
		
	}
	
}

