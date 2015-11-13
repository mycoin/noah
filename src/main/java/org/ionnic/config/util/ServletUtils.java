package org.ionnic.config.util;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

/**
 * @author apple
 *
 */
public abstract class ServletUtils extends ServletRequestUtils {

    /**
     * @param name
     * @param requiredType
     * @return
     * @throws BeansException
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAttribute(HttpServletRequest request, String name, Class<T> requiredType)
            throws BeansException {
        try {
            return (T) request.getAttribute(name);
        } catch (Exception e) {
        }
        return null;

    }

    /**
     * @param request
     * @param name
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        for (Cookie item : request.getCookies()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    /**
     * @param request
     * @return
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
                // According to the network card to get the machine
                // configuration IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }

        // For the case of multiple agents, the first IP for the client to real
        // IP, multiple IP according to ',' segmentation
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    /**
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        String requestedWith = request.getHeader("X-Requested-With");
        if (requestedWith == null) {
            return false;
        } else {
            return "XMLHttpRequest".equals(requestedWith);
        }
    }

    /**
     * @param method
     * @param request
     * @return
     */
    public static boolean isJSONResponse(Object handle) {
        ResponseBody anno = null;
        try {
            Method me = ((HandlerMethod) handle).getMethod();
            anno = AnnotationUtils.findAnnotation(me, ResponseBody.class);
            anno.annotationType();
        } catch (Exception e) {

        }

        return anno != null;
    }
}
