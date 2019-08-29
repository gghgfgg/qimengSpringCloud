package com.qimeng.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

import com.qimeng.main.entity.DeviceState;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月3日 下午10:54:32 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Mapper
public interface DeviceStateDao {

	static String tablename="auxiliary_device_state";
	
	static String fields="type,status,mark,create_time,update_time";
	
	static String item="#{item.type},#{item.status},#{item.mark},#{item.createTime},#{item.updateTime}";
	@Insert("insert into "+tablename+"("+fields+") values" + "("+item+")")
	@Options(useGeneratedKeys = true,keyProperty = "id")
	int insertDeviceState(@Param("item")DeviceState deviceState);
	
	@Update("update "+tablename+" set type=#{item.type},status=#{item.status},mark=#{item.mark},"
			+ "update_time=#{item.updateTime} where id=#{item.id}")
	int updateDeviceState(@Param("item")DeviceState deviceState);
	
	@SelectProvider(type = SqlFactory.class,method = "selectDeviceState")
	List<DeviceState> selectDeviceStateList(@Param("item")DeviceState deviceState);
		
	public class SqlFactory extends SQL{
		 public String selectDeviceState(@Param("item")DeviceState deviceState) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
		    	
		    sql.SELECT("id,"+fields);
		    sql.FROM(tablename);
		    if(deviceState.getType()!=null)
		    {
		    	sql.WHERE("type=#{item.type}");
		    }
		    if(deviceState.getStatus()!=null) {
		    	sql.WHERE("status=#{item.status}");
		    }
		    if(deviceState.getId()!=null) {
		    	sql.WHERE("id=#{item.id}");
		    }
		    return sql.toString();
		 }
		 
	}
}

