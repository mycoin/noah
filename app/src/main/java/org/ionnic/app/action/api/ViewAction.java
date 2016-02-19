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
public class ViewAction extends ActionSupport {

	@RequestMapping("/1")
	public String A(HttpServletRequest request) throws Exception {
		request.setAttribute("p", 1);
		return "index";
	}

	@RequestMapping("/2")
	public ModelAndView B() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p", 1);
		return new ModelAndView("index", map);
	}

	@RequestMapping("/3")
	public String C(Map<String, Object> map) throws Exception {
		map.put("p", 1);
		return "index";
	}

	@RequestMapping("/4")
	public String D(Model model) throws Exception {
		model.addAttribute("p", 1);
		return "index";
	}

}
