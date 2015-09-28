package org.ionnic.app.controller;

import org.ionnic.core.action.ActionSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserAction extends ActionSupport {

	private static final long serialVersionUID = 3076411315921873338L;

	@RequestMapping
	public String execute(ModelAndView mv) throws Exception {
		throw new Exception("2");
	}
}
