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

import com.qimeng.main.entity.WecharUser;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2019年8月30日 下午4:09:10
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Mapper
public interface WecharUserDao {
	static String tablename = "wechat_user";

	static String fields = "openid,unionid,nick_name,create_time,update_time";

	static String item = "#{item.openid},#{item.unionid},#{item.nickName},#{item.createTime},#{item.updateTime}";

	@Insert("insert into " + tablename + "(" + fields + ") values" + "(" + item + ")")
	@Options(useGeneratedKeys = true,keyProperty = "item.id")
	int insertWecharUser(@Param("item") WecharUser wecharUser);

	@UpdateProvider(type = SqlFactory.class, method = "updateWecharUser")
	int updateWecharUser(@Param("item") WecharUser wecharUser);

	@SelectProvider(type = SqlFactory.class, method = "selectWecharUser")
	List<WecharUser> selectWecharUserList(@Param("item") WecharUser wecharUser);

	public class SqlFactory extends SQL {
		public String updateWecharUser(@Param("item")WecharUser wecharUser) {
			SQL sql = new SQL(); // SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
			sql.UPDATE(tablename);

			if (!StringUtils.isEmpty(wecharUser.getUnionid())) {
				sql.SET("unionid=#{item.unionid}");
			}
			if (!StringUtils.isEmpty(wecharUser.getNickName())) {
				sql.SET("nick_name=#{item.nickName}");
			}
			sql.SET("update_time=#{item.updateTime}");
			if (!StringUtils.isEmpty(wecharUser.getOpenid())) {
				sql.WHERE("openid=#{item.openid}");
			}
			if (wecharUser.getId() != null) {
				sql.WHERE("id=#{item.id}");
			}
			return sql.toString();
		}

		public String selectWecharUser(@Param("item") WecharUser wecharUser) {
			SQL sql = new SQL(); // SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
			sql.SELECT("id," + fields);
			sql.FROM(tablename);
			if (!StringUtils.isEmpty(wecharUser.getUnionid())) {
				sql.WHERE("unionid=#{item.unionid}");
			}
			if (!StringUtils.isEmpty(wecharUser.getOpenid())) {
				sql.WHERE("openid=#{item.openid}");
			}
			if (wecharUser.getId() != null) {
				sql.WHERE("id=#{item.id}");
			}
			return sql.toString();
		}
	}
}
