package org.ionnic.core.view;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

public class ExceptionViewResolver extends UrlBasedViewResolver {
	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
	    // TODO Auto-generated method stub
		
		System.out.println(viewName);
		
	    return super.resolveViewName(viewName, locale);
	}
}
