package com.breakidea.lotus.web.beans;

import java.io.StringWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.springframework.util.NumberUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.velocity.VelocityView;

import com.breakidea.lotus.web.utils.JsonUtils;
import com.breakidea.lotus.web.utils.Utils;

@SuppressWarnings("deprecation")
public class TemplateBasedLayoutView extends VelocityView {

	public static final String DEFAULT_LAYOUT_URL = "layout/defaultLayout.vm";

	public static final String DEFAULT_LAYOUT_KEY = "screenLayout";

	public static final String DEFAULT_SCREEN_CONTENT_KEY = "screenContent";

	@Override
	protected void doRender(Context context, HttpServletResponse response) throws Exception {
		renderScreenContent(context);

		String layoutUrl = (String) context.get(DEFAULT_LAYOUT_KEY);
		if (layoutUrl == null) {
			layoutUrl = DEFAULT_LAYOUT_URL;
		}
		mergeTemplate(getTemplate(layoutUrl), context, response);
	}

	@Override
	protected Context createVelocityContext(Map<String, Object> model) throws Exception {
		model.put("NumberUtils", NumberUtils.class);
		model.put("StringUtils", StringUtils.class);
		model.put("ObjectUtils", ObjectUtils.class);
		model.put("JsonUtils", JsonUtils.class);
		model.put("Utils", Utils.class);

		return new VelocityContext(model);
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