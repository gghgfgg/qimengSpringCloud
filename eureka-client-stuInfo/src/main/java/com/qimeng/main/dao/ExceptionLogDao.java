package com.qimeng.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import com.qimeng.main.entity.ExceptionLog;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年10月18日 下午6:09:01 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Mapper
public interface ExceptionLogDao {

	static String TABLE_NAME="kernel_student_recycle_exception_log";
	
	static String FIELDS="uuid,type,count,ex_count,activity_count,create_time";
	
	static String ITEM="#{item.uuid},#{item.type},#{item.count},#{item.exCount},#{item.activityCount},#{item.createTime}";
	
	@Insert("insert into "+TABLE_NAME+"("+FIELDS+") values" + "("+ITEM+") ")
	int insertExceptionLog(@Param("item")ExceptionLog exceptionLog);
	
	@SelectProvider(type = SqlFactory.class,method = "selectExceptionLog")
	List<ExceptionLog> selectExceptionLogList(@Param("item")ExceptionLog exceptionLog);

	public class SqlFactory extends SQL{
		public String selectExceptionLog(@Param("item")ExceptionLog exceptionLog) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
			sql.SELECT("id,"+FIELDS);
	    	sql.FROM(TABLE_NAME);
	    	
	    	if(exceptionLog.getType()!=null) {
		    	 sql.WHERE("type=#{item.type}");
		    }
	    	if(!StringUtils.isEmpty(exceptionLog.getUuid())){
	    		sql.WHERE("uuid=#{item.uuid}");
	    	}
	    	return sql.toString();
		}
	}
	
}

