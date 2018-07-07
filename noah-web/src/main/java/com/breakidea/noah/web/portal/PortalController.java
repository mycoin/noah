package com.breakidea.noah.web.portal;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.breakidea.noah.shared.param.UserParam;
import com.breakidea.noah.shared.service.UserService;
import com.breakidea.noah.starter.support.AbstractExtendedController;
import com.breakidea.noah.starter.support.RequestUtils;

@Controller("/portal/index")
public class PortalController extends AbstractExtendedController {

	@Resource
	private UserService userService;

	@Resource
	private WebRequest webRequest;

	@Override
	public void handleRequestInternal(ModelAndView mv) {
		UserParam param = RequestUtils.bindRequest(request, UserParam.class);

		if ("submit".equals(RequestUtils.getParameter(request, "action"))) {
			userService.add(param);

			mv.addObject("status", 0);
			mv.addObject("statusInfo", "OK");

			mv.setViewName("/welcome");
		} else {
			mv.addObject("user", userService.query(param));
			mv.addObject("sessionId", session.getId());
		}
	}

}
