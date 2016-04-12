package net.breakidea.common.util;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.breakidea.common.support.DigestSupport;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

/**
 * @author apple
 *
 */
public abstract class WebUtils extends org.springframework.web.util.WebUtils {

    public static final String HEADER_NAME = "X-Request-Token";

    public static final String PARAMETER_NAME = "csrf_token";

    /**
     * @param handler
     * @return
     */
    public static boolean hasAnnotation( Object handler ) {
        ResponseBody anno = null;
        try {
            Method me = ((HandlerMethod) handler).getMethod();
            anno = AnnotationUtils.findAnnotation(me, ResponseBody.class);
            anno.annotationType();
        } catch (Exception e) {
            return false;
        }

        return anno != null;
    }

    /**
     * SessionId 的加密
     *
     * @param request
     * @return
     */
    public static String getSessionToken( HttpServletRequest request ) {
        HttpSession session = request.getSession(true);
        Assert.notNull(session, "The session cannot be null");

        return DigestSupport.encrypt(session.getId());
    }

    /**
     * @param request
     * @return
     */
    public static boolean checkSessionToken( HttpServletRequest request ) {
        HttpSession session = request.getSession(false);
        String tokenValue = null;
        try {
            tokenValue = request.getHeader(HEADER_NAME);
            if (tokenValue == null) {
                tokenValue = request.getParameter(PARAMETER_NAME);
            }
        } catch (Exception e) {
        }
        if (session == null || tokenValue == null) {
            return false;
        } else {
            return tokenValue.equals(getSessionToken(request));
        }
    }

    /**
     * @param request
     * @return
     */
    public static String getRemoteAddr( HttpServletRequest request ) {
        String ip = request.getHeader("X-Forwarded-For");
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
}
