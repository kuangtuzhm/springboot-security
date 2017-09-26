package com.zealot.mytest.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zealot.mytest.service.UserService;

@Controller
public class CommonController {
	
	private final static Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@Autowired
    SessionRegistry sessionRegistry;

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
		logger.debug("debug登录成功进入mainFrame");
		logger.info("info登录成功进入mainFrame");
		List<Object> s = sessionRegistry.getAllPrincipals();
		logger.info("s="+s);
		return "/index";
	}
}
