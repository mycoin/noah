package com.alibaba.rigel.web.support;

import java.io.StringWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.springframework.util.NumberUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.velocity.VelocityView;

import com.alibaba.rigel.framework.util.JsonUtils;

@SuppressWarnings("deprecation")
public class TemplateBasedLayoutView extends VelocityView {

	public static final String DEFAULT_LAYOUT_KEY = "screenLayout";

	public static final String DEFAULT_SCREEN_CONTENT_KEY = "screenContent";

	@Override
	protected void doRender(Context context, HttpServletResponse response) throws Exception {
		StringWriter screen = new StringWriter();
		Template screenTemplate = getTemplate(getUrl());
		screenTemplate.merge(context, screen);

		// Put rendered content into Velocity context.
		context.put(DEFAULT_SCREEN_CONTENT_KEY, screen);

		String layoutUrl = (String) context.get(DEFAULT_LAYOUT_KEY);
		if (!StringUtils.hasLength(layoutUrl)) {
			StreamUtils.copy(screen.toString().getBytes(), response.getOutputStream());
		} else {
			mergeTemplate(getTemplate(layoutUrl), context, response);
		}
	}

	@Override
	protected Context createVelocityContext(Map<String, Object> model) throws Exception {
		model.put("NumberUtils", NumberUtils.class);
		model.put("StringUtils", StringUtils.class);
		model.put("ObjectUtils", ObjectUtils.class);
		model.put("JsonUtils", JsonUtils.class);
		model.put("context", model);

		return new VelocityContext(model);
	}
}