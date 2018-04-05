package com.alibaba.rigel.web.module;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.rigel.shared.exception.ServiceException;

@Controller
@RequestMapping("/lotus.server")
public class IndexController {

	@Resource
	protected HttpServletRequest request;

	@Resource
	protected HttpServletResponse response;

	@RequestMapping
	public String index() throws ServiceException {
		return "/index";
	}
}
