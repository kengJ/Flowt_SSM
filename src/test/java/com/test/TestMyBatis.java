package com.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.model.User;
import com.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了这个类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestMyBatis {

	private static Logger logger = Logger.getLogger(TestMyBatis.class);
	
	@Autowired
	private IUserService userService = null;
	
	@Test
	public void test() {
		User user = userService.getUserById(1);
		logger.info(user);
		logger.info("user转为json");
		logger.info(JSON.toJSONString(user));
	}
}
