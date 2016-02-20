package org.ionnic.app.action.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ionnic.common.ActionSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author apple
 *
 */
@Controller
@RequestMapping("/api/view")
public class View extends ActionSupport {

	@RequestMapping("/1")
	public String A(HttpServletRequest request) throws Exception {
		request.setAttribute("p", "OK");
		return "api/view";
	}

	@RequestMapping("/2")
	public ModelAndView B() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p", "OK");
		return new ModelAndView("api/view", map);
	}

	@RequestMapping("/3")
	public String C(Map<String, Object> map) throws Exception {
		map.put("p", "OK");
		return "api/view";
	}

	@RequestMapping("/4")
	public String D(Model model) throws Exception {
		model.addAttribute("p", "OK");
		return "api/view";
	}

	@RequestMapping("/5")
	public ModelAndView E() throws Exception {
		ModelAndView mv = new ModelAndView();

		mv.addObject("p", "OK");
		mv.setViewName("api/view");
		return mv;
	}

}
