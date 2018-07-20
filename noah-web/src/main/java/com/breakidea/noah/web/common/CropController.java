package com.breakidea.noah.web.common;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.breakidea.noah.starter.support.AbstractExtendedController;

@Controller("/common/img")
public class CropController extends AbstractExtendedController {

	@Resource
	private ServletContext servletContext;

	@Override
	protected void handleRequestInternal(ModelAndView mv) throws ServletException {
		mv.setViewName("welcome");

		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(servletContext);
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		}

	}
}