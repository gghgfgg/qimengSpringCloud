package com.qimeng.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import com.qimeng.main.vo.DeviceInformVo;
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
	
	
	public class SqlFactory extends SQL{
		public String selectDeviceInformVos(@Param("item")DeviceInformVo deviceInformVo) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
			sql.SELECT("a.machine_id,a.serial_number,a.school_code,a.postal_code,"
					+ "a.active,b.status,b.status_type as type");
	    	sql.FROM("machine_device_management a");
	    	sql.INNER_JOIN("machine_device_state b on a.serial_number=b.serial_number");
	    
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
	    	if(!StringUtils.isEmpty(deviceInformVo.getPostalCode())) {		
	    		String[] postalCodeArray = deviceInformVo.getPostalCode().split("-");
	    		String tempString="(";
	    		for (int i=0;i!=postalCodeArray.length;i++) {
	    			if(i!=postalCodeArray.length-1)
		    		{
		    			tempString+="a.postal_code='"+postalCodeArray[i]+"\' or ";
		    		}
		    		else {
		    			tempString+="a.postal_code='"+postalCodeArray[i]+"\')";
		    		}
				}
	    		sql.WHERE(tempString);
		    }
	    	
	    	return sql.toString();
		}
		public String selectStudentVos(@Param("item")StudentVo studentVo) {
			SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
			sql.SELECT("a.uuid");
	    	sql.FROM("kernel_student_data a");
	    	sql.INNER_JOIN("kernel_student_inform b on (a.student_code=b.student_code) or (a.identity_card=b.identity_card)");
	    
	    	if(studentVo.getActive()!=null) {
	        	 sql.WHERE("a.active=#{item.active}");
	        }
	    	if(studentVo.getType()!=null) {
	    		sql.WHERE("a.type=#{item.type}");
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
	    	if(studentVo.getBinding()!=null) {
	    		sql.WHERE("a.binding=#{item.binding}");
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
	}
}

