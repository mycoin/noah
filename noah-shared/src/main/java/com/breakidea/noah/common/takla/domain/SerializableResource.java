package com.breakidea.noah.common.takla.domain;

import java.io.Serializable;

public interface SerializableResource extends Serializable {

	public Object getObject();

	public String getName();

	public int getType();
}
