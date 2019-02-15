package com.breakidea.noah.support.misc;

import java.nio.charset.Charset;
import java.util.Map;

public class HttpRequest {
	private String serverAddress;
	private int serverPort = 80;
	private String requestPath;
	private int soTimeout = 3000;
	private Map<String, String> params;
	private Charset chartset = Charset.forName("UTF-8");

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getRequestPath() {
		return requestPath;
	}

	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public Charset getChartset() {
		return chartset;
	}

	public void setChartset(Charset chartset) {
		this.chartset = chartset;
	}

	public int getSoTimeout() {
		return soTimeout;
	}

	public void setSoTimeout(int soTimeout) {
		this.soTimeout = soTimeout;
	}
}
