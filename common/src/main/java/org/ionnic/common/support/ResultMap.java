package org.ionnic.common.support;

import java.io.Serializable;

/**
 * @author apple
 *
 */
public class ResultMap implements Cloneable, Serializable {

    private static final long serialVersionUID = 3010067735038366774L;

    private int status = 0;

    private Object data = null;

    private String statusInfo = "OK";

    /**
     * @param result
     */
    public ResultMap(Object result) {
        data = result;
    }

    /**
     * @param result
     * @param status
     * @param statusInfo
     */
    public ResultMap(Object data, int status, String statusInfo) {
        this.data = data;
        this.status = status;
        this.statusInfo = statusInfo;
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
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @param statusInfo the statusInfo to set
     */
    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

}
