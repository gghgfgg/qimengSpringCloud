package com.qimeng.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

import com.qimeng.main.entity.SchoolContactsType;


/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月5日 下午5:46:02 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Mapper
public interface SchoolContactsTypeDao {

	static String tablename="auxiliary_school_contacts_type";
	
	static String fields="type,position,weight,create_time,update_time";
	
	static String item="#{item.type},#{item.position},#{item.weight},#{item.createTime},#{item.updateTime}";
	
	static String update="type=VALUES(type),mark=VALUES(position),uint=VALUES(weight),update_time=VALUES(update_time)";
	
	@Insert("insert into "+tablename+"("+fields+") values" + "("+item+") ")
	int insertSchoolContactsType(@Param("item")SchoolContactsType schoolContactsType);
	
	@Update("update "+tablename+"Set type=#{item.type},mark=#{item.mark},uint=#{item.uint},factor=#{item.factor},"
			+ "update_time=#{item.update_time} where id=#{item.id} " )
	int updateSchoolContactsType(@Param("item")SchoolContactsType schoolContactsType);
	
	@SelectProvider(type = SqlFactory.class,method = "selectSchoolContactsType")
	List<SchoolContactsType> selectSchoolContactsType(@Param("item")SchoolContactsType schoolContactsType);
	public class SqlFactory extends SQL{
		public String selectSchoolContactsType(@Param("item")SchoolContactsType schoolContactsType) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
	    	
	    	sql.SELECT("id,"+fields);
	    	sql.FROM(tablename);
	    	if(schoolContactsType.getId()!=null) {
	    		sql.WHERE("id=#{item.id}");
	    	}	    	
	    	if(schoolContactsType.getType()!=null) {
	    		sql.WHERE("type=#{item.type}");
	    	}
	    	return sql.toString();
		}
	}
}

