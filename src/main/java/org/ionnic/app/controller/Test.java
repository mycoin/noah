package org.ionnic.app.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ionnic.core.utils.RequestUtils;
import org.ionnic.core.web.action.ActionSupport;
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
	public void basic(@RequestBody String reqBody, Model model, HttpServletRequest req) {
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("method", req.getMethod());
		data.put("filter", req.getAttribute("filter"));
		data.put("intercepter", req.getAttribute("intercepter"));
		data.put("controller", this.getClass().getName());
		data.put("body", reqBody);

		model.addAttribute("data", data);
	}

	@RequestMapping(value = "/exception", produces = "application/json")
	public void exception() throws Exception {
		throw new Exception("STATUS_OK");
	}

	@RequestMapping("/primitive")
	@ResponseBody
	public Object primitive(@RequestParam(required = false) String app, Model data) {
		data.addAttribute("status", 0);
		data.addAttribute("data", app);

		return data;
	}

	@RequestMapping(value = "/rest")
	public void rest(Model model, @RequestParam() String app) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", new Date());
		data.put("app", app);

		model.addAttribute("status", 0);
		model.addAttribute("data", data);
	}

	@RequestMapping(value = "/search/{search}", produces = "application/json", method = { RequestMethod.PUT, RequestMethod.POST })
	@ResponseBody
	public Object search(@PathVariable() String search, @RequestParam(required = false) String app) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("biz", app);
		data.put("search", search);

		return result(0, data);
	}

	@RequestMapping(value = "/security/{username}")
	@ResponseBody
	public Object security(@RequestParam(required = false) String app) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("biz", app);
		return result(0, data);
	}

	@RequestMapping(value = "/header")
	@ResponseBody
	public Object header(HttpServletRequest req) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("ip", RequestUtils.getRemoteAddr(req));
		data.put("search", null);

		return result(0, data);
	}
}
