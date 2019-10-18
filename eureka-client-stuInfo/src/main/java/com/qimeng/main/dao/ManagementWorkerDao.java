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

import com.qimeng.main.entity.ManagementWorker;


/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年10月17日 下午8:14:03 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Mapper
public interface ManagementWorkerDao {

	static String TABLE_NAME="machine_management_worker";
	
	static String FIELDS="uuid,name,address,postal_code,type,active,activity_count,create_time,last_time";
	
	static String ITEM="#{item.uuid},#{item.name},#{item.address},#{item.postalCode},#{item.type},#{item.active},"
			+ "#{item.activityCount},#{item.createTime},#{item.lastTime}";
	
	@Insert("insert into "+TABLE_NAME+"("+FIELDS+") values" + "("+ITEM+")")
	@Options(useGeneratedKeys = true,keyProperty = "item.id")
	int insertManagementWorker(@Param("item")ManagementWorker managementWorker);
	
	@UpdateProvider(type = SqlFactory.class,method = "updateManagementWorker")
	int updateManagementWorker(@Param("item")ManagementWorker managementWorker);
	
	@SelectProvider(type = SqlFactory.class,method = "selectManagementWorker")
	List<ManagementWorker>  selectManagementWorkerList(@Param("item")ManagementWorker managementWorker);
	
	public class SqlFactory extends SQL{
		public String updateManagementWorker(@Param("item")ManagementWorker managementWorker) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
		    sql.UPDATE(TABLE_NAME);
		    
		    if(!StringUtils.isEmpty(managementWorker.getName())) {
		    	 sql.SET("name=#{item.name}");
		     }
		    if(!StringUtils.isEmpty(managementWorker.getAddress())) {
		    	 sql.SET("address=#{item.address}");
		     }
		    if(!StringUtils.isEmpty(managementWorker.getPostalCode())) {
		    	 sql.SET("postal_code=#{item.postalCode}");
		     }
		    
		    if(managementWorker.getType()!=null) {
		    	 sql.SET("type=#{item.type}");
		    }
		    if(managementWorker.getActivityCount()!=null) {
		    	 sql.SET("activity_count=#{item.activityCount}");
		    }
		    if(managementWorker.getActive()!=null) {
		    	 sql.SET("active=#{item.active}");
		    }
		    sql.SET("last_time=#{item.lastTime}");
		    
		    if(!StringUtils.isEmpty(managementWorker.getUuid())) {
		    	 sql.WHERE("uuid=#{item.uuid}");
		     }
		     if(managementWorker.getId()!=null) {
		    	 sql.WHERE("id=#{item.id}");
		     }
		    return sql.toString();
		}
		
		public String selectManagementWorker(@Param("item")ManagementWorker managementWorker) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
			sql.SELECT("id,"+FIELDS);
	    	sql.FROM(TABLE_NAME);
	        
	    	if(!StringUtils.isEmpty(managementWorker.getName())) {
		    	 sql.WHERE("name like CONCAT('%',#{item.name},'%')");
		     }
		    if(managementWorker.getType()!=null) {
		    	 sql.WHERE("type=#{item.type}");
		    }
		    if(managementWorker.getActive()!=null) {
		    	 sql.WHERE("active=#{item.active}");
		    }
		    if(!StringUtils.isEmpty(managementWorker.getUuid())) {
		    	 sql.WHERE("uuid=#{item.uuid}");
		     }
		     if(managementWorker.getId()!=null) {
		    	 sql.WHERE("id=#{item.id}");
		     }
		     if (!StringUtils.isEmpty(managementWorker.getPostalCode())) {
					String[] postalCodeArray = managementWorker.getPostalCode().split("-");
					String tempString = "(";
					for (int i = 0; i != postalCodeArray.length; i++) {
						if (i != postalCodeArray.length - 1) {
							tempString += "postal_code='" + postalCodeArray[i] + "\' or ";
						} else {
							tempString += "postal_code='" + postalCodeArray[i] + "\')";
						}
					}
					sql.WHERE(tempString);
			}
	        return sql.toString();
		}
	}
}

