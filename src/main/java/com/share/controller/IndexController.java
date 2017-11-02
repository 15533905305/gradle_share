package com.share.controller;

import com.share.dao.UserMapper;
import com.share.entity.User;
import com.share.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by antLuck on 2017/11/2.
 */
@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;
    @RequestMapping("/")
    public String getIndex(){
        return "index";
    }

}
