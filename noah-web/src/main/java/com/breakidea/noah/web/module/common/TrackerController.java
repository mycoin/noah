package com.breakidea.noah.web.module.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.breakidea.noah.web.module.AbstractController;

@Controller
public class TrackerController extends AbstractController {

	@RequestMapping("/tracker.js")
	public ModelAndView index(@RequestParam Long sid) {
		ModelAndView mv = new ModelAndView("/internal/tracker");

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript; charset=UTF-8");

		mv.addObject("siteId", sid);
		mv.addObject("serverName", request.getServerName() + ":" + request.getServerPort());

		return mv;
	}

	@RequestMapping("/e.gif")
	public @ResponseBody String saveLog() {
		return "";

	}
}
