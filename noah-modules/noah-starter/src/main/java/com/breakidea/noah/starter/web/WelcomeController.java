package com.breakidea.noah.starter.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.breakidea.noah.starter.support.AbstractEnhancedController;

@Controller("/welcome")
public class WelcomeController extends AbstractEnhancedController {

	@Override
	protected void handleRequestInternal(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		mv.addObject("status", 0);
		mv.addObject("statusInfo", "OK");
	}

}
