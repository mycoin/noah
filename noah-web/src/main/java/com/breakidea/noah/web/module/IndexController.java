package com.breakidea.noah.web.module;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.breakidea.noah.framework.util.JsonUtils;

@Controller
public class IndexController {

	@RequestMapping("/welcome")
	public ModelAndView welcome() {
		ModelAndView mv = new ModelAndView("/welcome");

		mv.addObject("status", 0);
		mv.addObject("statusInfo", "OK");

		return mv;
	}
	
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("/index");
		
		String json = "{\"list\":[{\"name\":\" guokai\",\"show\":true},{\"name\":\" benben\",\"show\":false},{\"name\":\" dierbaby\",\"show\":true}],\"blah\":[{\"num\":1},{\"num\":2},{\"num\":3,\"inner\":[{\"time\":\"15:00\"},{\"time\":\"16:00\"},{\"time\":\"17:00\"},{\"time\":\"18:00\"}]},{\"num\":4}]}";
		Map<String, Object> dataMap = JsonUtils.parse(json);
		
		mv.addObject("status", 0);
		mv.addObject("statusInfo", "OK");
		mv.addAllObjects(dataMap);
		return mv;
	}
}
