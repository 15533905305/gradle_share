package com.share.controller;

import com.share.dao.UserMapper;
import com.share.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by antLuck on 2017/10/20.
 */
//@CrossOrigin
@Controller
@RequestMapping("user")
public class TestController {

  @Autowired
  UserMapper userMapper;

    @ResponseBody
    @RequestMapping("addUser")
    public String addUser(User user){
        System.out.println("进入方法");
        userMapper.insert(user);
        return "success";
    }
}
