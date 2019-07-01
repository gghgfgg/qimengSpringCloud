package com.qimeng.main;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.qimeng.main.Service.ApplicationInfoService;
import com.qimeng.main.entity.ApplicationInfo;
import com.qimeng.main.oldStuService.OldService;
import com.qimeng.main.vo.OldResponseJson;
import com.qimeng.main.vo.StudentInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EurekaClientStuInfoApplicationTests {

	@Autowired
	OldService oldService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	ApplicationInfoService applicationInfoService;
	@Test
	public void contextLoads() throws UnsupportedEncodingException {
		
		//OldResponseJson json=oldService.getStudent("5B2DDE67C27283D61FED7C6CEE15FE35783B7EDD");
		//System.out.println(json.toString());
		ApplicationInfo appInfo=applicationInfoService.getApplicationInfo("7166912116544627");
		String tempString=applicationInfoService.encodeMessage(appInfo, "123456");
		System.out.println(tempString);
		System.out.println(applicationInfoService.decodeMessage(appInfo, tempString));
		
		//stringRedisTemplate.opsForValue().set("123","123");
		//stringRedisTemplate.opsForValue().get(key)
	}

}
