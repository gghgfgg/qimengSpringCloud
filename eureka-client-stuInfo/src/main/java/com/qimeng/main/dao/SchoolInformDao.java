package com.qimeng.main.dao;
/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月5日 下午9:53:38 
* @version 1.0 
* @parameter  
* @since  
* @return  */

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import com.qimeng.main.entity.SchoolInform;

@Mapper
public interface SchoolInformDao {

	static String tablename="kernel_school_inform";
	
	static String fields="school_name,school_id,school_code,postal_code,address,contacts,active,create_time,update_time";
	
	static String item="#{item.schoolName},#{item.schoolId},#{item.schoolCode},#{item.postalCode},#{item.address},#{item.contacts},#{item.active},#{item.createTime},#{item.updateTime}";
	
	static String update="school_name=VALUES(school_name),school_id=VALUES(school_id),postal_code=VALUES(postal_code),address=VALUES(address),"
			+ "contacts=VALUES(contacts),active=VALUES(active),update_time=VALUES(update_time)";
	
	@Insert("insert into "+tablename+"("+fields+") values" + "("+item+") ON DUPLICATE KEY UPDATE " + update )
	@Options(useGeneratedKeys = true,keyProperty = "id")
	int insertSchoolInform(@Param("item")SchoolInform schoolInform);
	
	@UpdateProvider(type = SqlFactory.class,method = "updateSchoolInform")
	int updateSchoolInform(@Param("item")SchoolInform schoolInform);
	
	@SelectProvider(type = SqlFactory.class,method = "selectSchoolInform")
	List<SchoolInform> selectSchoolInformList(@Param("item")SchoolInform schoolInform);
	
	@SelectProvider(type = SqlFactory.class,method = "selectSchoolCode")
	List<SchoolInform> selectSchoolCodeList(@Param("item")String postalCode);
	
	public class SqlFactory extends SQL{
		public String updateSchoolInform(@Param("item")SchoolInform schoolInform) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
	        sql.UPDATE(tablename);
	        if(!StringUtils.isEmpty(schoolInform.getSchoolName())){
	            sql.SET("school_name=#{item.schoolName}");
	        }
	        if(!StringUtils.isEmpty(schoolInform.getSchoolCode())){
	            sql.SET("school_code=#{item.schoolCode}");
	        }
	        if(!StringUtils.isEmpty(schoolInform.getPostalCode())){
	            sql.SET("postal_code=#{item.postalCode}");
	        }
	        if(!StringUtils.isEmpty(schoolInform.getAddress())){
	            sql.SET("address=#{item.address}");
	        }
	        if(!StringUtils.isEmpty(schoolInform.getContacts())){
	            sql.SET("contacts=#{item.contacts}");
	        }
	        if(schoolInform.getActive()!=null){
	            sql.SET("active=#{item.active}");
	        }
	        sql.SET("update_time=#{item.updateTime}");
	        return sql.toString();
		}
		public String selectSchoolInform(@Param("item")SchoolInform schoolInform) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
	    	
	    	sql.SELECT("id,"+fields);
	    	sql.FROM(tablename);
	    	
	    	if(!StringUtils.isEmpty(schoolInform.getSchoolName())){
	            sql.WHERE("school_name like CONCAT('%',#{item.schoolName},'%')");
	        }
	        if(!StringUtils.isEmpty(schoolInform.getSchoolCode())){
	            sql.WHERE("school_code=#{item.schoolCode}");
	        }
	        if(!StringUtils.isEmpty(schoolInform.getPostalCode())){
	            sql.WHERE("postal_code=#{item.postalCode}");
	        }
	        if(schoolInform.getActive()!=null){
	            sql.WHERE("active=#{item.active}");
	        }
	    	return sql.toString();
		}
		public String selectSchoolCode(@Param("item")String postalCode) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
	    	
	    	sql.SELECT("id,"+fields);
	    	sql.FROM(tablename);
	    	if(!StringUtils.isEmpty(postalCode)) {		
	    		String[] postalCodeArray = postalCode.split("-");
	    		String tempString="(";
	    		for (int i=0;i!=postalCodeArray.length;i++) {
	    			if(i!=postalCodeArray.length-1)
		    		{
		    			tempString+="postal_code='"+postalCodeArray[i]+"\' or ";
		    		}
		    		else {
		    			tempString+="postal_code='"+postalCodeArray[i]+"\')";
		    		}
				}
	    		sql.WHERE(tempString);
		    }
	    	return sql.toString();
		}
	}
	
}

