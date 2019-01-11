package com.breakidea.noah.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

public class Model implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> formSchema = Collections.emptyMap();

	private String version;

	private Long widgetId;

	public Map<String, Object> getFormSchema() {
		return formSchema;
	}

	public String getVersion() {
		return version;
	}

	public Long getWidgetId() {
		return widgetId;
	}

	public void setFormSchema(Map<String, Object> formSchema) {
		this.formSchema = formSchema;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setWidgetId(Long widgetId) {
		this.widgetId = widgetId;
	}

}
