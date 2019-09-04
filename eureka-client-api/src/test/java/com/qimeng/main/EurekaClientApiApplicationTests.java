package com.qimeng.main;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qimeng.main.util.EncryptUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EurekaClientApiApplicationTests {

	@Test
	public void contextLoads() {
		
		EncryptUtil encryptUtil;
		try {
			encryptUtil = new EncryptUtil("384FJcvcjaSDAACS","13ASAhda");
			String accountTonken=encryptUtil.decode("CUlQiVnY4CrhHuqp0dq9gg==");
			System.out.println(accountTonken);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
