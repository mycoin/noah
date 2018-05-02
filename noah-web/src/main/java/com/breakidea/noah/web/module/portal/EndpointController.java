package com.breakidea.noah.web.module.portal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.breakidea.noah.web.support.AbstractExtendedRequest;

@Controller(EndpointController.ENDPOINT_NAME)
public class EndpointController extends AbstractExtendedRequest {

	public static final String ENDPOINT_NAME = "/portal/endpoint";

	public Boolean defaultMain(ModelMap modelMap) {

		modelMap.addAttribute("properties", System.getProperties());
		modelMap.addAttribute("request", request);
		modelMap.addAttribute("response", request);
		modelMap.addAttribute("session", session);

		return true;
	}
}
