package org.ionnic.common.support;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author apple
 *
 */
@SuppressWarnings("serial")
public class DefaultWebException extends ServletException {

    @SuppressWarnings("unused")
    private final Log log = LogFactory.getLog(getClass());

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