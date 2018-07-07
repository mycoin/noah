package com.breakidea.noah.shared.model;

import java.io.Serializable;
import java.util.Date;

public abstract class GenaralModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Date gmtCreated;

	private Date gmtModified;

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public Long getId() {
		return id;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
