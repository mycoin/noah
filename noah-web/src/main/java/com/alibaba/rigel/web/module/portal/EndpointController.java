package com.alibaba.rigel.web.module.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.rigel.web.module.AbstractController;

@Controller
public class EndpointController extends AbstractController {

	@RequestMapping("/portal/endpoint")
	public ModelAndView endpoint() throws Exception {
		ModelAndView mv = new ModelAndView();

		mv.addObject("properties", System.getProperties());
		mv.addObject("request", request);
		mv.addObject("response", request);
		mv.addObject("session", session);

		return mv;
	}
	
	@RequestMapping("/portal/servlet")
	public ModelAndView servlet() throws Exception {
		ModelAndView mv = new ModelAndView();

		mv.addObject("properties", System.getProperties());
		mv.addObject("request", request);
		mv.addObject("response", request);
		mv.addObject("session", session);

		return mv;
	}
}
