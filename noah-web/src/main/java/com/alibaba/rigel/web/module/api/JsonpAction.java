package com.alibaba.rigel.web.module.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.rigel.shared.model.UserModel;

@CrossOrigin
@Controller
@RequestMapping("/api/jsonp")
public class JsonpAction {

	@RequestMapping
	public @ResponseBody UserModel get() {
		return new UserModel();
	}
}
