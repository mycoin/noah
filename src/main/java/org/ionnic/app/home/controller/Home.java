package org.ionnic.app.home.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class has the logic for acting on web requests.
 * 
 * @author ajesler
 * 
 */
// @Controller
// public class Home extends Action {
// /**
// * Responds with a welcome message for users. Can be accessed with GET or
// * POST. If a name parameter is set, the name value will be included in the
// * welcome.
// *
// * @param name
// * an optional name for
// * @return
// */
// @RequestMapping(value = "/home", method = { RequestMethod.GET,
// RequestMethod.POST })
// public ModelAndView welcome(@RequestParam(required = false, value = "name")
// String name) {
// String welcome = "";
// if (name != null && name.length() > 0) {
// welcome = "<h1>Welcome, " + name + "!</h1>";
// } else {
// welcome = "<h1>Welcome.</h1>";
// }
//
// Map<String, Object> map = new HashMap<String, Object>();
// map.put("status", 0);
// map.put("statusInfo", "OK");
// map.put("data", welcome);
//
// ModelAndView model = new ModelAndView("index");
// model.addAllObjects(map);
// return model;
// }
//
// @RequestMapping(value = "/home/1")
// public ModelAndView index(Model model) {
//
//
// // return commit();
//
// model.addAllAttributes(map);
// return new ModelAndView("home/1");
// }
// }

@Controller
public class Home {

	@RequestMapping("/home")
	public ModelAndView index(HttpServletRequest req) {

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", 0);
		data.put("name", "ronghan.lrh");
		data.put("website", "http://home.ionnic.org/");
		data.put("age", 25);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 0);
		map.put("statusInfo", "OK");
		map.put("data", data);

		// System.out.println(req.getAttribute("Param"));
		System.out.println("index()");
		return new ModelAndView("home/index", map);
	}

	@RequestMapping("/home/about")
	public ModelAndView about() {
		ModelAndView view = new ModelAndView("home/about");
		view.addObject("about", "about");
		return view;
	}

	@RequestMapping("/home/install")
	public ModelAndView install() {
		ModelAndView view = new ModelAndView("home/install");
		return view;
	}
}