package org.ionnic.app.action;

import org.ionnic.config.ActionSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author apple
 *
 */
@Controller
@RequestMapping("/click")
public class Click extends ActionSupport {

	@RequestMapping("log.js")
	public void index(@RequestParam String version, @RequestParam int sid, Model model) {
		model.addAttribute("siteId", sid);
		model.addAttribute("version", version);
	}
}

