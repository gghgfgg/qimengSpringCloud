package com.qimeng.main.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageInfo;
import com.qimeng.main.dao.JoinDao;
import com.qimeng.main.entity.SchoolInform;
import com.qimeng.main.entity.StudentData;
import com.qimeng.main.util.RedisPage;
import com.qimeng.main.util.RedisUtil;
import com.qimeng.main.vo.DeviceInformVo;
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
	JoinDao joinDao;
	public void add(StudentData studentDate) {
		redisUtil.zsSet(STURANK_KEY,studentDate.getUuid(),-studentDate.getTotalPoints());
	}
	
	public void addlist() {
		List<String> uuid=joinDao.selectStudentRank(new StudentVo());
		int i=0;
		for (String string : uuid) {
			redisUtil.zsSet(STURANK_KEY,string,i);
			i--;
		}
		
	}
	
	public RedisPage<StudentVo> StudentRankList(Integer page,StudentVo studentVo) {
		// TODO Auto-generated method stub
		String schoolcode=new String();
		List<SchoolInform> schoollist=new ArrayList<SchoolInform>();
		if(!StringUtils.isEmpty(studentVo.getPostalCode())) {
			String codeString=postalCodeService.selectPostalCode(studentVo.getPostalCode());
			schoollist.addAll(schoolInformService.selectSchoolCodeList(codeString));
		}
		if(!StringUtils.isEmpty(studentVo.getSchoolName())) {
			 
			 List<SchoolInform> schoollistbyname=schoolInformService.selectSchoolInformByName(studentVo.getSchoolName());
			 if(!schoollist.isEmpty()) {
				 schoollistbyname.retainAll(schoollist);
			 }
			 if(!schoollistbyname.isEmpty()) {
				 for (SchoolInform schoolInform : schoollistbyname) {
					 schoolcode+=schoolInform.getSchoolCode();
					 schoolcode+="-";
				}
			 }else {
				 for (SchoolInform schoolInform : schoollist) {
					 schoolcode+=schoolInform.getSchoolCode();
					 schoolcode+="-";
				}
			}
			
			if(studentVo.getSchoolCode()!=null) {
				schoolcode=studentVo.getSchoolCode()+"-"+schoolcode;
			}
			studentVo.setSchoolCode(schoolcode);
		}
		if(redisUtil.hasKey(RANKTEMP_KEY)){
			redisUtil.del(RANKTEMP_KEY);
		}
		if(redisUtil.hasKey(RANKRESULT_KEY)) {
			redisUtil.del(RANKRESULT_KEY);
		}
		List<String> uuid=joinDao.selectStudentRank(studentVo);
		redisUtil.sSet(RANKTEMP_KEY, uuid);

		redisUtil.intersectAndStore(STURANK_KEY, RANKTEMP_KEY, RANKRESULT_KEY);
		
		List<StudentVo> result = new ArrayList<>();
		RedisPage<StudentVo> redisPage=new RedisPage<StudentVo>(page,(int)redisUtil.zsCard(RANKRESULT_KEY));
		
		Set<Object> setrankSet=redisUtil.zsrange(RANKRESULT_KEY,redisPage.getStartRow(),redisPage.getEndRow());
		
		
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
}

