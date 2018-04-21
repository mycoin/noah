package com.breakidea.noah.web.module.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.breakidea.noah.web.module.AbstractController;

@Controller
public class EndpointController extends AbstractController {

	@RequestMapping("/portal/endpoint")
	public ModelAndView endpointPortal() throws Exception {
		ModelAndView mv = new ModelAndView();

		mv.addObject("properties", System.getProperties());
		mv.addObject("request", request);
		mv.addObject("response", request);
		mv.addObject("session", session);

		return mv;
	}
}
