package org.ionnic.common.support;

import javax.servlet.ServletException;

/**
 * @author apple
 *
 */
@SuppressWarnings("serial")
public class DefaultWebException extends ServletException {

    private String statusInfo;

    private int status;

    private Object data = new Object();

    /**
     * @param status
     * @param statusInfo
     */
    public DefaultWebException( int status, String statusInfo ) {
        super(statusInfo);

        this.status = status;
        this.statusInfo = statusInfo;
    }

    /**
     * @param status
     * @param statusInfo
     */
    public DefaultWebException( int status, String statusInfo, Throwable exception ) {
        super(statusInfo);

        this.status = status;
        this.statusInfo = statusInfo;

        this.data = exception;
    }

    /**
     * @return the data
     */
    public Object getData() {
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
     * @param data the data to set
     */
    public void setData( Object data ) {
        this.data = data;
    }
}