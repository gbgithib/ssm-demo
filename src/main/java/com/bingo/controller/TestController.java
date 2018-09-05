package com.bingo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bingo.service.TestService;
import com.bingo.utils.PropertiesConfigUtil;

@Controller
public class TestController {
	@Autowired
	private TestService testService;
	@RequestMapping("/getData")
	@ResponseBody
	public String getData() {
		Map<String, String> map=testService.getList();
		return JSON.toJSONString(map);
	}
	@RequestMapping("/getProperties")
	@ResponseBody
	public String getProperties() {
		//return PropertiesConfigUtil.getValue(PropertiesConfigUtil.FILE_CONFIG, "air.ftp.username");
		
		return JSON.toJSONString(PropertiesConfigUtil.getProperties(PropertiesConfigUtil.FILE_CONFIG));
	}
}
