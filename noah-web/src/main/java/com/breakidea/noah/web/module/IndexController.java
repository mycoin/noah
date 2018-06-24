package com.breakidea.noah.web.module;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.breakidea.noah.shared.exception.ServiceException;
import com.breakidea.noah.shared.param.UserParam;
import com.breakidea.noah.shared.service.UserService;
import com.breakidea.noah.web.support.RetrofitService;
import com.google.gson.JsonObject;

@Controller
@RequestMapping("/")
public class IndexController extends AbstractController {

	@Resource
	protected UserService userService;

	@Resource
	protected RetrofitService retrofitService;

	@RequestMapping("/index")
	public ModelAndView index(UserParam param) throws ServiceException {
		ModelAndView mv = new ModelAndView();

		mv.addObject("user", userService.query(param));
		mv.addObject("request", request);
		mv.addObject("request_id", retrofitService.getRequestId());
		mv.addObject("session", session);
		mv.addObject("response", response);

		return mv;
	}

	@RequestMapping("/list")
	public @ResponseBody Map<String, Object> list(UserParam param) {
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
