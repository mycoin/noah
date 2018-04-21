package com.breakidea.noah.web.module;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping("/http.status")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("/index");

		mv.addObject("status", 0);
		mv.addObject("statusInfo", "OK");

		return mv;
	}
}
