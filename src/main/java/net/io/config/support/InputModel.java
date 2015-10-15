package net.io.config.support;

import net.io.config.util.JsonUtils;

public class InputModel {

	/**
	 * @return
	 */
	public String toJSON() {
		return JsonUtils.toJson(this);
	}
}
