package com.qimeng.main.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import com.qimeng.main.entity.MainToStudent;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月30日 下午9:54:57 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Mapper
public interface MainToStudentDao {
	static String tablename = "main_stu";

	static String fields = "uuid,stuinfo";

	static String item = "#{item.uuid},#{item.stuinfo}";

	@Insert("insert into " + tablename + "(" + fields + ") values" + "(" + item + ")")
	@Options(useGeneratedKeys = true,keyProperty = "item.id")
	int insertMainToStudent(@Param("item") MainToStudent mainToStudent);

	@UpdateProvider(type = SqlFactory.class, method = "updateMainToStudent")
	int updateMainToStudent(@Param("item") MainToStudent mainToStudent);

	@SelectProvider(type = SqlFactory.class, method = "selectMainToStudent")
	MainToStudent selectMainToStudent(@Param("item") MainToStudent mainToStudent);

	public class SqlFactory extends SQL {
		public String updateMainToStudent(@Param("item") MainToStudent mainToStudent) {
			SQL sql = new SQL(); // SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
			sql.UPDATE(tablename);

			if (!StringUtils.isEmpty(mainToStudent.getStuinfo())) {
				sql.SET("stuinfo=#{item.stuinfo}");
			}
			if (!StringUtils.isEmpty(mainToStudent.getUuid())) {
				sql.WHERE("uuid=#{item.uuid}");
			}
			if (mainToStudent.getId() != null) {
				sql.WHERE("id=#{item.id}");
			}
			return sql.toString();
		}
		public String selectMainToStudent(@Param("item") MainToStudent mainToStudent)  {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
			sql.SELECT("id,"+fields);
	    	sql.FROM(tablename);
			if (!StringUtils.isEmpty(mainToStudent.getUuid())) {
				sql.WHERE("uuid=#{item.uuid}");
			}
			if (mainToStudent.getId() != null) {
				sql.WHERE("id=#{item.id}");
			}
	    	return sql.toString();
		}
		
	}
}

