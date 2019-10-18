package com.qimeng.main.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import com.qimeng.main.entity.DeviceRecycleOrder;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年9月17日 上午11:07:14 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Mapper
public interface DeviceRecycleOrderDao {

	static String TABLE_NAME="machine_device_recycle_order";
	
	static String FIELDS="machine_id,uuid,worker_uuid,worker_name,order_type,recycle_type,sys_count,create_time,real_count,upload_time,end_time,b_end";
	
	static String ITEM="#{item.machineId},#{item.uuid},#{item.workerUuid},#{item.workerName},#{item.orderType},#{item.recycleType},#{item.sysCount},#{item.createTime},#{item.realCount},"
			+ "#{item.uploadTime},#{item.endTime},#{item.bEnd}";
	
	@Insert("insert into "+TABLE_NAME+"("+FIELDS+") values" + "("+ITEM+")")
	@Options(useGeneratedKeys = true,keyProperty = "item.id")
	int insertDeviceRecycleOrder(@Param("item") DeviceRecycleOrder deviceRecycleOrder);
	
	@UpdateProvider(type = SqlFactory.class,method = "updateDeviceRecycleOrder")
	int updateDeviceRecycleOrder(@Param("item") DeviceRecycleOrder deviceRecycleOrder);
	
	public class SqlFactory extends SQL{
		public String updateDeviceRecycleOrder(@Param("item")DeviceRecycleOrder deviceRecycleOrder) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
		    sql.UPDATE(TABLE_NAME);
		    
		     if(deviceRecycleOrder.getRealCount()!=null) {
		    	 sql.SET("real_count=#{item.realCount}");
		     }
		     if(deviceRecycleOrder.getUploadTime()!=null) {
		    	 sql.SET("upload_time=#{item.uploadTime}");
		     }
		     if(deviceRecycleOrder.getEndTime()!=null) {
		    	 sql.SET("end_time=#{item.endTime}");
		     }
		     if(deviceRecycleOrder.getbEnd()!=null) {
		    	 sql.SET("b_end=#{item.bEnd}");
		     }
		     if(!StringUtils.isEmpty(deviceRecycleOrder.getUuid())) {
		    	 sql.WHERE("uuid=#{item.uuid}");
		     }
		     if(!StringUtils.isEmpty(deviceRecycleOrder.getWorkerUuid())) {
		    	 sql.WHERE("worker_uuid=#{item.workerUuid}");
		     }
		     if(!StringUtils.isEmpty(deviceRecycleOrder.getWorkerName())) {
		    	 sql.WHERE("worker_name=#{item.workerName}");
		     }
		     if(!StringUtils.isEmpty(deviceRecycleOrder.getMachineId())) {
		    	 sql.WHERE("machine_id=#{item.machineId}");
		     }
		     if(deviceRecycleOrder.getId()!=null) {
		    	 sql.WHERE("id=#{item.id}");
		     }
		     sql.WHERE("b_end=0");
		    return sql.toString();
		}
	}
}

