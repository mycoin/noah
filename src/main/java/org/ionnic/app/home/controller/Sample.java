package org.ionnic.app.home.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample")
public class Sample {
	Logger logger = LoggerFactory.getLogger(Sample.class);

	@RequestMapping("/index")
	public void index(Model model, HttpServletRequest req) {
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("method", req.getMethod());
		data.put("filter", req.getAttribute("filter"));
		data.put("intercepter", req.getAttribute("intercepter"));
		data.put("controller", this.getClass().getName());

		model.addAttribute("data", data);
	}

	@RequestMapping("/param")
	public void param(String name, Model model) {
		model.addAttribute("data", name);
	}

	@RequestMapping("/rest")
	public void rest(Model model) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", 83961);
		data.put("website", "http://home.ionnic.org/");
		data.put("age", 25);

		model.addAttribute("status", 0);
		model.addAttribute("statusInfo", "OK");
		model.addAttribute("data", data);
	}

	@RequestMapping("/security")
	public void security(Model model) {
		model.addAttribute("status", 0);
		model.addAttribute("statusInfo", "OK");
	}

	@RequestMapping("/velocity")
	public void velocity(Model model) throws Exception {
		model.addAttribute("data", 1);

		String html = "";
		File file = ResourceUtils.getFile("classpath:conf/README.txt");
		InputStreamReader read = new InputStreamReader(new FileInputStream(file));// 考虑到编码格式
		BufferedReader bufferedReader = new BufferedReader(read);
		String lineTxt = null;
		while ((lineTxt = bufferedReader.readLine()) != null) {
			html += lineTxt + "\n";
		}
		
		model.addAttribute("html", html);
	}

}
