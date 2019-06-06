package com.breakidea.noah.support.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

public class SimpleHttpClient {
	public static String get(HttpRequest request) {
		if (request == null) {
			return null;
		}
		return request(request.getServerAddress(), request.getServerPort(), RequestType.GET, request.getRequestPath(),
				request.getParams(), request.getChartset(), request.getSoTimeout());
	}

	public static String post(HttpRequest request) {
		if (request == null) {
			return null;
		}
		return request(request.getServerAddress(), request.getServerPort(), RequestType.POST, request.getRequestPath(),
				request.getParams(), request.getChartset(), request.getSoTimeout());
	}

	private enum RequestType {
		GET, POST;
	}

	private static String request(String serverAddress, int serverPort, RequestType type, String requestPath,
			Map<String, String> paramsMap, Charset charset, int soTimeout) {
		Socket socket = null;
		BufferedWriter writer = null;
		try {
			socket = new Socket(InetAddress.getByName(serverAddress), serverPort);
			socket.setSoTimeout(soTimeout);
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), charset));

			String params = getRequestParms(paramsMap, charset);
			requestPath = getRequestPath(type, requestPath, paramsMap, charset);
			writer.write(getRequestTypeHeader(type, requestPath));
			writer.write("Content-Length: " + (params == null ? 0 : params.length()) + "\r\n");
			writer.write("Content-Type: application/x-www-form-urlencoded\r\n");
			writer.write("Host: " + serverAddress + "\r\n");
			writer.write("\r\n");
			writer.write(params);
			writer.flush();

			BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line;
			int mark = 0;
			StringBuilder response = new StringBuilder();
			while ((line = rd.readLine()) != null) {
				if (mark >= 1) {
					response.append(line).append("\n");
				}
				if (line.trim().equals("")) {
					mark += 1;
				}
			}
			rd.close();
			return response.toString().trim();
		}
		catch (Throwable e) {
			return e.getMessage();
		}
		finally {
			if (socket != null) {
				try {
					socket.close();
				}
				catch (IOException e) {
				}
			}

			if (writer != null) {
				try {
					writer.close();
				}
				catch (IOException e) {
				}
			}
		}
	}

	private static String getRequestPath(RequestType type, String requestPath, Map<String, String> paramsMap,
			Charset charset) {
		if (type == RequestType.GET) {
			return requestPath + "?" + getRequestParms(paramsMap, charset);
		}
		return requestPath;
	}

	private static String getRequestTypeHeader(RequestType type, String requestPath) {
		if (type == RequestType.POST) {
			return "POST " + requestPath + " HTTP/1.0\r\n";
		}
		return "GET " + requestPath + " HTTP/1.0\r\n";
	}

	private static String getRequestParms(Map<String, String> paramsMap, Charset charset) {
		if (paramsMap == null) {
			return "";
		}
		try {
			StringBuilder paramsBuilder = new StringBuilder();
			for (Entry<String, String> entry : paramsMap.entrySet()) {
				paramsBuilder.append(URLEncoder.encode(entry.getKey(), charset.name())).append("=")
						.append(URLEncoder.encode(entry.getValue(), charset.name())).append("&");
			}
			return paramsBuilder.toString();
		}
		catch (Throwable e) {
			return "";
		}
	}
}
