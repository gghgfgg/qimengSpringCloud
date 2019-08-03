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

import com.qimeng.main.entity.DeviceManagement;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月2日 下午2:57:51 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Mapper
public interface DeviceManagementDao {

	static String tablename="machine_device_management";
	
	static String fields="machine_id,serial_number,school_code,active,create_time,update_time";
	
	static String item="#{item.machineId},#{item.serialNumber},#{item.schoolCode},#{item.active},#{item.createTime},#{item.updateTime}";
	
	@Insert("insert into "+tablename+"("+fields+") values" + "("+item+")")
	@Options(useGeneratedKeys = true,keyProperty = "id")
	int insertDeviceManagement(@Param("item")DeviceManagement deviceManagement);
	
	@UpdateProvider(type = SqlFactory.class,method = "updateDeviceManagement")
	int updateDeviceManagement(@Param("item")DeviceManagement deviceManagement);
	
	@SelectProvider(type = SqlFactory.class,method = "selectDeviceManagementList")
	List<DeviceManagement> selectDeviceManagementList(@Param("item")DeviceManagement deviceManagement);
	
	public class SqlFactory extends SQL{
		public String updateDeviceManagement(@Param("item")DeviceManagement deviceManagement) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
	        sql.UPDATE(tablename);
	        
	        if(!StringUtils.isEmpty(deviceManagement.getSerialNumber())) {
	        	sql.SET("serial_number=#{item.serialNumber}");
	        }
	        if(deviceManagement.getActive()!=null) {
	        	 sql.SET("active=#{item.active}");
	        }
	        sql.SET("update_time=#{item.updateTime}");
	        
	        if(deviceManagement.getId()!=null) {
	        	sql.WHERE("id=#{item.id}");
	        }
	        
	        if(deviceManagement.getMachineId()!=null) {
	        	sql.WHERE("machine_id=#{item.machineId}");
	        }
	        return sql.toString();
		}
		public String selectDeviceManagementList(@Param("item")DeviceManagement deviceManagement) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
			sql.SELECT("id,"+fields);
	    	sql.FROM(tablename);
	        
	    	if(!StringUtils.isEmpty(deviceManagement.getSerialNumber())) {
	        	sql.WHERE("serial_number=#{item.serialNumber}");
	        }
	        if(deviceManagement.getActive()!=null) {
	        	 sql.WHERE("active=#{item.active}");
	        }
	        sql.SET("update_time=#{item.updateTime}");
	        
	        if(deviceManagement.getId()!=null) {
	        	sql.WHERE("id=#{item.id}");
	        }
	        
	        if(deviceManagement.getMachineId()!=null) {
	        	sql.WHERE("machine_id=#{item.machineId}");
	        }
	        return sql.toString();
		}
	}
}

