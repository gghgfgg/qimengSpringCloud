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

import com.qimeng.main.entity.StudentInform;;
@Mapper
public interface StudentInformDao {
	
	static String tablename="kernel_student_inform";
	static String fields="name,sex,student_code,identity_card,native_place,nation,"
			+ "phone,school_name,school_id,birthday,birthplace,"
			+ "nationality,identity_type,grade,classS,overseas_chinese,"
			+ "politics_status,health,census_register,census_register_type,enrollment_time,"
			+ "entrance_way,studying_way,address,contact_address,residence,"
			+ "postal_code,workers_children,only_child,preschool_education,leftover_children,"
			+ "orphan,martyr_children,funding,boarder_alimony,auxiliary_number,"
			+ "student_number,student_source,learning_class,disability_types,family_name,"
			+ "family_relationship,family_work,family_residence,family_census_register,family_phone";
	
	static String item="#{item.name},#{item.sex},#{item.studentCode},#{item.identityCard},#{item.nativePlace},#{item.nation},"
			+ "#{item.phone},#{item.schoolName},#{item.schoolId},#{item.birthday},#{item.birthplace},"
			+ "#{item.nationality},#{item.identityType},#{item.grade},#{item.classS},#{item.overseasChinese},"
			+ "#{item.politicsStatus},#{item.health},#{item.censusRegister},#{item.censusRegisterType},#{item.enrollmentTime},"
			+ "#{item.entranceWay},#{item.studyingWay},#{item.address},#{item.contactAddress},#{item.residence},"
			+ "#{item.postalCode},#{item.workersChildren},#{item.onlyChild},#{item.preschoolEducation},#{item.leftoverChildren},"
			+ "#{item.orphan},#{item.martyrChildren},#{item.funding},#{item.boarderAlimony},#{item.auxiliaryNumber},"
			+ "#{item.studentNumber},#{item.studentSource},#{item.learningClass},#{item.disabilityTypes},#{item.familyName},"
			+ "#{item.familyRelationship},#{item.familyWork},#{item.familyResidence},#{item.familyCensusRegister},#{item.familyPhone}";
	
	static String update="name=VALUES(name),sex=VALUES(sex),native_place=VALUES(native_place),nation=VALUES(nation),"
			+ "phone=VALUES(phone),school_name=VALUES(school_name),school_id=VALUES(school_id),birthday=VALUES(birthday),birthplace=VALUES(birthplace),"
			+ "nationality=VALUES(nationality),identity_type=VALUES(identity_type),grade=VALUES(grade),classS=VALUES(classS),overseas_chinese=VALUES(overseas_chinese),"
			+ "politics_status=VALUES(politics_status),health=VALUES(health),census_register=VALUES(census_register),census_register_type=VALUES(census_register_type),enrollment_time=VALUES(enrollment_time),"
			+ "entrance_way=VALUES(entrance_way),studying_way=VALUES(studying_way),address=VALUES(address),contact_address=VALUES(contact_address),residence=VALUES(residence),"
			+ "postal_code=VALUES(postal_code),workers_children=VALUES(workers_children),only_child=VALUES(only_child),preschool_education=VALUES(preschool_education),leftover_children=VALUES(leftover_children),"
			+ "orphan=VALUES(orphan),martyr_children=VALUES(martyr_children),funding=VALUES(funding),boarder_alimony=VALUES(boarder_alimony),auxiliary_number=VALUES(auxiliary_number),"
			+ "student_number=VALUES(student_number),student_source=VALUES(student_source),learning_class=VALUES(learning_class),disability_types=VALUES(disability_types),family_name=VALUES(family_name),"
			+ "family_relationship=VALUES(family_relationship),family_work=VALUES(family_work),family_residence=VALUES(family_residence),family_census_register=VALUES(family_census_register),family_phone=VALUES(family_phone)";
	
	static String updatecode=",student_code=VALUES(student_code),identity_card=VALUES(identity_card)";
	
	@Insert({"<script> insert into "+tablename+"("+fields+") values" +
            "<foreach collection=\"list\" item=\"item\" index=\"index\"  separator=\",\"> "+
            "("+item+")"+
            "</foreach>"
            + "ON DUPLICATE KEY UPDATE " + update+ "</script>"})
	int insertStudentInformList(@Param("list")List<StudentInform> studentInformList);
	
	@Insert("insert into "+tablename+"("+fields+") values" + "("+item+") ON DUPLICATE KEY UPDATE " + update+updatecode )
	@Options(useGeneratedKeys = true,keyProperty = "id")
	int insertStudentInform(@Param("item")StudentInform studentInform);
	
	@UpdateProvider(type = SqlFactory.class,method = "updateByIdentityCardOrStudentCode")
	int updateStudentInformByIdentityCardOrStudentCode(@Param("item")StudentInform studentInform);
	
	@SelectProvider(type = SqlFactory.class,method = "selectStudentInform")
	List<StudentInform> selectStudentInform(@Param("item")StudentInform studentInform);
	
	
	public class SqlFactory extends SQL{
	    public String updateByIdentityCardOrStudentCode(@Param("item")StudentInform studentInform){

	        SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
	        sql.UPDATE(tablename);
	        String[] tempfields;
	        tempfields = fields.split(","); // 分割字符串
	        String[] tempitem;
	        tempitem = item.split(","); // 分割字符串
	        String tempString=new String();
	        for (int i = 0; i < tempitem.length; i++) {
	        	tempString+=tempfields[i]+"="+tempitem[i];
	        	if(i!=tempitem.length-1)
	        		tempString+=",";
			}
	        sql.SET(tempString);
	       
	        if(!StringUtils.isEmpty(studentInform.getStudentCode())){
	            sql.WHERE("student_code=#{item.studentCode}");
	        }
	        else if(!StringUtils.isEmpty(studentInform.getIdentityCard())){
	        	 sql.WHERE("identity_card=#{item.identityCard}");
			}
	        return sql.toString();
	        }
	    public String selectStudentInform(@Param("item")StudentInform studentInform){
	    	SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
	    	
	    	sql.SELECT("id,"+fields);
	    	sql.FROM(tablename);
	    	if(!StringUtils.isEmpty(studentInform.getStudentCode())){
	            sql.WHERE("student_code=#{item.studentCode}");
	        }
	    	if(!StringUtils.isEmpty(studentInform.getIdentityCard())){
	        	 sql.WHERE("identity_card=#{item.identityCard}");
			}
	    	if(!StringUtils.isEmpty(studentInform.getName())){
	        	 sql.WHERE("name=#{item.name}");
			}
	    	if(!StringUtils.isEmpty(studentInform.getSex())){
	        	 sql.WHERE("sex=#{item.sex}");
			}
	    	if(!StringUtils.isEmpty(studentInform.getSchoolName())){
	        	 sql.WHERE("school_name=#{item.schoolName}");
			}
	    	if(!StringUtils.isEmpty(studentInform.getSchoolId())){
	        	 sql.WHERE("school_id=#{item.schoolId}");
			}
	    	if(!StringUtils.isEmpty(studentInform.getGrade())){
	        	 sql.WHERE("grade=#{item.grade}");
			}
	    	if(!StringUtils.isEmpty(studentInform.getClassS())){
	        	 sql.WHERE("classS=#{item.classS}");
			}
	    	return sql.toString();
	    }
	    }

}
