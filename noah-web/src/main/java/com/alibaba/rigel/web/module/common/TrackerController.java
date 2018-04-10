package com.alibaba.rigel.web.module.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.rigel.web.module.AbstractController;

@Controller
public class TrackerController extends AbstractController {

	@RequestMapping("/tracker.js")
	public ModelAndView index(@RequestParam Long sid) throws Exception {
		ModelAndView mv = new ModelAndView("internal/tracker");

		mv.addObject("siteId", sid);
		mv.addObject("serverName", request.getServerName());

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript; charset=utf-8");

		return mv;
	}

	@RequestMapping("/e.gif")
	public void saveLog() throws Exception {
		response.getOutputStream().close();
	}
}
