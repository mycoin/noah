package org.ionnic.app.home.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Home {

	@RequestMapping("/home/about")
	public ModelAndView about() {
		ModelAndView view = new ModelAndView("home/a");
		view.addObject("about", "about");
		return view;
	}

	@RequestMapping("/home")
	public ModelAndView index(HttpServletRequest req) {

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", 0);
		data.put("name", "ronghan.lrh");
		data.put("website", "http://home.ionnic.org/");
		data.put("age", 25);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 0);
		map.put("statusInfo", "OK");
		map.put("data", data);

		// System.out.println(req.getAttribute("Param"));
		System.out.println("index()");
		return new ModelAndView("home/index", map);
	}

	@RequestMapping("/home/install")
	public ModelAndView install() {
		return new ModelAndView("/home/install");
	}
}