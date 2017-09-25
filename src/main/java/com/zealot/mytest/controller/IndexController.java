package com.zealot.mytest.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/hello")
	public String hello(Map<String,Object> map)
	{
		 map.put("name", "[Angel -- 守护天使]");  
	     return "index";  
	}
	
	@RequestMapping(value={"/index","/"})
	public String index(Map<String,Object> map)
	{
		 map.put("name", "[Angel -- 守护天使]");  
	     return "index";  
	}
}
