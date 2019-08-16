package com.qimeng.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import com.qimeng.main.entity.ApplicationManagement;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月2日 下午5:42:57 
* @version 1.0 
* @parameter  
* @since  
* @return  
* */
@Mapper
public interface ApplicationManagementDao {

	static String tablename="management_application_management";
	
	static String fields="app_id,deskey,ivkey,app_type,app_name,active,create_time,update_time";
	
	static String item="#{item.appId},#{item.deskey},#{item.ivkey},#{item.appType},#{item.appName},#{item.active},"
			+ "#{item.createTime},#{item.updateTime}";
	
	@Insert("insert into "+tablename+"("+fields+") values" + "("+item+")")
	@Options(useGeneratedKeys = true,keyProperty = "id")
	int insertApplicationManagement(@Param("item")ApplicationManagement applicationManagement);
	
	@UpdateProvider(type = SqlFactory.class,method = "updateApplicationManagement")
	int updateApplicationManagement(@Param("item")ApplicationManagement applicationManagement);
	
	@SelectProvider(type = SqlFactory.class,method = "selectApplicationManagement")
	List<ApplicationManagement>  selectApplicationManagementList(@Param("item")ApplicationManagement applicationManagement);
	
	public class SqlFactory extends SQL{
		public String updateApplicationManagement(@Param("item")ApplicationManagement applicationManagement) {
			 SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
		     sql.UPDATE(tablename);
		     if(applicationManagement.getAppType()!=null) {
		    	 sql.SET("app_type=#{item.appType}");
		     }
		     if(!StringUtils.isEmpty(applicationManagement.getAppName())) {
		    	 sql.SET("app_name=#{item.appName}");
		     }
		     if(!StringUtils.isEmpty(applicationManagement.getDeskey())) {
		    	 sql.SET("deskey=#{item.deskey}");
		     }
		     if(!StringUtils.isEmpty(applicationManagement.getIvkey())) {
		    	 sql.SET("ivkey=#{item.ivkey}");
		     }
		     if(applicationManagement.getActive()!=null) {
		    	 sql.SET("active=#{item.active}");
		     }
		     sql.SET("update_time=#{item.updateTime}");
		     
		     if(!StringUtils.isEmpty(applicationManagement.getAppId())) {
		    	 sql.WHERE("app_id=#{item.appId}");
		     }
		     if(applicationManagement.getId()!=null) {
		    	 sql.WHERE("id=#{item.id}");
		     }
		     return sql.toString();
		}
		
		public String selectApplicationManagement(@Param("item")ApplicationManagement applicationManagement) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
			sql.SELECT("id,"+fields);
	    	sql.FROM(tablename);
	    	if(applicationManagement.getActive()!=null) {
	    		sql.WHERE("active=#{item.active}");
	    	}
	    	if(applicationManagement.getAppId()!=null) {
		    	 sql.WHERE("app_id=#{item.appId}");
		    }
	    	if(applicationManagement.getAppType()!=null) {
		    	 sql.WHERE("app_type=#{item.appType}");
		     }
	    	 if(applicationManagement.getId()!=null) {
		    	 sql.WHERE("id=#{item.id}");
		     }
	    	 if(!StringUtils.isEmpty(applicationManagement.getAppName())) {
	    		 sql.WHERE("app_name like CONCAT('%',#{item.appName},'%')");
	    	 }
	    	return sql.toString();
		}
	}
}

