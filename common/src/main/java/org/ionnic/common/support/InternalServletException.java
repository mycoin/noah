package org.ionnic.common.support;

import javax.servlet.ServletException;

import org.ionnic.common.model.JSONObject;

/**
 * @author apple
 *
 */
public class InternalServletException extends ServletException {

    private static final long serialVersionUID = 1L;

    private JSONObject result;

    /**
     * @param status
     * @param statusInfo
     */
    public InternalServletException(int status, String statusInfo) {
        result = new JSONObject();

        result.setStatus(status);
        result.setStatusInfo(statusInfo);

        setException(null);
    }

    /**
     * @param statusInfo
     */
    public InternalServletException(String statusInfo) {
        this(500, statusInfo);
    }

    /**
     * @param exception
     */
    public void setException(Exception exception) {
        result.addAttribute("error", exception);
    }

    /**
     * @return
     */
    public JSONObject getObject() {
        return result;
    }

    @Override
    public String toString() {
        return result.toString();
    }
}
