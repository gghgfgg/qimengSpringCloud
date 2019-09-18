package com.qimeng.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import com.qimeng.main.entity.SchoolAutoCount;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月5日 下午4:24:54 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Mapper
public interface SchoolAutoCountDao {
	static String tablename="kernel_school_auto_count";
	
	static String fields="school_code,type,count,points,remainder,activity_count,count_type,create_time";
	
	static String item="#{item.schoolCode},#{item.type},#{item.count},#{item.points},#{item.remainder},#{item.activityCount},#{item.countType},#{item.createTime}";
	
	@Insert("insert into "+tablename+"("+fields+") values" + "("+item+")")
	@Options(useGeneratedKeys = true,keyProperty = "item.id")
	int insertSchoolAutoCount(@Param("item")SchoolAutoCount schoolAutoCount);
	
	@SelectProvider(type = SqlFactory.class,method = "selectSchoolAutoCount")
	List<SchoolAutoCount> selectSchoolAutoCountList(@Param("item")SchoolAutoCount schoolAutoCount);
	
	@Select("Select id "+fields+" from "+tablename+" where school_code=#{schoolCode} and type=#{type} and count_type=1 "
			+ "and date_format(create_time,'%Y-%m')=date_format(date_sub(CURDATE(),interval 1 month),'%Y-%m')")
	List<SchoolAutoCount> selectSchoolAutoCountForDay(String schoolCode,byte type);
	
	@Select("Select id "+fields+" from "+tablename+" where school_code=#{schoolCode} and type=#{type} and count_type=2 "
			+ "and date_format(create_time,'%Y')=date_format(date_sub(CURDATE(),interval 1 year),'%Y')")
	List<SchoolAutoCount> selectSchoolAutoCountForMonth(String schoolCode,byte type);
	
	public class SqlFactory extends SQL{
		
		public String selectSchoolAutoCount(@Param("item")SchoolAutoCount schoolAutoCount){
	    	SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
	    	
	    	sql.SELECT("id,"+fields);
	    	sql.FROM(tablename);
	    	if(!StringUtils.isEmpty(schoolAutoCount.getSchoolCode())){
	            sql.WHERE("school_code=#{item.schoolCode}");
	        }
	    	if(schoolAutoCount.getType()!=null){
	        	 sql.WHERE("type=#{item.type}");
			}
	    	if(schoolAutoCount.getCountType()!=null){
	        	 sql.WHERE("count_type=#{item.countType}");
			}
	    	return sql.toString();
	    }
		
	}
}

