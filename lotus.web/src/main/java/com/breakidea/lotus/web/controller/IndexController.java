package com.breakidea.lotus.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.breakidea.lotus.shared.param.user.UserParam;
import com.breakidea.lotus.shared.service.user.UserService;
import com.breakidea.lotus.shared.support.ServiceException;
import com.breakidea.lotus.web.support.beans.RequestContextFilter;

@Controller
@RequestMapping("/")
public class IndexController extends AbstractController {

	@Resource
	protected UserService userService;

	@RequestMapping("/index")
	public ModelAndView index(UserParam param) throws ServiceException {
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", userService.query(param));
		mv.addObject("request_id", request.getAttribute(RequestContextFilter.REQUEST_ID));
		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView add(UserParam param) {
		ModelAndView mv = new ModelAndView();
		try {
			userService.add(param);
		} catch (ServiceException e) {
			mv.addObject("error", e);
		}
		
		return mv;
	}
}
