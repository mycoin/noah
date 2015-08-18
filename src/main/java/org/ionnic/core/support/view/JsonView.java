package org.ionnic.core.support.view;

/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.core.support.tools.StringTool;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractView;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Spring MVC {@link View} that renders JSON content by serializing the model
 * for the current request using Gson
 * 
 * @author Jeremy Grelle
 * @author Arjen Poutsma
 * @author Rossen Stoyanchev
 * @since 3.0
 * @see org.springframework.http.converter.json.MappingJacksonHttpMessageConverter
 */
public class JsonView extends AbstractView {

	/**
	 * Default content type. Overridable as bean property.
	 */
	public static final String DEFAULT_CONTENT_TYPE = "application/json";

	private Set<String> modelKeys;

	private boolean extractValueFromSingleKeyModel = false;

	private boolean disableCaching = true;

	/**
	 * Construct a new {@code JacksonJsonView}, setting the content type to
	 * {@code application/json}.
	 */
	public JsonView() {
		setContentType(DEFAULT_CONTENT_TYPE);
		setExposePathVariables(false);
	}

	/**
	 * Filters out undesired attributes from the given model. The return value
	 * can be either another {@link Map} or a single value object.
	 * <p>
	 * The default implementation removes {@link BindingResult} instances and
	 * entries not included in the {@link #setRenderedAttributes
	 * renderedAttributes} property.
	 * 
	 * @param model
	 *            the model, as passed on to {@link #renderMergedOutputModel}
	 * @return the object to be rendered
	 */
	protected Object filterModel(Map<String, Object> model) {
		Map<String, Object> result = new HashMap<String, Object>(model.size());
		Set<String> renderedAttributes = (!CollectionUtils.isEmpty(this.modelKeys) ? this.modelKeys : model.keySet());
		for (Map.Entry<String, Object> entry : model.entrySet()) {
			if (!(entry.getValue() instanceof BindingResult) && renderedAttributes.contains(entry.getKey())) {
				result.put(entry.getKey(), entry.getValue());
			}
		}
		return (this.extractValueFromSingleKeyModel && result.size() == 1 ? result.values().iterator().next() : result);
	}

	/**
	 * Return the attributes in the model that should be rendered by this view.
	 */
	public Set<String> getModelKeys() {
		return this.modelKeys;
	}

	@Override
	protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType(getContentType());
		if (this.disableCaching) {
			response.addHeader("Pragma", "no-cache");
			response.addHeader("Cache-Control", "no-cache, no-store, max-age=0");
			response.addDateHeader("Expires", 1L);
		}
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().serializeNulls().setDateFormat("yyyy-MM-dd H:mm:ss")
		        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setVersion(1.0).create();

		Object value = filterModel(model);
		String result = gson.toJson(value);
		result = StringTool.encodeUnicode(result);
		response.getOutputStream().write(result.getBytes());
	}

	/**
	 * Disables caching of the generated JSON.
	 * <p>
	 * Default is {@code true}, which will prevent the client from caching the
	 * generated JSON.
	 */
	public void setDisableCaching(boolean disableCaching) {
		this.disableCaching = disableCaching;
	}

	/**
	 * Set whether to serialize models containing a single attribute as a map or
	 * whether to extract the single value from the model and serialize it
	 * directly.
	 * <p>
	 * The effect of setting this flag is similar to using
	 * {@code MappingJacksonHttpMessageConverter} with an {@code @ResponseBody}
	 * request-handling method.
	 * <p>
	 * Default is {@code false}.
	 */
	public void setExtractValueFromSingleKeyModel(boolean extractValueFromSingleKeyModel) {
		this.extractValueFromSingleKeyModel = extractValueFromSingleKeyModel;
	}

	/**
	 * Set the attribute in the model that should be rendered by this view. When
	 * set, all other model attributes will be ignored.
	 */
	public void setModelKey(String modelKey) {
		this.modelKeys = Collections.singleton(modelKey);
	}

	/**
	 * Set the attributes in the model that should be rendered by this view.
	 * When set, all other model attributes will be ignored.
	 */
	public void setModelKeys(Set<String> modelKeys) {
		this.modelKeys = modelKeys;
	}

}
