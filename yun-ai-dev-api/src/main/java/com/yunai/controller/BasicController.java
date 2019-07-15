package com.yunai.controller;

import com.yunai.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class BasicController {
	@Autowired
	public RedisOperator redis;

	public static final String USER_REDIS_SEESION = "user_redis_session";

	@Autowired
	// 每页分页的记录数
	public static final Integer PAGE_SIZE = 5;
	
}
