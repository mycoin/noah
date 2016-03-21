package org.ionnic.common.support.util;

import java.lang.reflect.Method;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

public class WebUtils {
    /**
     * @param method
     * @param request
     * @return
     */
    public static boolean hasAnnotation( Object handler ) {
        ResponseBody anno = null;
        try {
            Method me = ((HandlerMethod) handler).getMethod();
            anno = AnnotationUtils.findAnnotation(me, ResponseBody.class);
            anno.annotationType();
        } catch (Exception e) {

        }

        return anno != null;
    }
}
