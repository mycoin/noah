package com.breakidea.noah.web.module.portal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.breakidea.noah.shared.param.UserParam;
import com.breakidea.noah.shared.service.UserService;
import com.breakidea.noah.web.support.util.RequestUtils;

@Controller("/portal/index")
public class PortalController extends AbstractController {

	@Resource
	private HttpServletRequest request;

	@Resource
	private HttpServletResponse response;

	@Resource
	private HttpSession session;

	@Resource
	private UserService userService;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		UserParam param = RequestUtils.bindRequest(request, UserParam.class);

		if ("submit".equals(RequestUtils.getParameter(request, "action"))) {
			userService.add(param);
			mv.addObject("status", 0);
			mv.addObject("statusInfo", "OK");

			mv.setViewName("/welcome");
		} else {
			mv.addObject("user", userService.query(param));
			mv.addObject("request", request);
			mv.addObject("session", session);
			mv.addObject("response", response);
		}
		return mv;
	}

}
