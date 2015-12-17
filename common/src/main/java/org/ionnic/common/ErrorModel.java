package org.ionnic.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public class ErrorModel {

    public static final String ERROR_MODEL_KEY = ErrorModel.class.getName() + ".ERROR_ATTRIBUTE";

    public static final String ACCESS_DENIED = "Access Denied";

    public static final String INTERNAL_ERROR = "Internal Error";

    private int status;

    private String statusInfo;

    private Map<String, Object> data = new HashMap<String, Object>();

    public ErrorModel(HttpServletRequest request, int status, String statusInfo) {
        if (request != null) {
            request.setAttribute(ErrorModel.ERROR_MODEL_KEY, this);
        }
        setStatus(status);
        setStatusInfo(statusInfo);
    }

    /**
     * @param result
     */
    public void extractTo(ModelAndView result) {

        result.addObject("data", data);
        result.addObject("status", status);
        result.addObject("statusInfo", statusInfo);

    }

    /**
     * @return the exception
     */
    public Map<String, Object> getData() {
        return data;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @return the statusInfo
     */
    public String getStatusInfo() {
        return statusInfo;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @param statusInfo
     *            the statusInfo to set
     */
    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }
}
