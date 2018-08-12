package com.breakidea.noah.framework.view;

import java.io.StringWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.springframework.web.servlet.view.velocity.VelocityView;

@SuppressWarnings("deprecation")
public class TemplateBasedLayoutView extends VelocityView {

	public static final String DEFAULT_LAYOUT_KEY = "screenLayout";

	public static final String DEFAULT_SCREEN_CONTENT_KEY = "screenContent";

	public static final String DEFAULT_LAYOUT = "layout/defaultLayout.vm";

	@Override
	protected void doRender(Context context, HttpServletResponse response) throws Exception {
		renderScreenContent(context);

		// Velocity context now includes any mappings that were defined
		String layoutUrlToUse = (String) context.get(DEFAULT_LAYOUT_KEY);
		if (layoutUrlToUse != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Screen content template has requested layout [" + layoutUrlToUse + "]");
			}
		} else {
			// No explicit layout URL given -> use default layout of this view.
			layoutUrlToUse = DEFAULT_LAYOUT;
		}

		mergeTemplate(getTemplate(layoutUrlToUse), context, response);
	}

	/**
	 * The resulting context contains any mappings from render, plus screen
	 * content.
	 */
	private void renderScreenContent(Context velocityContext) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Rendering screen content template [" + getUrl() + "]");
		}

		StringWriter sw = new StringWriter();
		Template screenContentTemplate = getTemplate(getUrl());
		screenContentTemplate.merge(velocityContext, sw);

		// Put rendered content into Velocity context.
		velocityContext.put(DEFAULT_SCREEN_CONTENT_KEY, sw.toString());
	}
}