package com.qimeng.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import com.qimeng.main.entity.DeviceRecycleLog;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月2日 下午4:33:22 
* @version 1.0 
* @parameter  
* @since  
* @return  */

@Mapper
public interface DeviceRecycleLogDao {
	static String tablename="machine_device_recycle_log";
	
	static String fields="machine_id,schoolid_of_device,uuid,schoolid_of_student,recycle_type,identity_type,count,"
			+ "last_remainder,factor,points,remainder,create_time";
	
	static String item="#{item.machineId},#{item.schoolidOfDevice},#{item.uuid},#{item.schoolidOfStudent},"
			+ "#{item.recycleType},#{item.identityType},#{item.count},#{item.lastRemainder},#{item.factor},"
			+ "#{item.points},#{item.remainder},#{item.createTime}";
	
	@Insert("insert into "+tablename+"("+fields+") values" + "("+item+")")
	@Options(useGeneratedKeys = true,keyProperty = "id")
	int insertDeviceRecycleLog(@Param("item")DeviceRecycleLog deviceRecycleLog);
	
	@SelectProvider(type = SqlFactory.class,method = "selectDeviceRecycleLog")
	List<DeviceRecycleLog> selectDeviceRecycleLogList(@Param("item")DeviceRecycleLog deviceRecycleLog);
	
	@Select("Select id "+fields+" from "+tablename+" where schoolid_of_device=#{schoolCode} and recycle_type=#{type} "
			+ "and date_format(create_time,'%Y-%m-%d')=date_sub(CURDATE(),interval 1 day)")
	List<DeviceRecycleLog> selectDeviceRecycleLogBySchoolDay(String schoolCode,byte type);
	
	public class SqlFactory extends SQL{
		public String selectDeviceRecycleLog(@Param("item")DeviceRecycleLog deviceRecycleLog) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
	    	
	    	sql.SELECT("id,"+fields);
	    	sql.FROM(tablename);
	    	if(!StringUtils.isEmpty(deviceRecycleLog.getMachineId())){
	            sql.WHERE("machine_id=#{item.machineId}");
	        }
	    	if(!StringUtils.isEmpty(deviceRecycleLog.getSchoolidOfDevice())){
	        	 sql.WHERE("schoolid_of_device=#{item.schoolidOfDevice}");
			}
	    	if(!StringUtils.isEmpty(deviceRecycleLog.getUuid())){
	        	 sql.WHERE("uuid=#{item.uuid}");
			}
	    	if(!StringUtils.isEmpty(deviceRecycleLog.getSchoolidOfStudent())){
	        	 sql.WHERE("schoolid_of_student=#{item.schoolidOfStudent}");
			}
	    	if(deviceRecycleLog.getRecycleType()!=null){
	        	 sql.WHERE("recycle_type=#{item.recycleType}");
			}
	    	if(deviceRecycleLog.getIdentityType()!=null){
	        	 sql.WHERE("identity_type=#{item.identityType}");
			}
	    	return sql.toString();
		}
	}
}

