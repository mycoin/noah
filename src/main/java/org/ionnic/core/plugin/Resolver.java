package org.ionnic.core.plugin;

import java.util.Locale;
import java.util.Map;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

public class Resolver implements ViewResolver {
	private Map<String, ViewResolver> resolvers;

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		int n = viewName.lastIndexOf(".");
		if (n == (-1)) {
			return null;
		}
		String suffix = viewName.substring(n + 1);

		ViewResolver resolver = resolvers.get(suffix);
		if (resolver != null) {
			return resolver.resolveViewName(viewName.substring(0, n), locale);
		}
		return null;
	}

	public void setResolvers(Map<String, ViewResolver> resolvers) {
		this.resolvers = resolvers;
	}

}