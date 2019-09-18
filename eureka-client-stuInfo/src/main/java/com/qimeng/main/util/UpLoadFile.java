package com.qimeng.main.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.entity.StudentInform;
import com.qimeng.main.entity.Uploadlog;
import com.qimeng.main.service.UploadlogService;
import com.qimeng.main.util.ExcelImpotUtils.FieldDefine;
import com.qimeng.main.vo.StudentVo;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2019年8月16日 上午11:52:32
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Service
public class UpLoadFile {
	private static Logger logger = Logger.getLogger(UpLoadFile.class);
	@Autowired
	UploadlogService uploadlogService;

	public File getUploadFile(String fileName, String appId, String operator) {
		File upload = new File("/upload/");
		if (!upload.exists()) {
			upload.mkdirs();
		}
		String path = upload.getAbsolutePath(); // 本地路径
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
		String time = simpleDateFormat.format(System.currentTimeMillis());
		String url = "/" + time + fileName;
		// 存储地址
		String destFileName = path + url;
		File destFile = new File(destFileName);
		destFile.getParentFile().mkdirs();
		Uploadlog uploadlog = new Uploadlog();
		uploadlog.setAppId(appId);
		uploadlog.setFile(url);
		uploadlog.setName(fileName);
		uploadlog.setOperator(operator);
		uploadlog.setCreateTime(new Date());
		uploadlogService.insertUploadlog(uploadlog);
		return destFile;
	}

	public List<StudentInform> uploadFileToList(File upload) {
		try {
			InputStream inputStream = new FileInputStream(upload);
			List<FieldDefine> fieldMap = new ArrayList<FieldDefine>();
			fieldMap.add(new FieldDefine("学生姓名", "name"));
			fieldMap.add(new FieldDefine("性别", "sex"));
			fieldMap.add(new FieldDefine("学籍号", "studentCode"));
			fieldMap.add(new FieldDefine("身份证件号", "identityCard"));
			fieldMap.add(new FieldDefine("籍贯", "nativePlace"));
			fieldMap.add(new FieldDefine("民族", "nation"));
			fieldMap.add(new FieldDefine("联系电话", "phone"));
			fieldMap.add(new FieldDefine("学校名称", "schoolName"));
			fieldMap.add(new FieldDefine("学校标识码", "schoolId"));
			fieldMap.add(new FieldDefine("出生日期", "birthday"));
			fieldMap.add(new FieldDefine("出生地", "birthplace"));
			fieldMap.add(new FieldDefine("国籍地区", "nationality"));
			fieldMap.add(new FieldDefine("身份证件类型", "identityType"));
			fieldMap.add(new FieldDefine("年级", "grade"));
			fieldMap.add(new FieldDefine("班级", "classS"));
			fieldMap.add(new FieldDefine("港澳台侨外", "overseasChinese"));
			fieldMap.add(new FieldDefine("政治面貌", "politicsStatus"));
			fieldMap.add(new FieldDefine("健康状况", "health"));
			fieldMap.add(new FieldDefine("户口所在地", "censusRegister"));
			fieldMap.add(new FieldDefine("户口性质", "censusRegisterType"));
			fieldMap.add(new FieldDefine("入学年月", "enrollmentTime"));
			fieldMap.add(new FieldDefine("入学方式", "entranceWay"));
			fieldMap.add(new FieldDefine("就读方式", "studyingWay"));
			fieldMap.add(new FieldDefine("现住址", "address"));
			fieldMap.add(new FieldDefine("通讯地址", "contactAddress"));
			fieldMap.add(new FieldDefine("家庭住址", "residence"));
			fieldMap.add(new FieldDefine("邮政编码", "postalCode"));
			fieldMap.add(new FieldDefine("是否进城务工人员子女", "workersChildren"));
			fieldMap.add(new FieldDefine("是否独生子女", "onlyChild"));
			fieldMap.add(new FieldDefine("是否受过学前教育", "preschoolEducation"));
			fieldMap.add(new FieldDefine("是否留守儿童", "leftoverChildren"));
			fieldMap.add(new FieldDefine("是否孤儿", "orphan"));
			fieldMap.add(new FieldDefine("是否烈士优抚子女", "martyrChildren"));
			fieldMap.add(new FieldDefine("是否需要申请资助", "funding"));
			fieldMap.add(new FieldDefine("是否享受一补", "boarderAlimony"));
			fieldMap.add(new FieldDefine("学籍辅号", "auxiliaryNumber"));
			fieldMap.add(new FieldDefine("班内学号", "studentNumber"));
			fieldMap.add(new FieldDefine("学生来源", "studentSource"));
			fieldMap.add(new FieldDefine("随班就读", "learningClass"));
			fieldMap.add(new FieldDefine("残疾类型", "disabilityTypes"));
			fieldMap.add(new FieldDefine("家庭成员姓名", "familyName"));
			fieldMap.add(new FieldDefine("家庭成员关系", "familyRelationship"));
			fieldMap.add(new FieldDefine("家庭成员工作单位", "familyWork"));
			fieldMap.add(new FieldDefine("家庭成员现住址", "familyResidence"));
			fieldMap.add(new FieldDefine("家庭成员户口所在地", "familyCensusRegister"));
			fieldMap.add(new FieldDefine("家庭成员联系电话", "familyPhone"));
			fieldMap.add(new FieldDefine("快速关联手机号", "teacherPhone"));
			List<StudentInform> list=ExcelImpotUtils.excelToList(inputStream,0, StudentInform.class,fieldMap,upload.getName());
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("上传学生信息文件异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
	
	public void exporFile(List<StudentVo> studentList, String sheetName,File expor) {
		// TODO Auto-generated method stub
		try {
			List<FieldDefine> fieldMap = new ArrayList<FieldDefine>();
			
			fieldMap.add(new FieldDefine("学生姓名", "name"));
			fieldMap.add(new FieldDefine("性别", "sex"));
			fieldMap.add(new FieldDefine("学籍号", "studentCode"));
			fieldMap.add(new FieldDefine("身份证件号", "identityCard"));
			fieldMap.add(new FieldDefine("二维码链接", "qrcode"));
			fieldMap.add(new FieldDefine("学校名称", "schoolName"));
			fieldMap.add(new FieldDefine("学校标识码", "schoolCode"));
			fieldMap.add(new FieldDefine("年级", "grade"));
			fieldMap.add(new FieldDefine("班级", "classS"));
			fieldMap.add(new FieldDefine("uuid", "uuid"));
			fieldMap.add(new FieldDefine("邮政编码", "postalCode"));
			fieldMap.add(new FieldDefine("所在地区", "postalCodename"));
			fieldMap.add(new FieldDefine("是否绑定", "binding"));
			fieldMap.add(new FieldDefine("是否参与活动", "active"));
			fieldMap.add(new FieldDefine("首次参活动时间", "firstTime"));
			fieldMap.add(new FieldDefine("最后参与时间", "lastTime"));
			fieldMap.add(new FieldDefine("参与活动次数", "activityCount"));
			fieldMap.add(new FieldDefine("获得积分", "totalPoints"));
			fieldMap.add(new FieldDefine("使用积分", "usedPoints"));
			fieldMap.add(new FieldDefine("扣除积分", "deductPoints"));
			
			OutputStream outputStream = new FileOutputStream(expor);
			ExcelImpotUtils.listToExcel(studentList, fieldMap, sheetName, outputStream);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("导出学生信息文件异常");
			logger.error("Error:", e);
			throw new RuntimeException(e);
		}
	}
}

