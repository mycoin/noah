package com.breakidea.noah.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Model implements Featureable {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> featureMap;

	private Integer status;

	private String message;

	@Override
	public boolean clearFeature() {
		Map<String, Object> feature = getFeatureMap();
		return ModelUtils.clear(feature);
	}

	@Override
	public <T> T getFeature(String key) {
		Map<String, Object> feature = getFeatureMap();

		return ModelUtils.get(feature, key);
	}

	@Override
	public <T> T putFeatureIfAbsent(String key, T value) {
		if (key == null || value == null) {
			return null;
		}
		Map<String, Object> feature = getFeatureMap();

		if (feature == null) {
			feature = new HashMap<>();
			setFeatureMap(feature);
		}

		if (feature.containsKey(key)) {
			return null;
		}
		return ModelUtils.set(feature, key, value);
	}

	@Override
	public <T> T removeFeature(String key) {
		Map<String, Object> feature = getFeatureMap();

		return ModelUtils.remove(feature, key);
	}

	@Override
	public <T> T setFeature(String key, T value) {
		if (key == null || value == null) {
			return null;
		}
		Map<String, Object> feature = getFeatureMap();

		if (feature == null) {
			feature = new HashMap<>();
			setFeatureMap(feature);
		}

		return ModelUtils.set(feature, key, value);
	}

	@Override
	public void setFeatures(Map<String, Object> featureMap) {
		if (featureMap == null) {
			return;
		}

		Map<String, Object> feature = getFeatureMap();
		if (feature == null) {
			feature = new HashMap<>();
			setFeatureMap(feature);
		}
		feature.putAll(featureMap);
		setFeatureMap(featureMap);
	}
}
