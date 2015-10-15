package net.io.app.action;

import javax.servlet.http.HttpServletRequest;

import net.io.config.ActionSupport;
import net.io.config.support.InputModel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author apple
 *
 */
@Controller
@RequestMapping("/api")
public class Api extends ActionSupport {

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView index(@RequestBody InputModel model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("json");
		mv.addObject("data", model);
		mv.addObject("keyword", model.getParams().get("keyword"));
		return mv;
	}
}
