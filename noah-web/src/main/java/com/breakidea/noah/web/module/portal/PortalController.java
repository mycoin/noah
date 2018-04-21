package com.breakidea.noah.web.module.portal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.breakidea.noah.shared.param.UserParam;
import com.breakidea.noah.shared.service.UserService;

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
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		UserParam param = new UserParam();

		ServletRequestDataBinder binder = new ServletRequestDataBinder(param);
		binder.bind(request);

		if ("submit".equals(request.getParameter("action"))) {
			userService.add(param);
			mv.setViewName("/index");
		} else {
			mv.addObject("user", userService.query(param));
			mv.addObject("request", request);
			mv.addObject("session", session);
			mv.addObject("response", response);
		}
		return mv;
	}

}
