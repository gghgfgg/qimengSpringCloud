package com.qimeng.main.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimeng.main.entity.StudentInform;
import com.qimeng.main.entity.Uploadlog;
import com.qimeng.main.service.UploadlogService;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月16日 上午11:52:32 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@Service
public class UpLoadFile {
	private static Logger logger = Logger.getLogger(UpLoadFile.class); 
	@Autowired
	UploadlogService uploadlogService;
	
	public File getUploadFile(String fileName,String appId,String operator) {
		File upload=new File("/upload/");
		if(!upload.exists()){
			upload.mkdirs();	 
		}
		String path=upload.getAbsolutePath(); //本地路径
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
        String time = simpleDateFormat.format(System.currentTimeMillis());
        String url="/"+ time + fileName;
        //存储地址
        String destFileName = path +url;
        File destFile = new File(destFileName);
        destFile.getParentFile().mkdirs();
        Uploadlog uploadlog=new Uploadlog();
        uploadlog.setAppId(appId);
        uploadlog.setFile(url);
        uploadlog.setName(fileName);
        uploadlog.setOperator(operator);
        uploadlog.setCreateTime(new Date());
        uploadlogService.insertUploadlog(uploadlog);
        return destFile;
	}
	
	List<StudentInform> uploadFileToList(File upload){
		try {
			return ExcelUtil.excelToList();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("上传学生信息文件异常");
			logger.error("Error:",e);
			throw new RuntimeException(e);
		}
		
	}
}

