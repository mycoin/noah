package org.ionnic.config;

import javax.servlet.ServletException;

import org.springframework.web.servlet.ModelAndView;

public class ErrorModel {

    public static final String ERROR_MODEL_KEY = ErrorModel.class.getName() + ".ERROR_ATTRIBUTE";

    public static final String ACCESS_DENIED = "Access Denied";

    public static final String INTERNAL_ERROR = "Internal Error";

    private int status = 500;

    private String statusInfo = ACCESS_DENIED;

    private Exception exception = new ServletException();

    /**
     * @param exception
     */
    public ErrorModel(Exception exception) {
        setException(exception);
    }

    /**
     * @param status
     * @param statusInfo
     */
    public ErrorModel(int status, String statusInfo) {
        setStatus(status);
        setStatusInfo(statusInfo);
    }

    /**
     * @param result
     */
    public void extractTo(ModelAndView result) {

        result.addObject("data", exception);
        result.addObject("status", status);
        result.addObject("statusInfo", statusInfo);
    }

    /**
     * @return the exception
     */
    public Exception getException() {
        return exception;
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
     * @param exception
     *            the exception to set
     */
    public void setException(Exception exception) {
        this.exception = new Exception(exception.getMessage());
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
