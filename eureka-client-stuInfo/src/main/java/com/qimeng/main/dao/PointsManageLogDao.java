package com.qimeng.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;


import com.qimeng.main.entity.PointsManageLog;


/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月3日 下午5:27:53 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Mapper
public interface PointsManageLogDao {

	static String tablename="kernel_points_manage_log";
	
	static String fields="uuid,name,app_id,operator,points,mark,create_time";
	
	static String item="#{item.uuid},#{item.name},#{item.appId},#{item.operator},#{item.points},#{item.mark},#{item.createTime}";
	
	@Insert("insert into "+tablename+"("+fields+") values" + "("+item+")")
	@Options(useGeneratedKeys = true,keyProperty = "id")
	int insertPointsManageLog(@Param("item")PointsManageLog PointsManageLog);
	
	@SelectProvider(type = SqlFactory.class,method ="selectPointsManageLog")
	List<PointsManageLog> selectPointsManageLogList(@Param("item")PointsManageLog PointsManageLog);
	
	public class SqlFactory extends SQL{
		public String selectPointsManageLog(@Param("item")PointsManageLog PointsManageLog) {
			SQL sql = new SQL();
			sql.SELECT("id,"+fields);
	    	sql.FROM(tablename);
	    	if(!StringUtils.isEmpty(PointsManageLog.getAppId())){
	        	 sql.WHERE("app_id=#{item.appId}");
			}
	    	if(PointsManageLog.getUuid()!=null) {
	    		sql.WHERE("uuid=#{item.uuid}");
	    	}
	    	return sql.toString();
		}
	}
}

