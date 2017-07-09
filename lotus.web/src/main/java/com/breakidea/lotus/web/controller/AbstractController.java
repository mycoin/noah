package com.breakidea.lotus.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;

@Scope("request")
public abstract class AbstractController {

	@Resource
	protected HttpServletRequest request;

	public AbstractController() {
		System.out.println("AbstractController");
	}
}
