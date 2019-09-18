package com.qimeng.main.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import com.qimeng.main.entity.StudentData;


@Mapper
public interface StudentDataDao {
	static String tablename="kernel_student_data";
	
	static String fields="uuid,identity_card,student_code,card,code,name,type,"
			+ "school_code,binding,active,create_time,update_time,first_time,"
			+ "last_time,activity_count,total_points,used_points,deduct_points";
	
	static String fieldsByInfrom="uuid,identity_card,student_code,card,code,name,type,"
			+ "school_code,create_time,update_time";
	
	static String item="#{item.uuid},#{item.identityCard},#{item.studentCode},#{item.card},#{item.code},"
			+ "#{item.name},#{item.type},#{item.schoolCode},#{item.binding},#{item.active},#{item.createTime}"
			+ "#{item.updateTime},#{item.firstTime},#{item.activityCount},#{item.totalPoints},#{item.usedPoints},#{item.deductPoints}";
	
	static String itemByInfrom="#{item.uuid},#{item.identityCard},#{item.studentCode},#{item.card},#{item.code},"
			+ "#{item.name},#{item.type},#{item.schoolCode},#{item.createTime},#{item.updateTime}";
	
	static String update="uuid=VALUES(uuid),identity_card=VALUES(identity_card),student_code=VALUES(student_code),card=VALUES(card),code=VALUES(code),name=VALUES(name),type=VALUES(type),"
			+ "school_code=VALUES(school_code),binding=VALUES(binding),active=VALUES(active),create_time=VALUES(create_time),update_time=VALUES(update_time),first_time=VALUES(first_time),"
			+ "last_time=VALUES(last_time),activity_count=VALUES(activity_count),total_points=VALUES(total_points),used_points=VALUES(used_points),deduct_points=VALUES(deduct_points)";
	
	static String updateByInfrom="name=VALUES(name),type=VALUES(type),"
			+ "school_code=VALUES(school_code),update_time=VALUES(update_time)";
	
	static String updatecode=",student_code=VALUES(student_code),identity_card=VALUES(identity_card)";
	
	@Insert({"<script> insert into "+tablename+"("+fieldsByInfrom+") values" +
            "<foreach collection=\"list\" item=\"item\" index=\"index\"  separator=\",\"> "+
            "("+itemByInfrom+")"+
            "</foreach>"
            + "ON DUPLICATE KEY UPDATE " + updateByInfrom+ "</script>"})
	int insertStudentDataList(@Param("list")List<StudentData> studentDataList);
	
	@Insert("insert into "+tablename+"("+fieldsByInfrom+") values" +
            "("+itemByInfrom+") ON DUPLICATE KEY UPDATE " + updateByInfrom+ updatecode)
	@Options(useGeneratedKeys = true,keyProperty = "item.id")
	int insertStudentData(@Param("item")StudentData studentData);
	
	@UpdateProvider(type = SqlFactory.class,method = "updateByIdentityCardOrStrudentCode")
	int updateStudentDateByIdentityCardOrStrudentCode(@Param("item")StudentData studentData);
	
	@UpdateProvider(type = SqlFactory.class,method = "updateByUuid")
    int updateStudentDateByUuid(@Param("item")StudentData studentData);
	
	@SelectProvider(type = SqlFactory.class,method = "selectStudentData")
	List<StudentData> selectStudentData(@Param("item")StudentData studentData);
	
	@Select("select COUNT(*) from "+tablename+" where school_code=#{schoolCode} and update_time=#{time}")
	int selectStudentCountByUpdata(String schoolCode,Date time);
	
