package com.share;

import com.share.dao.UserMapper;
import com.share.entity.User;
import com.share.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
@RunWith(SpringRunner.class)
@SpringBootTest
public class GradleShareApplicationTests {

	@Autowired
	UserMapper userMapper;
	@Autowired
	RedisTemplate<String,String> redisTemplate;
	@Autowired
	UserService userService;
/* 	@Test
	public void contextLoads() {
		List<Login> list = userMapper.selectAll();
		list.forEach(n -> System.out.println(n.getUsername()));
	}*/

	@Test
	public void testRedis(){
		redisTemplate.opsForValue().set("wjl","testwjl",60*30,TimeUnit.SECONDS);
	}
	@Test
	public void testGetRedis(){
		String info = redisTemplate.opsForValue().get("wjdl");
		System.out.println(info);
	}
	@Test
	public void testRedisTimeOut(){
		boolean info = redisTemplate.expire("wjl" ,1L, TimeUnit.MILLISECONDS);
		System.out.println(info);
	}
	@Test
	public void testRedisMap(){
		Map<String,String> map = new HashMap<>();
		map.put("aaa","add");
		map.put("bbb","dddd");
		redisTemplate.opsForHash().putAll("userMap",map);
	}
	@Test
	public void testRedisMapTimeOut(){
		redisTemplate.opsForHash().delete("userMap","aaa");
		redisTemplate.opsForHash().put("userMap","bbb","ccc");
	}
	@Test
	public void test(){
		List<User> users = userMapper.selectAll();
		users.forEach(n-> System.out.println(n.getUsername()));
	}
}
