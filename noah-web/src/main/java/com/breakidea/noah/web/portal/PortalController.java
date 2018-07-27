package com.breakidea.noah.web.portal;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.breakidea.noah.shared.param.UserParam;
import com.breakidea.noah.shared.service.UserService;
import com.breakidea.noah.starter.support.AbstractEnhancedController;
import com.breakidea.noah.starter.support.RequestUtils;

@Controller("/portal/index")
public class PortalController extends AbstractEnhancedController {

	@Resource
	private UserService userService;

	@Resource
	private WebRequest webRequest;

	@Override
	protected void handleRequestInternal(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		UserParam param = RequestUtils.bindRequest(request, UserParam.class);

		if ("submit".equals(RequestUtils.getParameter(request, "action"))) {
			userService.add(param);

			mv.addObject("status", 0);
			mv.addObject("statusInfo", "OK");

			mv.setViewName("/welcome");
		} else {
			mv.addObject("user", userService.query(param));
			mv.addObject("sessionId", webRequest.getSessionId());
		}
	}

}
