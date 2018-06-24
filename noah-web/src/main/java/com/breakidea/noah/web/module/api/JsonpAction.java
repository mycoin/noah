package com.breakidea.noah.web.module.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.breakidea.noah.shared.model.UserModel;

@Controller
@RequestMapping("/api/jsonp")
public class JsonpAction {

	@RequestMapping
	public @ResponseBody UserModel get() {
		return new UserModel();
	}
}
