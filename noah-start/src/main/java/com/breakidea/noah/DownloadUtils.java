package com.breakidea.noah;

import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadUtils {
	public static String downloadCDN(String downloadUrl) throws Exception {
		return downloadCDN(downloadUrl, "UTF-8");
	}

	public static String downloadCDN(String downloadUrl, String charsetName) throws Exception {

		HttpURLConnection httpConn = null;
		try {
			URL urlObj = new URL(downloadUrl);
			httpConn = (HttpURLConnection) urlObj.openConnection();

			httpConn.setDoInput(true);
			httpConn.setRequestMethod("GET");
			httpConn.setConnectTimeout(60000);
			httpConn.connect();

			if (httpConn.getResponseCode() == 200) {
				return IOUtils.toString(httpConn.getInputStream(), charsetName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpConn != null) {
				httpConn.disconnect();
			}
		}
		return null;
	}
}
