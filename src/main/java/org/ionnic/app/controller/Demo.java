package org.ionnic.app.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.ionnic.core.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class Demo {

	Logger logger = LoggerFactory.getLogger(Demo.class);

	@RequestMapping("/index")
	public void velocity(Model model) throws Exception {
		model.addAttribute("data", 1);

		String html = "";
		File file = ResourceUtils.getFile("classpath:conf/README.txt");
		InputStreamReader read = new InputStreamReader(new FileInputStream(file), Config.CHARSET);
		BufferedReader bufferedReader = new BufferedReader(read);
		String lineTxt = null;
		boolean firstRow = true;
		while ((lineTxt = bufferedReader.readLine()) != null) {
			if (firstRow) {
				html = lineTxt;
			} else {
				html += lineTxt + "\n";
			}
			firstRow = false;
		}
		model.addAttribute("html", html);
	}
}
