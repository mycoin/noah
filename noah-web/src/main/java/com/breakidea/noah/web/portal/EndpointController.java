package com.breakidea.noah.web.portal;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.breakidea.noah.starter.support.AbstractExtendedRequest;

@Controller("/portal/endpoint")
public class EndpointController extends AbstractExtendedRequest {

	public Boolean defaultMain(ModelMap modelMap) {
		session.setAttribute("RequestId", new Date().getTime());
		session.setAttribute("RequestName", request.getRequestURI());

		modelMap.addAttribute("properties", System.getProperties());
		return true;
	}
}
