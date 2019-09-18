package com.qimeng.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import com.qimeng.main.vo.DeviceInformVo;
import com.qimeng.main.vo.SchoolInfoVo;
import com.qimeng.main.vo.StudentVo;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月9日 上午9:22:17 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Mapper
public interface JoinDao {
	
	@SelectProvider(type = SqlFactory.class,method = "selectDeviceInformVos")
	List<DeviceInformVo> selectDeviceInformVosList(@Param("item")DeviceInformVo deviceInformVo);
	
	@SelectProvider(type = SqlFactory.class,method = "selectStudentVos")
	List<StudentVo> selectStudentVosList(@Param("item")StudentVo studentVo);
	
	@Deprecated
	@SelectProvider(type = SqlFactory.class,method = "selectStudentRank")
	List<String> selectStudentRank(@Param("item")StudentVo studentVo);
	
	
	@SelectProvider(type = SqlFactory.class,method = "selectSchoolInformVos")
	List<SchoolInfoVo> selectSchoolInformVosList(@Param("item")SchoolInfoVo schoolInfoVo);
	
	@Deprecated
	@Select("select school_code ,count(*) as headcount ,count(case when type&1=0 then 1 else null end) as studentcount,"
			+ "count(case when type&1=1 then 1 else null end) as teachercount,count(case when type&1=0 and active !=0 then 1 else null end) as studentactcount,"
			+ "count(case when type&1=1 and active !=0 then 1 else null end) as teacheractcount from kernel_student_data where school_code=#{schoolCode}")
	SchoolInfoVo SchoolCount(String schoolCode);
	
	public class SqlFactory extends SQL{
		public String selectDeviceInformVos(@Param("item")DeviceInformVo deviceInformVo) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
			sql.SELECT("a.machine_id,a.serial_number,a.school_code,a.postal_code,"
					+ "a.active,b.status,b.status_type as type");
	    	sql.FROM("machine_device_management a");
	    	sql.LEFT_OUTER_JOIN("machine_device_state b on a.serial_number=b.serial_number");
	    
	    	if(deviceInformVo.getActive()!=null) {
	        	 sql.WHERE("a.active=#{item.active}");
	        }
	    	if(deviceInformVo.getStatus()!=null) {
	    		sql.WHERE("b.status=#{item.status}");
	    	}
	    	if(!StringUtils.isEmpty(deviceInformVo.getSchoolCode())) {
	    		String[] schoolCodeArray = deviceInformVo.getSchoolCode().split("-");
	    		String tempString="(";
		    	for (int i=0;i!=schoolCodeArray.length;i++) {
		    		if(i!=schoolCodeArray.length-1)
		    		{
		    			tempString+="a.school_code='"+schoolCodeArray[i]+"\' or ";
		    		}
		    		else {
		    			tempString+="a.school_code='"+schoolCodeArray[i]+"\')";
		    		}
		    	}
	    		sql.WHERE(tempString);
	        }
//	    	if(!StringUtils.isEmpty(deviceInformVo.getPostalCode())) {		
//	    		String[] postalCodeArray = deviceInformVo.getPostalCode().split("-");
//	    		String tempString="(";
//	    		for (int i=0;i!=postalCodeArray.length;i++) {
//	    			if(i!=postalCodeArray.length-1)
//		    		{
//		    			tempString+="a.postal_code='"+postalCodeArray[i]+"\' or ";
//		    		}
//		    		else {
//		    			tempString+="a.postal_code='"+postalCodeArray[i]+"\')";
//		    		}
//				}
//	    		sql.WHERE(tempString);
//		    }
	    	
	    	return sql.toString();
		}
		public String selectStudentVos(@Param("item")StudentVo studentVo) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
			sql.SELECT("a.uuid,a.name,a.identity_card,a.student_code,a.active,"
					+ "a.school_code,a.card,a.code,a.type,a.binding,a.first_time,"
					+ "a.last_time,a.activity_count,a.total_points,a.used_points,"
					+ "a.deduct_points,"
					+ "b.grade,b.classS,b.sex");
	    	sql.FROM("kernel_student_data a");
	    	sql.INNER_JOIN("kernel_student_inform b on (a.student_code=b.student_code) or (a.identity_card=b.identity_card)");
	    
	       	if(studentVo.getActive()!=null) {
	        	 sql.WHERE("a.active=#{item.active}");
	        }
	    	if(studentVo.getType()!=null) {
	    		if(studentVo.getType()>1) {
	    			sql.WHERE("a.type=#{item.type}");
	    		}else {
	    			sql.WHERE("a.type&1=#{item.type}");
				}
	    	}
	    	if(studentVo.getBinding()!=null) {
	    		sql.WHERE("a.binding=#{item.binding}");
	    	}
	    	if(!StringUtils.isEmpty(studentVo.getClassS())) {
	    		sql.WHERE("b.classS=#{item.classS}");
	    	}
	    	if(!StringUtils.isEmpty(studentVo.getGrade())) {
	    		sql.WHERE("b.grade=#{item.grade}");
	    	}
	    	if(!StringUtils.isEmpty(studentVo.getSex())) {
	    		sql.WHERE("b.sex=#{item.sex}");
	    	}
	    	if(!StringUtils.isEmpty(studentVo.getName())) {
	    		sql.WHERE("a.name like CONCAT('%',#{item.name},'%')");
	    	}
	    	if(!StringUtils.isEmpty(studentVo.getSchoolCode())) {
	    		String[] schoolCodeArray = studentVo.getSchoolCode().split("-");
	    		String tempString="(";
		    	for (int i=0;i!=schoolCodeArray.length;i++) {
		    		if(i!=schoolCodeArray.length-1)
		    		{
		    			tempString+="a.school_code='"+schoolCodeArray[i]+"\' or ";
		    		}
		    		else {
		    			tempString+="a.school_code='"+schoolCodeArray[i]+"\')";
		    		}
		    	}
	    		sql.WHERE(tempString);
	        }
	    	