	public class SqlFactory extends SQL{
	    public String updateByIdentityCardOrStrudentCode(@Param("item")StudentData studentData){

	        SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
	        sql.UPDATE(tablename);
	       
	        
	        if(!StringUtils.isEmpty(studentData.getName())){
	        	sql.SET("name=#{item.name}");
	        }
	        if(studentData.getType()!=null){
	        	sql.SET("type=#{item.type}");
	        }
	        if(!StringUtils.isEmpty(studentData.getSchoolCode())){
	            sql.SET("school_code=#{item.schoolCode}");
	        }
	        if(!StringUtils.isEmpty(studentData.getStudentCode())){
	        	sql.SET("student_code=#{item.studentCode}");
	        }
	        if(!StringUtils.isEmpty(studentData.getIdentityCard())){
	        	sql.SET("identity_card=#{item.identityCard}");
			}
	        if(!StringUtils.isEmpty(studentData.getBinding())){
	        	sql.SET("binding=#{item.binding}");
			}
	        
	        sql.SET("update_time=#{item.updateTime}");
	      
	        if(!StringUtils.isEmpty(studentData.getStudentCode())){
	            sql.WHERE("student_code=#{item.studentCode}");
	        }
	        if(!StringUtils.isEmpty(studentData.getIdentityCard())){
	        	if(!StringUtils.isEmpty(studentData.getStudentCode())){
		            sql.OR();
		        }
	        	 sql.WHERE("identity_card=#{item.identityCard}");
			}
	       
	        return sql.toString();
	      }
	    
	    public String updateByUuid(@Param("item")StudentData studentData) {
	    	 SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
		     sql.UPDATE(tablename);
		     
		     if(studentData.getActive()!=null) {
		    	 sql.SET("active=#{item.active}");
		     }
		     if(studentData.getUpdateTime()!=null) {
		    	 sql.SET("update_time=#{item.updateTime}");
		     }
		     if(studentData.getFirstTime()!=null) {
		    	 sql.SET("first_time=#{item.firstTime}");
		     }
		     if(studentData.getActivityCount()!=null) {
		    	 sql.SET("activity_count=#{item.activityCount}");
		     }
		     if(studentData.getTotalPoints()!=null) {
		    	 sql.SET("total_points=#{item.totalPoints}");
		     }
		     if(studentData.getUsedPoints()!=null) {
		    	 sql.SET("used_points=#{item.usedPoints}");
		     }
		     if(studentData.getDeductPoints()!=null) {
		    	 sql.SET("deduct_points=#{item.deductPoints}");
		     }
		     if(studentData.getLastTime()!=null) {
		    	 sql.SET("last_time=#{item.lastTime}");
		     }
		     
		     sql.WHERE("uuid=#{item.uuid}");
	    	return sql.toString();
	    }
	    
	    public String selectStudentData(@Param("item")StudentData studentData){
	    	SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
	    	
	    	sql.SELECT("id,"+fields);
	    	sql.FROM(tablename);
	    	if(!StringUtils.isEmpty(studentData.getStudentCode())){
	            sql.WHERE("strudent_code=#{item.strudentCode}");
	        }
	    	if(!StringUtils.isEmpty(studentData.getIdentityCard())){
	        	 sql.WHERE("identity_card=#{item.identityCard}");
			}
	    	if(!StringUtils.isEmpty(studentData.getName())){
	        	 sql.WHERE("name=#{item.name}");
			}  	
	    	if(!StringUtils.isEmpty(studentData.getSchoolCode())){
	        	 sql.WHERE("school_code=#{item.schoolCode}");
			}
	    	if(!StringUtils.isEmpty(studentData.getCode())){
	        	 sql.WHERE("code=#{item.code}");
			}
	    	if(!StringUtils.isEmpty(studentData.getCard())){
	        	 sql.WHERE("card=#{item.card}");
			}
	    	
	    	if(!StringUtils.isEmpty(studentData.getUuid())) {
	    		sql.WHERE("uuid=#{item.uuid}");
	    	}
	    	
	    	if(studentData.getType()!=null) {
	    		if(studentData.getType()>1) {
	    			sql.WHERE("type=#{item.type}");
	    		}else {
	    			sql.WHERE("type&1=#{item.type}");
				}
	    	}
	    	
	    	if(studentData.getBinding()!=null)
	    	{
	    		sql.WHERE("binding=#{item.binding}");
	    	}
	    	
	    	if(studentData.getActive()!=null)
	    	{
	    		sql.WHERE("active=#{item.active}");
	    	}
	    	return sql.toString();
	    }
	    }

}
