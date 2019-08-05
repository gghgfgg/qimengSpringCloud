package com.qimeng.main.dao;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

import com.qimeng.main.entity.RecycleType;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月2日 上午11:04:55 
* @version 1.0 
* @parameter  
* @since  
* @return  */

@Mapper
public interface RecycleTypeDao {
	static String tablename="auxiliary_recycle_type";
	
	static String fields="type,mark,uint,factor,create_time,update_time";
	
	static String item="#{item.type},#{item.mark},#{item.uint},#{item.factor},#{item.createTime},#{item.updateTime}";
	
	static String update="type=VALUES(type),mark=VALUES(mark),uint=VALUES(uint),factor=VALUES(factor),update_time=VALUES(update_time)";
	
	@Insert("insert into "+tablename+"("+fields+") values" + "("+item+") ")
	int insertRecycleType(@Param("item")RecycleType recycleType);
	
	@Update("update "+tablename+"Set type=#{item.type},mark=#{item.mark},uint=#{item.uint},factor=#{item.factor},"
			+ "update_time=#{item.update_time} where id=#{item.id} " )
	int updateRecycleType(@Param("item")RecycleType recycleType);
	
	@SelectProvider(type = SqlFactory.class,method = "selectRecycleType")
	List<RecycleType> selectRecycleTypeList(@Param("item")RecycleType recycleType);
	
	public class SqlFactory extends SQL{
		public String selectRecycleType(@Param("item")RecycleType recycleType) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
	    	
	    	sql.SELECT("id,"+fields);
	    	sql.FROM(tablename);
	    	if(recycleType.getId()!=null) {
	    		sql.WHERE("id=#{item.id}");
	    	}	    	
	    	if(recycleType.getType()!=null) {
	    		sql.WHERE("type=#{item.type}");
	    	}
	    	return sql.toString();
		}
	}
}
