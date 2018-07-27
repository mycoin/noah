package com.breakidea.noah.web.portal;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.breakidea.noah.starter.support.AbstractEnhancedController;

@Controller("/portal/endpoint")
public class EndpointController extends AbstractEnhancedController {

	@Override
	protected void handleRequestInternal(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		HttpSession session = request.getSession(true);

		session.setAttribute("RequestId", new Date().getTime());
		session.setAttribute("RequestName", request.getRequestURI());

		mv.addObject("properties", System.getProperties());
	}
}
