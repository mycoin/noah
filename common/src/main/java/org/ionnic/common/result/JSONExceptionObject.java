package org.ionnic.common.result;

import javax.servlet.ServletException;

/**
 * @author apple
 *
 */
public class JSONExceptionObject extends JSONObject {

    /**
     * @param servletException
     */
    public void setException(ServletException error) {
        setAttribute("error", error);
    }

}
