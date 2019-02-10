package com.breakidea.noah.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Model implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> formSchema = new HashMap<String, Object>();

	private String version;

	private Long widgetId;

}
