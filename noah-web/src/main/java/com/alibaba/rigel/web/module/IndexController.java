package com.alibaba.rigel.web.module;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.rigel.shared.exception.ServiceException;
import com.alibaba.rigel.web.base.AbstractController;

@Controller
@RestController
@RequestMapping("/")
public class IndexController extends AbstractController {

	@RequestMapping("/index")
	public void index() throws ServiceException {

	}
}
