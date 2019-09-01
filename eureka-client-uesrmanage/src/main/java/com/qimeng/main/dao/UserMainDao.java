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

import com.qimeng.main.entity.UserMain;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2019年8月30日 下午3:14:50
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Mapper
public interface UserMainDao {

	static String tablename = "main_user";

	static String fields = "uuid,phone,mail,username,password,salt,create_time,update_time";

	static String item = "#{item.uuid},#{item.phone},#{item.mail},#{item.username},#{item.password},#{item.salt},"
			+ "#{item.createTime},#{item.updateTime}";

	@Insert("insert into " + tablename + "(" + fields + ") values" + "(" + item + ")")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insertUserMain(@Param("item") UserMain userMain);

	@UpdateProvider(type = SqlFactory.class, method = "updateUserMain")
	int updateUserMain(@Param("item") UserMain userMain);

	@SelectProvider(type = SqlFactory.class, method = "selectUserMain")
	List<UserMain> selectUserMainList(@Param("item") UserMain userMain);

	public class SqlFactory extends SQL {
		public String updateUserMain(@Param("item") UserMain userMain) {
			SQL sql = new SQL(); // SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
			sql.UPDATE(tablename);

			if (!StringUtils.isEmpty(userMain.getPhone())) {
				sql.SET("phone=#{item.phone}");
			}
			if (!StringUtils.isEmpty(userMain.getMail())) {
				sql.SET("mail=#{item.mail}");
			}
			if (!StringUtils.isEmpty(userMain.getUsername())) {
				sql.SET("username=#{item.username}");
			}
			if (!StringUtils.isEmpty(userMain.getPassword())) {
				sql.SET("password=#{item.password}");
			}
			if (!StringUtils.isEmpty(userMain.getSalt())) {
				sql.SET("salt=#{item.salt}");
			}
			sql.SET("update_time=#{item.updateTime}");
			if (!StringUtils.isEmpty(userMain.getUuid())) {
				sql.WHERE("uuid=#{item.uuid}");
			}
			if (userMain.getId() != null) {
				sql.WHERE("id=#{item.id}");
			}
			return sql.toString();
		}
		public String selectUserMain(@Param("item") UserMain userMain)  {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
			sql.SELECT("id,"+fields);
	    	sql.FROM(tablename);
	    	if (!StringUtils.isEmpty(userMain.getPhone())) {
				sql.WHERE("phone=#{item.phone}");
			}
			if (!StringUtils.isEmpty(userMain.getMail())) {
				sql.WHERE("mail=#{item.mail}");
			}
			if (!StringUtils.isEmpty(userMain.getUsername())) {
				sql.WHERE("username=#{item.username}");
			}
			if (!StringUtils.isEmpty(userMain.getUuid())) {
				sql.WHERE("uuid=#{item.uuid}");
			}
			if (userMain.getId() != null) {
				sql.WHERE("id=#{item.id}");
			}
	    	return sql.toString();
		}
		
	}
}
