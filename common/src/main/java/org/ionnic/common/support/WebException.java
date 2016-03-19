package org.ionnic.common.support;

import javax.servlet.ServletException;

/**
 * @author apple
 *
 */
public class WebException extends ServletException {

    private static final long serialVersionUID = 1L;

    private String statusInfo;

    private int status;

    private Object data = new Object();

    /**
     * @param status
     * @param statusInfo
     */
    public WebException( int status, String statusInfo ) {
        super(statusInfo);

        this.status = status;
        this.statusInfo = statusInfo;
    }

    /**
     * @param status
     * @param statusInfo
     */
    public WebException( int status, String statusInfo, Throwable exception ) {
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
