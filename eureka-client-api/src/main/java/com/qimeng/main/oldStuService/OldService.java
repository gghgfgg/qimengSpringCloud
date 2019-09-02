package com.qimeng.main.oldStuService;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.qimeng.main.util.EncryptUtil;
import com.qimeng.main.vo.DeviceStatusVo;
import com.qimeng.main.vo.StudentInfoVo;


@Service
public class OldService {
	private static Logger logger = Logger.getLogger(OldService.class);
	public static final String BASE_URL = "http://admin.qimenghb.com/portal/inf/";
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Autowired
	RestTemplate restTemplate;
	  
	public String getStudent(String stucode) {
		 HashMap<String, String> map = new HashMap<>();
	     map.put("code",stucode);
	     map.put("class","1");
	     String json = restTemplate.getForEntity(BASE_URL+"getUser.jsp?code={code}&class={class}", String.class, map).getBody();
		 System.out.println(json);
		 return json;
	}
	
	public String uploadIntegral(StudentInfoVo stuInfo,String machineID) throws Exception
	{
		 String data=stuInfo.getStuCode()+":"+stuInfo.getWasteType()+":"+stuInfo.getUnit()+":"+machineID;
		 EncryptUtil encryptUtil=new EncryptUtil();
		 String encode=encryptUtil.encode(data);
		 logger.info(encode);
		 String json = restTemplate.postForEntity(BASE_URL+"uploadIntegral.jsp?", encode,String.class).getBody();
		 return json;
	}
	
	public String updateJqm(DeviceStatusVo machineInfo,String machineID)
	{
		 String json = restTemplate.getForEntity(BASE_URL+"updateJqm.jsp?bh={bh}&jqm={jqm}",String.class,machineID,machineInfo.getSerialNumber()).getBody();
		 return json;
	}
	
	public String newuploadIntegral(StudentInfoVo stuInfo,String machineID) throws Exception
	{
		 HashMap<String, String> map = new HashMap<>();
	     map.put("code",stuInfo.getStuCode());
	     map.put("classtype",String.valueOf(stuInfo.getWasteType()));
	     map.put("snum", String.valueOf(stuInfo.getUnit()));
	     map.put("maid",machineID);
		 String json = restTemplate.getForEntity("http://sys.qimenghb.com/api/getjf.php?code={code}&classtype={classtype}&snum={snum}&maid={maid}",
                                                  String.class,map).getBody();
		 return json;
	}
	
	
	public String bindstu(String openid,String unionid,String mobile) throws Exception
	{
		 HashMap<String, String> map = new HashMap<>();
		 
	     map.put("openid",openid);
	     map.put("unionid",unionid);
	     map.put("mobile",mobile);
		 String json = restTemplate.getForEntity("http://sys.qimenghb.com/api/getbdmob.php?openid={openid}&unionid={unionid}&mobile={mobile}",
                                                  String.class,map).getBody();
		 return json;
	}
}
