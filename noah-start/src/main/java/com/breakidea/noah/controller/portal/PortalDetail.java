package com.breakidea.noah.controller.portal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.breakidea.noah.common.service.UserService;
import com.breakidea.noah.support.AbstractWebController;
import com.breakidea.noah.web.session.Authz;

@Controller("/portal/detail")
public class PortalDetail extends AbstractWebController {

	@Autowired
	UserService userService;

	@Autowired
	private Authz authz;

	@Override
	public void handleRequestInternal(ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		

	}
}
