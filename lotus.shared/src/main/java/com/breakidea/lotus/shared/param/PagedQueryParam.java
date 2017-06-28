package com.breakidea.lotus.shared.param;

public abstract class PagedQueryParam extends BaseParam {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_PAGE = 0;

	public static final int DEFAULT_RESULT_COUNT = 0;

	public static final int MAX_PAGE_SIZE = 100;

	private int page = DEFAULT_PAGE;

	private int pageSize = DEFAULT_RESULT_COUNT;

	public int getPage() {
		return page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getStartIndex() {
		if (pageSize < 1 || pageSize > MAX_PAGE_SIZE) {
			pageSize = DEFAULT_RESULT_COUNT;
		}
		if (page <= 0) {
			page = 1;
		}
		int start = (page - 1) * pageSize + 1;
		return start;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
