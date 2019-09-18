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

import com.qimeng.main.entity.WecharToMain;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月30日 下午9:30:07 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Mapper
public interface WechatToMainDao {

	static String tablename = "wechat_main";

	static String fields = "openid,unionid,uuid";

	static String item = "#{item.openid},#{item.unionid},#{item.uuid}";

	@Insert("insert into " + tablename + "(" + fields + ") values" + "(" + item + ")")
	@Options(useGeneratedKeys = true,keyProperty = "item.id")
	int inserWechatToMain(@Param("item") WecharToMain wecharToMain);

	@UpdateProvider(type = SqlFactory.class, method = "updateWecharToMain")
	int updateWechatToMain(@Param("item") WecharToMain wecharToMain);

	@SelectProvider(type = SqlFactory.class, method = "selectWecharToMain")
	List<WecharToMain> selecWechatToMainList(@Param("item") WecharToMain wecharToMain);

	public class SqlFactory extends SQL {
		public String updateWecharToMain(@Param("item") WecharToMain wecharToMain) {
			SQL sql = new SQL(); // SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
			sql.UPDATE(tablename);

			if (!StringUtils.isEmpty(wecharToMain.getUnionid())) {
				sql.SET("unionid=#{item.unionid}");
			}
			if (!StringUtils.isEmpty(wecharToMain.getOpenid())) {
				sql.WHERE("openid=#{item.openid}");
			}
			if (wecharToMain.getId() != null) {
				sql.WHERE("id=#{item.id}");
			}
			return sql.toString();
		}

		public String selectWecharToMain(@Param("item") WecharToMain wecharToMain) {
			SQL sql = new SQL(); // SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
			sql.SELECT("id," + fields);
			sql.FROM(tablename);
			if (!StringUtils.isEmpty(wecharToMain.getUnionid())) {
				sql.WHERE("unionid=#{item.unionid}");
			}
			if (!StringUtils.isEmpty(wecharToMain.getOpenid())) {
				sql.WHERE("openid=#{item.openid}");
			}
			if (wecharToMain.getId() != null) {
				sql.WHERE("id=#{item.id}");
			}
			return sql.toString();
		}
	}
}

