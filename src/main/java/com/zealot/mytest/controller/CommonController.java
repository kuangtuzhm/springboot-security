package com.zealot.mytest.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zealot.mytest.constant.ResponseStatus;
import com.zealot.mytest.po.User;
import com.zealot.mytest.result.ResultResponse;
import com.zealot.mytest.service.UserService;

@Controller
public class CommonController {
	
	@Resource(name="userService")
	private UserService userService;

//	@RequestMapping(value="/login")
//	@ResponseBody
//	public ResultResponse login(Model model, String username, String pwd)
//	{
//		ResultResponse response = new ResultResponse();
//		
//		User u = userService.findUserByLoginName(username);
//		if(null != u && u.getPwd().endsWith(pwd))
//		{
//			response.setStatus(ResponseStatus.SUCCESS);
//		}
//		else
//		{
//			response.setStatus(ResponseStatus.ERROR);
//			response.setErrMsg("用户名密码错误");
//		}
//		return response;
//	}
	
//	@RequestMapping(value="/login")
//	public String login(Model model, String username, String pwd)
//	{
//		return "login";
//	}
	
	@RequestMapping(value="/mainFrame")
	public String mainFrame()
	{
		return "/index";
	}
}
