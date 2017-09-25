package com.zealot.mytest.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zealot.mytest.po.User;
import com.zealot.mytest.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource(name="userService")
	private UserService userService;

	@RequestMapping("/list")
	public String list()
	{
		return "user/userList";
	}
	
	@RequestMapping("/findByName")
	@ResponseBody
	public User findByName(String name)
	{
		User u = userService.findUserByName(name);
		
		return u;
	}
}
