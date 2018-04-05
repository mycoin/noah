package com.alibaba.rigel.web.module;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.rigel.shared.exception.ServiceException;

@Controller
@RequestMapping("/status.service")
public class IndexController extends AbstractController {

	@RequestMapping
	public String index() throws ServiceException {
		return "/index";
	}
}
