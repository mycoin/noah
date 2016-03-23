package org.ionnic.common.support.web;

import javax.servlet.http.HttpServletRequest;

import org.ionnic.common.config.ConfigConstants;
import org.ionnic.common.support.DefaultWebException;
import org.ionnic.common.support.view.MappingJacksonJsonView;
import org.ionnic.common.support.view.MappingMarshallerView;
import org.springframework.web.servlet.View;

/**
 * @author apple
 *
 */
public abstract class ActionSupport implements ConfigConstants {

    public static final View JSON = MappingJacksonJsonView.getInstance();

    public static final View XML = MappingMarshallerView.getInstance();

    /**
     * Check the given request
     * @param request current HTTP request
     */
    public abstract boolean checkRequest( HttpServletRequest request, Boolean is ) throws DefaultWebException;

}
