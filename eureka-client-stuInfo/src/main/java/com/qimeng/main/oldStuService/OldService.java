package com.qimeng.main.oldStuService;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.netflix.ribbon.proxy.annotation.Http.HttpMethod;
import com.qimeng.main.util.EncryptUtil;
import com.qimeng.main.vo.MachineInfo;
import com.qimeng.main.vo.OldResponseJson;
import com.qimeng.main.vo.StudentInfoVo;


@Service
public class OldService {

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
		 System.out.println(encode);
		 String json = restTemplate.postForEntity(BASE_URL+"uploadIntegral.jsp?", encode,String.class).getBody();
		 System.out.println(json);
		 return json;
	}
	
	public String updateJqm(MachineInfo machineInfo)
	{
		 String json = restTemplate.getForEntity(BASE_URL+"updateJqm.jsp?bh={bh}&jqm={jqm}",String.class,machineInfo.getMachineID(),machineInfo.getSerialNumber()).getBody();
		 System.out.println(json);
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
		 System.out.println(json);
		 return json;
	}
}
