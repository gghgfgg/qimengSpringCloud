package com.qimeng.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import com.qimeng.main.entity.DeviceRecycleCount;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月2日 下午4:01:30 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Mapper
public interface DeviceRecycleCountDao {
	static String tablename="machine_device_recycle_count";
	
	static String fields="machine_id,type,count,points,remainder,activity_count,create_time,update_time";
	
	static String item="#{item.machineId},#{item.type},#{item.count},#{item.points},#{item.remainder},#{item.activityCount},#{item.createTime},#{item.updateTime}";
	
	static String update="count=VALUES(count),points=VALUES(points),remainder=VALUES(remainder),activity_count=VALUES(activity_count),update_time=VALUES(update_time)";
	
	@Insert("insert into "+tablename+"("+fields+") values" + "("+item+") ON DUPLICATE KEY UPDATE " + update )
	@Options(useGeneratedKeys = true,keyProperty = "item.id")
	int insertDeviceRecycleCount(@Param("item")DeviceRecycleCount deviceRecycleCount);
	
	@SelectProvider(type = SqlFactory.class,method = "selectDeviceRecycleCount")
	List<DeviceRecycleCount> selectDeviceRecycleCountList(@Param("item")DeviceRecycleCount deviceRecycleCount);
	
	
	public class SqlFactory extends SQL{
		
		public String selectDeviceRecycleCount(@Param("item")DeviceRecycleCount deviceRecycleCount){
	    	SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
	    	
	    	sql.SELECT("id,"+fields);
	    	sql.FROM(tablename);
	    	if(!StringUtils.isEmpty(deviceRecycleCount.getMachineId())){
	            sql.WHERE("machine_id=#{item.machineId}");
	        }
	    	if(deviceRecycleCount.getType()!=null){
	        	 sql.WHERE("type=#{item.type}");
			}
	    	return sql.toString();
	    }
		
	}
}

