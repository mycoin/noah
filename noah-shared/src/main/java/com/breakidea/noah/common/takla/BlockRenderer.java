package com.breakidea.noah.common.takla;

public interface BlockRenderer {

	BlockRenderer setParameter(String name, Object value);

	String render(Block block);
}
