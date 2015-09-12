package org.ionnic.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.ionnic.core.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/i")
public class I extends BaseController {


	@RequestMapping(value = "search/{search}", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object search(@PathVariable() String search, @RequestParam(required = false) String app) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("biz", app);
		data.put("search", search);

		return result(0, data);
	}

	@RequestMapping("primitive")
	@ResponseBody
	public Object primitive(@RequestParam Integer value) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("status", 0);
		data.put("data", value);
		return data;
	}
}
