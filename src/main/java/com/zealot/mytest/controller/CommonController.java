package com.zealot.mytest.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zealot.mytest.constant.ResponseStatus;
import com.zealot.mytest.result.ResultResponse;
import com.zealot.mytest.security.entity.SysUserEntity;
import com.zealot.mytest.service.UserService;

@Controller
public class CommonController extends BaseController{
	
	private final static Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@Autowired
    SessionRegistry sessionRegistry;

	
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request, HttpServletResponse response, 
			Model model, String error, String logout, String expired) throws IOException
	{
		
		boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		logger.info("isAjax="+isAjax);
		if(isAjax)
		{
			ResultResponse result = new ResultResponse();
			result.setStatus(ResponseStatus.UNLOGIN);
			String json = JSON.toJSONString(result); 
			writeJson(response, json);
			return null;
		}
		
		model.addAttribute("error",error);
		model.addAttribute("logout",logout);
		model.addAttribute("expired", expired);
		return "login";
	}
	
	@RequestMapping(value="/mainFrame")
	public String mainFrame()
	{
		logger.debug("debug登录成功进入mainFrame");
		logger.info("info登录成功进入mainFrame");
		
		return "/index";
	}
	
	@RequestMapping(value="/kickUser")
	@ResponseBody
	public ResultResponse kickUser()
	{
		ResultResponse response = new ResultResponse();
		
		List<Object> s = sessionRegistry.getAllPrincipals();
		for(Object a : s)
		{
			SysUserEntity e = (SysUserEntity)a;
			if(e.getId().intValue() == 1)
			{
				List<SessionInformation> sessionInformations = sessionRegistry  
		                .getAllSessions(a, false);  
		        for (SessionInformation sessionInformation : sessionInformations) 
		        {  
		            sessionInformation.expireNow();  
		           // sessionRegistry.removeSessionInformation(sessionInformation.getSessionId());
		        }
			}
	    }
		return response;
	}
}
