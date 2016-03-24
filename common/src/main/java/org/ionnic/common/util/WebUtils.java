package org.ionnic.common.util;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ionnic.common.support.DESDigest;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

/**
 * @author apple
 *
 */
public class WebUtils {

    public static final String HEADER_NAME = "X-Request-Token";

    public static final String PARAMETER_NAME = "_csrfToken";

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
     * @param request
     * @param response
     * @param response
     * @throws Exception
     */
    public static void exposeSessionTokenAttribute( HttpServletRequest request ) throws RuntimeException {
        HttpSession session = request.getSession(true);
        Assert.notNull(session, "The session cannot be null");

        String code = DESDigest.encrypt(session.getId(), HEADER_NAME);
        request.setAttribute(HEADER_NAME, code);
    }

    /**
     * @param request
     * @return
     */
    public static boolean checkRequestSessionToken( HttpServletRequest request ) {
        HttpSession session = request.getSession(false);
        String tokenValue = request.getHeader(HEADER_NAME);
        if (tokenValue == null) {
            tokenValue = request.getParameter(PARAMETER_NAME);
        }

        if (session == null || tokenValue == null) {
            return false;
        }
        String sessionId = session.getId();
        return tokenValue.equals(DESDigest.encrypt(sessionId, HEADER_NAME));
    }

    /**
     * @param response
     */
    public static void exposeXSSResponseHeader( HttpServletResponse response ) {
        response.addHeader("X-Frame-Options", "deny");
        response.addHeader("X-XSS-Protection", "1; mode=block");
        response.addHeader("X-UA-Compatible", "IE=Edge,chrome=1");
    }
}
