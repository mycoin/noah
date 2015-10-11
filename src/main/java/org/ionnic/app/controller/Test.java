package org.ionnic.app.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.ionnic.config.ActionSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class Test extends ActionSupport {

	@RequestMapping("/basic")
	public void basic(@RequestBody String body, Model model, HttpServletRequest req) {
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("method", req.getMethod());
		data.put("controller", this.getClass().getName());
		data.put("body", body);

		model.addAttribute("data", data);
	}

	@RequestMapping(value = "/index")
	public Object index(HttpServletRequest req) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("ip", "11");
		data.put("search", new Date());

		return data;
	}

	@RequestMapping(value = "/search/{search}", produces = "application/json", method = { RequestMethod.POST })
	@ResponseBody
	public Object search(@PathVariable() String search, @RequestParam(required = false) String app) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("biz", app);
		data.put("search", search);
		data.put("status", 0);
		return data;
	}
}
