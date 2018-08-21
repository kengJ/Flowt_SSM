package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.User;
import com.service.IUserService;

@Controller
@RequestMapping("/Test")
public class TestController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping("/Test")
	@ResponseBody
	public User Test(){
		User user = userService.getUserById(1);
		return user;
	}
}
