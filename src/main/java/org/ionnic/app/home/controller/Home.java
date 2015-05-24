package org.ionnic.app.home.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

//http://my.oschina.net/bosscheng/blog/126941
@Controller
@RequestMapping("/home")
public class Home {

	Logger log = LoggerFactory.getLogger(Home.class);

	@RequestMapping("/about")
	public void about(Model model) {
		model.addAttribute("about", this);
	}

	@RequestMapping("/join")
	public void join(Result[] params) {
		System.out.println(params);
	}

	@RequestMapping(value = { "", "/index" })
	public ModelAndView index(HttpServletRequest req, WebRequest request) {

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", 0);
		data.put("name", "ronghan.lrh");
		data.put("website", "http://home.ionnic.org/");
		data.put("age", 25);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 0);
		map.put("statusInfo", "OK");
		map.put("data", data);

		return new ModelAndView("home/index", map);
	}

	@RequestMapping("/install")
	public ModelAndView install() {
		return new ModelAndView();
	}

	class Result {
		String id;
		String name;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
