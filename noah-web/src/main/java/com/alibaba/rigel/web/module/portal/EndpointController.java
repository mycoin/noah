package com.alibaba.rigel.web.module.portal;

import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

@Controller("/portal/endpoint")
public class EndpointController extends AbstractController {

	@Resource
	protected HttpServletRequest request;

	@Resource
	protected HttpServletResponse response;

	@Resource
	protected HttpSession session;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		Properties properties = System.getProperties();

		mv.addObject("properties", properties);
		mv.addObject("request", request);
		mv.addObject("response", request);
		mv.addObject("session", session);

		return mv;
	}

}
