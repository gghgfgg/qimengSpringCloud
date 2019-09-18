package com.qimeng.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qimeng.main.dao.JoinDao;
import com.qimeng.main.entity.SchoolInform;
import com.qimeng.main.entity.SchoolRecycleCount;
import com.qimeng.main.entity.StudentData;
import com.qimeng.main.util.RedisPage;
import com.qimeng.main.util.RedisUtil;
import com.qimeng.main.util.StaticGlobal;
import com.qimeng.main.vo.RecycleCountVo;
import com.qimeng.main.vo.SchoolInfoVo;
import com.qimeng.main.vo.StudentVo;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月27日 下午5:10:44 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class StudentRankService {

	private static Logger logger = Logger.getLogger(StudentRankService.class);
	public static final String STURANK_KEY="RankKey::StudentRank";
	public static final String RANKTEMP_KEY="RankKey::RankTemp";
	public static final String RANKRESULT_KEY="RankKey::RankResult";
	
	public static final String SCHRANK_KEY="RankKey::SchoolRank";
	public static final String SCHRANKTEMP_KEY="RankKey::SchoolRankTemp";
	public static final String SCHRANKRESULT_KEY="RankKey::SchoolRankResult";
	
	
	public static final String COUNT_KEY="Count::SchoolCount";
	public static final String STUCOUNT_KEY="Count::StuCount";
	public static final String TEACHCOUNT_KEY="Count::TeachCount";
	public static final String STUCOUNTACT_KEY="Count::StuActCount";
	public static final String TEACHCOUNTACT_KEY="Count::TeachActCount";
	
	@Autowired
	RedisUtil redisUtil;
	@Autowired
	StudentService studentService;
	@Autowired
	PostalCodeService postalCodeService;
	@Autowired
	StudentInformService studentInformService;
	@Autowired
	SchoolInformService schoolInformService;
	@Autowired
	StudentDataService studentDataService;
	@Autowired
	SchoolRecycleCountService schoolRecycleCountService;
	@Autowired
	SchoolService schoolService;
	
	@Autowired
	JoinDao joinDao;
	
	/**
	 * 添加到积分排行榜
	 * @param studentDate
	 */
	public void addStudentPoints(StudentData studentDate) {
		redisUtil.zsSet(STURANK_KEY,studentDate.getUuid(),-studentDate.getTotalPoints());
	}

	/**
	 * 初始化如果积分排行榜为空创建积分排行榜
	 */
	public void addListPoints() {
		if(!redisUtil.hasKey(STURANK_KEY)) {
			StudentData studentData=new StudentData();
			studentData.setActive((byte)1);
			List<StudentData> list=studentDataService.selectStudentDataList(studentData);
			if(!list.isEmpty())
			{
				redisUtil.zSetList(STURANK_KEY, list);
			}
		}
	}
	/**
	 * 添加到学校积分排行榜
	 * @param studentDate
	 */
	public void addSchoolPoints(SchoolRecycleCount schoolRecycleCount,int points) {
		redisUtil.zsSet(SCHRANK_KEY,schoolRecycleCount.getSchoolCode(),-points);
	}
	/**
	 * 初始化如果积分排行榜为空创建积分排行榜
	 */
	public void addSchoolListPoints() {
		if(!redisUtil.hasKey(SCHRANK_KEY)) {
			List<SchoolRecycleCount> list=schoolRecycleCountService.selectSchoolRecycleCountList(new SchoolRecycleCount());
			if(!list.isEmpty())
			{
				redisUtil.zIncrementScoreList(SCHRANK_KEY, list);
			}
		}
	}
	
	
	/**
	 * 添加到学校关联
	 * @param studentDate
	 */
	public void addSchool(String key,StudentData studentDate) {
		redisUtil.zsSet(key,studentDate.getUuid(),Long.valueOf(studentDate.getSchoolCode()));
	}
	
	/**
	 * 添加到学校关联人数
	 * @param studentDate
	 */
	public void addListSchoolCount(String schoolCode) {
		StudentData studentData=new StudentData();
		studentData.setSchoolCode(schoolCode);
		List<StudentData> list=studentDataService.selectStudentDataList(studentData);
		if(!list.isEmpty()) {
			redisUtil.zSetCountList(COUNT_KEY, list);
		}
		
		studentData.setType(StaticGlobal.STUDENT);
		List<StudentData> listStu=studentDataService.selectStudentDataList(studentData);
		if(!listStu.isEmpty()) {
			redisUtil.zSetCountList(STUCOUNT_KEY, listStu);
		}
		studentData.setType(StaticGlobal.TEACHER);
		List<StudentData> listTeach=studentDataService.selectStudentDataList(studentData);
		if(!listTeach.isEmpty()) {
			redisUtil.zSetCountList(TEACHCOUNT_KEY, listTeach);
		}
		
		studentData.setActive(StaticGlobal.ACTIVE);
		List<StudentData> listTeachAct=studentDataService.selectStudentDataList(studentData);
		if(!listTeachAct.isEmpty()) {
			redisUtil.zSetCountList(TEACHCOUNTACT_KEY, listTeachAct);
		}
		studentData.setType(StaticGlobal.STUDENT);
		List<StudentData> listStuAct=studentDataService.selectStudentDataList(studentData);
		if(!listStuAct.isEmpty()) {
			redisUtil.zSetCountList(STUCOUNTACT_KEY, listStuAct);
		}
	}	
	
	/**
	 * 添加到学校关联人数
	 * @param studentDate
	 */
	public void addSchoolCount(StudentData studentData) {
		addSchool(COUNT_KEY,studentData);
		if((studentData.getType()&StaticGlobal.STUDENT)==StaticGlobal.STUDENT) {
			addSchool(STUCOUNT_KEY,studentData);
			if(studentData.getActive()==StaticGlobal.ACTIVE) {
				addSchool(STUCOUNTACT_KEY,studentData);
			}
		}
		if((studentData.getType()&StaticGlobal.TEACHER)==StaticGlobal.TEACHER) {
			addSchool(TEACHCOUNT_KEY,studentData);
			if(studentData.getActive()==StaticGlobal.ACTIVE) {
				addSchool(TEACHCOUNTACT_KEY,studentData);
			}
		}
	}	
	
	/**
	 * 要获得哪个统计表
	 * @param studentVo
	 * @return
	 */
	String getCountKey(StudentVo studentVo) {
		if(studentVo.getActive()==null) {
			return COUNT_KEY;
		}
		if((studentVo.getType()&StaticGlobal.TEACHER)==StaticGlobal.TEACHER) {
			return TEACHCOUNTACT_KEY;
		}
		return STUCOUNTACT_KEY;
	}
	
	
	
	long Count(String key,String schoolCode) {
		if(StringUtils.isEmpty(schoolCode)) {
			return redisUtil.zsCard(key);
		}
		return redisUtil.zsCount(key,Long.valueOf(schoolCode),Long.valueOf(schoolCode));
	}
	long SchoolCount(String schoolCode) {
		return Count(COUNT_KEY,schoolCode);
	}
	long StudentCount(String schoolCode) {
		return Count(STUCOUNT_KEY,schoolCode);
	}
	long TeacherCount(String schoolCode) {
		return Count(TEACHCOUNT_KEY,schoolCode);
	}
	long StudentActCount(String schoolCode) {
		return Count(STUCOUNTACT_KEY,schoolCode);
	}
	long TeacherActCount(String schoolCode) {
		return Count(TEACHCOUNTACT_KEY,schoolCode);
	}
	
	
	public RedisPage<StudentVo> StudentRankList(Integer page,StudentVo studentVo) {
		// TODO Auto-generated method stub
		
		if(redisUtil.hasKey(RANKTEMP_KEY)){
			redisUtil.del(RANKTEMP_KEY);
		}
		if(redisUtil.hasKey(RANKRESULT_KEY)) {
			redisUtil.del(RANKRESULT_KEY);
		}
		String keyCount=getCountKey(studentVo);
		
		List<SchoolInform> schoollist=schoolService.getSchoolInformList(studentVo.getSchoolCode(), studentVo.getSchoolId(), studentVo.getPostalCode(), studentVo.getSchoolName());
		Set<Object> setrankSet;
		RedisPage<StudentVo> redisPage;
		if(schoollist==null) {
			return null;
		}
		if(schoollist.isEmpty()) {
			redisPage=new RedisPage<StudentVo>(page,(int)redisUtil.zsCard(STURANK_KEY));
			setrankSet=redisUtil.zsrange(STURANK_KEY,redisPage.getStartRow(),redisPage.getEndRow());
		}
		else {
			List<String> uuid=new ArrayList<String>();
			for (SchoolInform schoolInform : schoollist) {
				Set<Object> schoolSet=redisUtil.zrangeByScore(keyCount,Integer.valueOf(schoolInform.getSchoolCode()),Integer.valueOf(schoolInform.getSchoolCode()));
				for (Object object : schoolSet) {
					uuid.add(object.toString());
				}
			}
			redisUtil.sSet(RANKTEMP_KEY, uuid);
			redisUtil.intersectAndStore(STURANK_KEY, RANKTEMP_KEY, RANKRESULT_KEY);
			
			redisPage=new RedisPage<StudentVo>(page,(int)redisUtil.zsCard(RANKRESULT_KEY));
			setrankSet=redisUtil.zsrange(RANKRESULT_KEY,redisPage.getStartRow(),redisPage.getEndRow());
		}
		
		
		List<StudentVo> result = new ArrayList<>();
		for (Object object : setrankSet) {
			StudentVo item=new StudentVo();
			item.setUuid(object.toString());
			StudentData studentData=studentDataService.selectStudentDataByUuid(item.getUuid());
			item.setActive(studentData.getActive());
			item.setActivityCount(studentData.getActivityCount());
			item.setBinding(studentData.getBinding());
			item.setDeductPoints(studentData.getDeductPoints());
			item.setUsedPoints(studentData.getUsedPoints());
			item.setTotalPoints(studentData.getTotalPoints());
			item.setName(studentData.getName());
			item.setType(studentData.getType());
			item.setUuid(studentData.getUuid());
			result.add(item);
		}
		redisPage.setList(result);
		redisPage.setSize(result.size());
		return redisPage;
	}
	
	
	public RedisPage<SchoolInfoVo> SchoolRankList(Integer page,SchoolInfoVo schoolInfoVo) {
		// TODO Auto-generated method stub
		
		if(redisUtil.hasKey(RANKTEMP_KEY)){
			redisUtil.del(RANKTEMP_KEY);
		}
		if(redisUtil.hasKey(RANKRESULT_KEY)) {
			redisUtil.del(RANKRESULT_KEY);
		}
		
		
		List<SchoolInform> schoollist=schoolService.getSchoolInformList(schoolInfoVo.getSchoolCode(), schoolInfoVo.getSchoolId(), schoolInfoVo.getPostalCode(), schoolInfoVo.getSchoolName());
		Set<Object> setrankSet;
		RedisPage<SchoolInfoVo> redisPage;
		if(schoollist==null) {
			return null;
		}
		if(schoollist.isEmpty()) {
			redisPage=new RedisPage<SchoolInfoVo>(page,(int)redisUtil.zsCard(SCHRANK_KEY));
			setrankSet=redisUtil.zsrange(SCHRANK_KEY,redisPage.getStartRow(),redisPage.getEndRow());
		}
		else {
			List<String> uuid=new ArrayList<String>();
			for (SchoolInform schoolInform : schoollist) {
				uuid.add(schoolInform.getSchoolCode());
			}
			redisUtil.sSet(SCHRANKTEMP_KEY, uuid);
			redisUtil.intersectAndStore(STURANK_KEY, SCHRANKTEMP_KEY, RANKRESULT_KEY);
			
			redisPage=new RedisPage<SchoolInfoVo>(page,(int)redisUtil.zsCard(SCHRANKRESULT_KEY));
			setrankSet=redisUtil.zsrange(SCHRANKRESULT_KEY,redisPage.getStartRow(),redisPage.getEndRow());
		}
		
		
		List<SchoolInfoVo> result = new ArrayList<>();
		for (Object object : setrankSet) {
			SchoolInfoVo item=new SchoolInfoVo();
			item.setSchoolCode(object.toString());
			SchoolInform schoolInform=schoolInformService.selectSchoolInformBySchoolCode(item.getSchoolCode());
			item.setActive(schoolInform.getActive());
			item.setPostalCode(schoolInform.getPostalCode());
			item.setSchoolId(schoolInform.getSchoolId());
			item.setSchoolName(schoolInform.getSchoolName());
			item.setPostalCodename(postalCodeService.selectPostalCodeName(schoolInform.getPostalCode()));

			List<SchoolRecycleCount> list = schoolRecycleCountService
					.selectSchoolRecycleCountBySchoolCode(item.getSchoolCode());
			List<RecycleCountVo> listRecycleCountVo = new ArrayList<RecycleCountVo>();
			
			for (SchoolRecycleCount schoolRecycleCount : list) {
				RecycleCountVo recycleCountVo = new RecycleCountVo();
				recycleCountVo.setType(schoolRecycleCount.getType());
				recycleCountVo.setActivityCount(schoolRecycleCount.getActivityCount());
				recycleCountVo.setCount(schoolRecycleCount.getCount());
				recycleCountVo.setRemainder(schoolRecycleCount.getRemainder());
				recycleCountVo.setPoints(schoolRecycleCount.getPoints());
				listRecycleCountVo.add(recycleCountVo);
			}
			item.setListRecycleCountVo(listRecycleCountVo);
			result.add(item);
		}
		redisPage.setList(result);
		redisPage.setSize(result.size());
		return redisPage;
	}
	
}

