package org.ionnic.common.util;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ionnic.common.support.DigestSupport;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

/**
 * @author apple
 *
 */
public abstract class WebUtils {

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
        String tokenValue = request.getHeader(HEADER_NAME);
        if (tokenValue == null) {
            tokenValue = request.getParameter(PARAMETER_NAME);
        }
        if (session == null || tokenValue == null) {
            return false;
        } else {
            return tokenValue.equals(getSessionToken(request));
        }
    }
}
