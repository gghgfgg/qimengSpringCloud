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

import com.qimeng.main.entity.DeviceRecycleRealTime;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年9月17日 下午3:11:53 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Mapper
public interface DeviceRecycleRealTimeDao {

	static String TABLE_NAME="machine_device_recycle_real_time";
	
	static String FIELDS="machine_id,type,count,b_recycle,create_time,end_time";
	
	static String ITEM="#{item.machineId},#{item.type},#{item.count},#{item.bRecycle},#{item.createTime},"
			+ "#{item.endTime}";
	
	@Insert("insert into "+TABLE_NAME+"("+FIELDS+") values" + "("+ITEM+")")
	@Options(useGeneratedKeys = true,keyProperty = "item.id")
	int insertDeviceRecycleRealTime(@Param("item")DeviceRecycleRealTime deviceRecycleRealTime);
	
	@UpdateProvider(type = SqlFactory.class,method = "updateDeviceRecycleRealTime")
	int updateDeviceRecycleRealTime(@Param("item")DeviceRecycleRealTime deviceRecycleRealTime);
	
	@SelectProvider(type = SqlFactory.class,method = "selectDeviceRecycleRealTime")
	List<DeviceRecycleRealTime> selectDeviceRecycleRealTimeList(@Param("item")DeviceRecycleRealTime deviceRecycleRealTime);
	
	public class SqlFactory extends SQL{
		public String updateDeviceRecycleRealTime(@Param("item")DeviceRecycleRealTime deviceRecycleRealTime) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
		    sql.UPDATE(TABLE_NAME);
		    
		     if(deviceRecycleRealTime.getCount()!=null) {
		    	 sql.SET("count=#{item.count}");
		     }
		     if(deviceRecycleRealTime.getbRecycle()!=null) {
		    	 sql.SET("b_recycle=#{item.bRecycle}");
		     }
		     if(deviceRecycleRealTime.getEndTime()!=null) {
		    	 sql.SET("end_time=#{item.endTime}");
		     }
		     
		     if(deviceRecycleRealTime.getId()!=null) {
		    	 sql.WHERE("id=#{item.id}");
		     }
		    return sql.toString();
		}
		public String selectDeviceRecycleRealTime(@Param("item")DeviceRecycleRealTime deviceRecycleRealTime) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
			sql.SELECT("id,"+FIELDS);
	    	sql.FROM(TABLE_NAME);
		    
		     if(deviceRecycleRealTime.getbRecycle()!=null) {
		    	 sql.WHERE("b_recycle=#{item.bRecycle}");
		     }
		     if(!StringUtils.isEmpty(deviceRecycleRealTime.getMachineId())) {
	    		 sql.WHERE("machine_id=#{item.machineId}");
	    	 }
		     if(deviceRecycleRealTime.getType()!=null) {
		    	 sql.WHERE("type=#{item.type}");
		     }
		     if(deviceRecycleRealTime.getId()!=null) {
		    	 sql.WHERE("id=#{item.id}");
		     }
		    return sql.toString();
		}
		
	}
	
}

