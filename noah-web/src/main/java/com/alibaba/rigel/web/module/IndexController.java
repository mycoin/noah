package com.alibaba.rigel.web.module;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.rigel.shared.exception.ServiceException;
import com.alibaba.rigel.shared.param.UserParam;

@Controller
@RequestMapping("/")
public class IndexController extends AbstractController {

	@RequestMapping("/index")
	public ModelAndView index(UserParam param) throws ServiceException {
		return new ModelAndView();
	}
}
