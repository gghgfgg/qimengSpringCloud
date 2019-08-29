package com.qimeng.main;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.qimeng.main.entity.ApplicationManagement;
import com.qimeng.main.service.ApplicationManagementService;
import com.qimeng.main.service.StudentRankService;
import com.qimeng.main.util.RedisPage;
import com.qimeng.main.util.StaticGlobal;
import com.qimeng.main.vo.RequestMessage;
import com.qimeng.main.vo.ResponseMessage;
import com.qimeng.main.vo.StudentVo;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月27日 下午7:44:50 
* @version 1.0 
* @parameter  
* @since  
* @return  */
@RestController
@RequestMapping("/rank")
public class RankController {
	private static Logger logger = Logger.getLogger(RankController.class);

	@Autowired
	ApplicationManagementService applicationManagementService;

	@Autowired
	StudentRankService studentRankService;
	@RequestMapping("/getsturanklist/{page}")
	public String getStudentRankList(@PathVariable("page") Integer page, @RequestBody JSONObject message) {
		RequestMessage<StudentVo> requestMessage = JSON.parseObject(message.toString(),
				new TypeReference<RequestMessage<StudentVo>>() {
				});
		StudentVo studentVo = (StudentVo) requestMessage.getData();
		logger.debug(studentVo.toString());
		try {
			ApplicationManagement applicationManagement = applicationManagementService
					.selectApplicationManagementByAppId(requestMessage.getAppID(), StaticGlobal.ACTIVE);
			if (applicationManagement == null) {
				ResponseMessage<String> responseMessage = new ResponseMessage<String>();
				responseMessage.setData("");
				responseMessage.setFailedMessage("该appid没有权限");
				return JSONObject.toJSONString(responseMessage);
			}

			RedisPage<StudentVo> studentPageInfo = studentRankService.StudentRankList(page, studentVo);
			ResponseMessage<RedisPage<StudentVo>> responseMessage = new ResponseMessage<RedisPage<StudentVo>>();
			responseMessage.setData(studentPageInfo);
			responseMessage.setSuccessMessage("获取学生列表信息成功");
			return JSONObject.toJSONString(responseMessage);

		} catch (Exception e) {
			// TODO: handle exception
			ResponseMessage<String> responseMessage = new ResponseMessage<String>();
			responseMessage.setData("");
			responseMessage.setFailedMessage(e.toString());
			return JSONObject.toJSONString(responseMessage);
		}
	}
}

