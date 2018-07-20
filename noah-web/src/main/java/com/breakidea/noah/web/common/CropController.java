package com.breakidea.noah.web.common;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.breakidea.noah.starter.support.AbstractExtendedController;

@Controller("/common/img")
public class CropController extends AbstractExtendedController {

	@Autowired
	NativeWebRequest webRequest;

	@Override
	protected void handleRequestInternal(ModelAndView mv) throws ServletException {
		mv.setViewName("welcome");
		MultipartHttpServletRequest e = WebUtils.getNativeRequest((HttpServletRequest) webRequest.getNativeRequest(), MultipartHttpServletRequest.class);
		
		
		System.out.println(e);
	}
}