	    	return sql.toString();
		}
		@Deprecated
		public String selectStudentRank(@Param("item")StudentVo studentVo) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
			sql.SELECT("a.uuid");
	    	sql.FROM("kernel_student_data a");
	    	sql.LEFT_OUTER_JOIN("kernel_student_inform b on (a.student_code=b.student_code) or (a.identity_card=b.identity_card)");
	    
	    	sql.WHERE("a.active=1");
	    	sql.WHERE("a.total_points>10");
	    	if(studentVo.getType()!=null) {
	    		if(studentVo.getType()>1) {
	    			sql.WHERE("a.type=#{item.type}");
	    		}else {
	    			sql.WHERE("a.type&1=#{item.type}");
				}
	    	}
	    	if(studentVo.getBinding()!=null) {
	    		sql.WHERE("a.binding=#{item.binding}");
	    	}
	    	if(!StringUtils.isEmpty(studentVo.getClassS())) {
	    		sql.WHERE("b.classS=#{item.classS}");
	    	}
	    	if(!StringUtils.isEmpty(studentVo.getGrade())) {
	    		sql.WHERE("b.grade=#{item.grade}");
	    	}
	    	if(!StringUtils.isEmpty(studentVo.getSex())) {
	    		sql.WHERE("b.sex=#{item.sex}");
	    	}
	    	if(!StringUtils.isEmpty(studentVo.getName())) {
	    		sql.WHERE("a.name like CONCAT('%',#{item.name},'%')");
	    	}
	    	if(!StringUtils.isEmpty(studentVo.getSchoolId())) {
	    		sql.WHERE("b.school_id=#{item.schoolId}");
	    	}
	    	if(!StringUtils.isEmpty(studentVo.getSchoolCode())) {
	    		String[] schoolCodeArray = studentVo.getSchoolCode().split("-");
	    		String tempString="(";
		    	for (int i=0;i!=schoolCodeArray.length;i++) {
		    		if(i!=schoolCodeArray.length-1)
		    		{
		    			tempString+="a.school_code='"+schoolCodeArray[i]+"\' or ";
		    		}
		    		else {
		    			tempString+="a.school_code='"+schoolCodeArray[i]+"\')";
		    		}
		    	}
	    		sql.WHERE(tempString);
	        }
	    	
	    	return sql.toString();
		}
		public String selectSchoolInformVos(@Param("item")SchoolInfoVo schoolInfoVo) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
			sql.SELECT("a.school_code,a.school_name,a.school_id,a.postal_code,a.address,a.contacts,a.active");
					//+ "b.headcount,b.studentcount,b.teachercount,studentactcount,teacheractcount");
			sql.FROM("kernel_school_inform a");
			
//			sql.LEFT_OUTER_JOIN("(select school_code ,count(*) as headcount ,"
//					+ "count(case when type&1=0 then 1 else null end) as studentcount,"
//					+ "count(case when type&1=1 then 1 else null end) as teachercount,"
//					+ "count(case when type&1=0 and active !=0 then 1 else null end) as studentactcount,"
//					+ "count(case when type&1=1 and active !=0 then 1 else null end) as teacheractcount from kernel_student_data) b on a.school_code=b.school_code");
//			
			if(schoolInfoVo.getActive()!=null) {
	        	 sql.WHERE("a.active=#{item.active}");
	        }
//			if(!StringUtils.isEmpty(schoolInfoVo.getSchoolId())) {
//	    		sql.WHERE("a.school_id=#{item.schoolId}");
//	    	}
			if(!StringUtils.isEmpty(schoolInfoVo.getSchoolCode())) {
	    		String[] schoolCodeArray = schoolInfoVo.getSchoolCode().split("-");
	    		String tempString="(";
		    	for (int i=0;i!=schoolCodeArray.length;i++) {
		    		if(i!=schoolCodeArray.length-1)
		    		{
		    			tempString+="a.school_code='"+schoolCodeArray[i]+"\' or ";
		    		}
		    		else {
		    			tempString+="a.school_code='"+schoolCodeArray[i]+"\')";
		    		}
		    	}
	    		sql.WHERE(tempString);
	        }
//	    	if(!StringUtils.isEmpty(schoolInfoVo.getPostalCode())) {		
//	    		String[] postalCodeArray = schoolInfoVo.getPostalCode().split("-");
//	    		String tempString="(";
//	    		for (int i=0;i!=postalCodeArray.length;i++) {
//	    			if(i!=postalCodeArray.length-1)
//		    		{
//		    			tempString+="a.postal_code='"+postalCodeArray[i]+"\' or ";
//		    		}
//		    		else {
//		    			tempString+="a.postal_code='"+postalCodeArray[i]+"\')";
//		    		}
//				}
//	    		sql.WHERE(tempString);
//		    }
			return sql.toString();
		}
	}


	
}

