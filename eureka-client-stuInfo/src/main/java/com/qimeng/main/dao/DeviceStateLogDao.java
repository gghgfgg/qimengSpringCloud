package com.qimeng.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import com.qimeng.main.entity.DeviceStateLog;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月3日 下午11:38:16 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Mapper
public interface DeviceStateLogDao {
	static String tablename="machine_device_state_log";
	
	static String fields="serial_number,status,status_type,mark,create_time";
	
	static String item="#{item.serialNumber},#{item.status},#{item.statusType},#{item.mark},#{item.createTime}";
	
	@Insert("insert into "+tablename+"("+fields+") values" +"("+item+") ")
	@Options(useGeneratedKeys = true,keyProperty = "item.id")
	int insertDeviceStateLog(@Param("item")DeviceStateLog deviceStateLog);
	
	@SelectProvider(type = SqlFactory.class,method = "selectDeviceStateLog")
	List<DeviceStateLog> selectDeviceStateLog(@Param("item")DeviceStateLog deviceStateLog);
	
	public class SqlFactory extends SQL{
		public String selectDeviceStateLog(@Param("item")DeviceStateLog deviceStateLog) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
	    	
	    	sql.SELECT("id,"+fields);
	    	sql.FROM(tablename);
	    	if(!StringUtils.isEmpty(deviceStateLog.getSerialNumber())) {
	    		sql.WHERE("serial_number=#{item.serialNumber}");
	    	}
	    	if(deviceStateLog.getStatus()!=null) {
	    		sql.WHERE("status=#{item.status}");
	    	}
	    	if(deviceStateLog.getStatusType()!=null) {
	    		sql.WHERE("status_type=#{item.statusType}");
	    	}
	    	return sql.toString();
		}
	}
}

