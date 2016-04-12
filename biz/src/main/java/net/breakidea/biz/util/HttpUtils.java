package net.breakidea.biz.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author apple
 *
 */
public abstract class HttpUtils {
    private static final int HTTP_CONNECTION_TIMEOUT = 3000;
    private static final int HTTP_READ_TIMEOUT = 3000;

    /**
     * 根据URL获取一个GET请求的结果，以字符串形式返回
     *
     * @param getUrl
     * @return
     */
    public static String fetch( String getUrl ) {
        try {
            URL url = new URL(getUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(HTTP_CONNECTION_TIMEOUT);
            conn.setReadTimeout(HTTP_READ_TIMEOUT);
            conn.connect();

            InputStream in = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String currentLine;
            StringBuilder sb = new StringBuilder();
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.length() > 0) {
                    sb.append(currentLine);
                }
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据URL下载文件，并保存到指定位置。成功返回true，失败返回false。
     *
     * @param downloadUrl
     * @param saveFilePath 需要带上文件名，e.g. dest/to/your/path/taobao-hsf.tgz
     * @return
     */
    public static boolean download( String downloadUrl, String saveFilePath ) {

        InputStream inStream = null;
        FileOutputStream outStream = null;

        try {
            URL url = new URL(downloadUrl);
            URLConnection conn = url.openConnection();
            inStream = conn.getInputStream();
            outStream = new FileOutputStream(saveFilePath);

            byte[] buffer = new byte[12040];
            int bytesRead = 0;
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close(outStream);
            close(inStream);
        }
    }

    /**
     * @param outStream
     */
    private static void close( Closeable inStream ) {
        if (inStream != null) {
            try {
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
