package com.qimeng.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

import com.qimeng.main.entity.StudentType;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年9月7日 下午9:24:46 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Mapper
public interface StudentTypeDao {
static String tablename="auxiliary_student_type";
	
	static String fields="type,b_phone,b_teacher,mark,create_time,update_time";
	
	static String item="#{item.type},#{item.bPhone},#{item.bTeacher},#{item.mark},#{item.createTime},#{item.updateTime}";
	@Insert("insert into "+tablename+"("+fields+") values" + "("+item+")")
	@Options(useGeneratedKeys = true,keyProperty = "item.id")
	int insertStudentType(@Param("item")StudentType studentType);
	
	@Update("update "+tablename+" set type=#{item.type},b_phone=#{item.bPhone},b_teacher=#{item.bTeacher},mark=#{item.mark},"
			+ "update_time=#{item.updateTime} where id=#{item.id}")
	int updateStudentType(@Param("item")StudentType studentType);
	
	@SelectProvider(type = SqlFactory.class,method = "selectStudentType")
	List<StudentType> selectStudentTypeList(@Param("item")StudentType studentType);
		
	public class SqlFactory extends SQL{
		 public String selectStudentType(@Param("item")StudentType studentType) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
		    	
		    sql.SELECT("id,"+fields);
		    sql.FROM(tablename);
		    if(studentType.getType()!=null)
		    {
		    	sql.WHERE("type=#{item.type}");
		    }
		    if(studentType.getbTeacher()!=null) {
		    	sql.WHERE("b_teacher=#{item.bTeacher}");
		    }
		    if(studentType.getbPhone()!=null) {
		    	sql.WHERE("b_phone=#{item.bPhone}");
		    }
		    if(studentType.getId()!=null) {
		    	sql.WHERE("id=#{item.id}");
		    }
		    return sql.toString();
		 }
		 
	}
}

