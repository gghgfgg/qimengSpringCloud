package com.qimeng.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import com.qimeng.main.entity.PointsUsedLog;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月3日 下午3:16:21 
* @version 1.0 
* @parameter  
* @since  
* @return  */

@Mapper
public interface PointsUsedLogDao {

	static String tablename="kernel_points_used_log";
	
	static String fields="uuid,name,app_id,points,mark,create_time";
	
	static String item="#{item.uuid},#{item.name},#{item.appId},#{item.points},#{item.mark},#{item.createTime}";
	
	@Insert("insert into "+tablename+"("+fields+") values" + "("+item+")")
	@Options(useGeneratedKeys = true,keyProperty = "item.id")
	int insertPointsUsedLog(@Param("item")PointsUsedLog pointsUsedLog);

	@SelectProvider(type = SqlFactory.class,method ="selectPointsUsedLog")
	List<PointsUsedLog> selectPointsUsedLogList(@Param("item")PointsUsedLog pointsUsedLog);
	
	public class SqlFactory extends SQL{
		public String selectPointsUsedLog(@Param("item")PointsUsedLog pointsUsedLog) {
			SQL sql = new SQL();
			sql.SELECT("id,"+fields);
	    	sql.FROM(tablename);
	    	if(!StringUtils.isEmpty(pointsUsedLog.getAppId())){
	        	 sql.WHERE("app_id=#{item.appId}");
			}
	    	if(pointsUsedLog.getUuid()!=null) {
	    		sql.WHERE("uuid=#{item.uuid}");
	    	}
	    	return sql.toString();
		}
	}
}

