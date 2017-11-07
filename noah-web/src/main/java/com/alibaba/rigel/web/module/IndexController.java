package com.alibaba.rigel.web.module;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.rigel.framework.FrameworkConstants;
import com.alibaba.rigel.shared.exception.ServiceException;
import com.alibaba.rigel.shared.param.UserParam;
import com.alibaba.rigel.shared.service.UserService;
import com.google.gson.JsonObject;

@Controller
@RestController
@RequestMapping("/")
public class IndexController extends AbstractController {

	@Resource
	protected UserService userService;

	@RequestMapping("/index")
	public ModelAndView index(UserParam param) throws ServiceException {
		ModelAndView mv = new ModelAndView();

		session.setAttribute("name", param);

		mv.addObject("user", userService.query(param));
		mv.addObject("request_id", request.getAttribute(FrameworkConstants.REQUEST_ID));
		mv.addObject("session", session);
		mv.addObject("response", response);

		return mv;
	}

	@RequestMapping("/list")
	public @ResponseBody Map list(UserParam param) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result.put("dataList", userService.query(param));
		} catch (ServiceException e) {
			result.put("message", e.getMessage());
		}
		return result;
	}

	@RequestMapping("/add")
	public @ResponseBody JsonObject add(UserParam param) {
		JsonObject mv = new JsonObject();
		try {
			userService.add(param);
		} catch (ServiceException e) {
			mv.addProperty("message", e.getMessage());
		}
		return mv;
	}
}
