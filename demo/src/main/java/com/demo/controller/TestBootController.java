package com.demo.controller;

import java.util.List;

import com.demo.dao.UserMapper;
import com.demo.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/testboot")
public class TestBootController {
	@Autowired
	private UserMapper userMapper;
    @RequestMapping("getUser")
    public List<User> getUser() {
        return userMapper.selectUser();
    }
    @RequestMapping("hellor")
    public String hellor() {
    	return "hellor";
    }
}