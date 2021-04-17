package com.breakidea.noah.controller;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexAction {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	VelocityEngine velocityEngine;
	
	@RequestMapping("/welcome")
	public void welcome() {

	}
}
