package com.breakidea.noah.framework.config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.breakidea.noah.framework.support.AbstractWebController;

@Controller("/welcome")
public class WelcomeController extends AbstractWebController {

	@Override
	protected void handleRequestInternal(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		mv.addObject("status", 0);
		mv.addObject("statusInfo", "OK");
	}
}
