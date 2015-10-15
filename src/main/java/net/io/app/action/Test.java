package net.io.app.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.io.config.ActionSupport;
import net.io.config.Context.ResponseData;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class Test extends ActionSupport {

	@RequestMapping("/basic")
	public void basic(@RequestBody ResponseData body, Model model, HttpServletRequest request) {
		ModelMap data = new ModelMap();

		data.addAttribute("method", request.getMethod());
		data.addAttribute("controller", this.getClass().getName());

		model.addAttribute("body", body);
		model.addAttribute("data", data);
	}

	@RequestMapping(value = "/error")
	public Map<String, Object> error() throws Exception {
		System.out.println("[DEBUG] net.io.app.action.Test.error()");
		throw new Exception("<!-- status-ok --> PageException");
	}

	@RequestMapping(value = "/exception", produces = "application/json")
	@ResponseBody
	public Map<String, Object> exception() throws Exception {
		throw new Exception("<!-- status-ok --> UserException");
	}

	@RequestMapping(value = "/index")
	public Object index(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("ip", "11");
		data.put("search", new Date());
		return data;
	}

	@RequestMapping(value = "/search/{search}", method = RequestMethod.POST)
	@ResponseBody
	public Object search(@RequestBody Map<String, Object> body, Model model, @PathVariable() String search) throws Exception {
		ModelMap data = new ModelMap();

		data.addAttribute("biz", body);
		data.addAttribute("search", search);
		data.addAttribute("status", 0);

		return data;
	}
}
