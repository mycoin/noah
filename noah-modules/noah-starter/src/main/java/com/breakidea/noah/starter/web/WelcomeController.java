package com.breakidea.noah.starter.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.breakidea.noah.starter.support.AbstractExtendedController;

@Controller("/welcome")
public class WelcomeController extends AbstractExtendedController {

	@Resource
	private WebRequest webRequest;

	@Override
	public void handleRequestInternal(ModelAndView mv) {
		mv.addObject("status", 0);
		mv.addObject("statusInfo", "OK");
	}

}
