package com.qimeng.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import com.qimeng.main.entity.DeviceCurrentState;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月4日 上午12:01:38 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Mapper
public interface DeviceCurrentStateDao {
   static String tablename="machine_device_state";
	
	static String fields="serial_number,status,status_type,create_time,update_time";
	
	static String item="#{item.serialNumber},#{item.status},#{item.statusType},#{item.createTime},#{item.updateTime}";
	
	static String update="status=VALUES(status),status_type=VALUES(status_type),update_time=VALUES(update_time)";
	
	@Insert("insert into "+tablename+"("+fields+") values" +
		        "("+item+") ON DUPLICATE KEY UPDATE " + update)
	int insertDeviceCurrentState(@Param("item")DeviceCurrentState deviceCurrentState);
	
	@SelectProvider(type = SqlFactory.class,method="selectDeviceCurrentState")
	List<DeviceCurrentState> selectDeviceCurrentStateList(@Param("item")DeviceCurrentState deviceCurrentState);
	
	public class SqlFactory extends SQL{
		public String selectDeviceCurrentState(@Param("item")DeviceCurrentState deviceCurrentState) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
	    	
	    	sql.SELECT(fields);
	    	sql.FROM(tablename);
	    	if(!StringUtils.isEmpty(deviceCurrentState.getSerialNumber())) {
	    		sql.WHERE("serial_number=#{item.serialNumber}");
	    	}
	    	if(deviceCurrentState.getStatus()!=null) {
	    		sql.WHERE("status=#{item.status}");
	    	}
	    	if(deviceCurrentState.getStatusType()!=null) {
	    		sql.WHERE("status_type=#{item.statusType}");
	    	}
	    	
	    	return sql.toString();
		}
	}
}

