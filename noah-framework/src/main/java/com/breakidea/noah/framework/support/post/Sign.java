package com.breakidea.noah.framework.support.post;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Sign implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, Boolean> positions = new HashMap<String, Boolean>();

	public boolean containsKey(String key) {
		return positions.containsKey(key);
	}

	public boolean get(String key) {
		if (positions.containsKey(key)) {
			Boolean obj = positions.get(key);
			return obj == null ? false : obj;
		}

		return false;
	}

	public Map<String, Boolean> getAll() {
		return positions;
	}

	public Boolean getCanNull(String key) {
		if (positions.containsKey(key)) {
			return positions.get(key);
		}
		return null;
	}

	public boolean isEmpty() {
		return positions.isEmpty();
	}

	public Collection<String> keySet() {
		return positions.keySet();
	}

	public void remove(String key) {
		positions.remove(key);
	}

	public void set(String key, Boolean isTrueOrFalse) {
		positions.put(key, isTrueOrFalse);
	}

	public void setAll(Map<String, Boolean> positions) {
		this.positions.putAll(positions);
	}

	public void setCanNull(String key, Boolean isTrueOrFalse) {
		positions.put(key, isTrueOrFalse);
	}

	public int size() {
		return positions.size();
	}
}
