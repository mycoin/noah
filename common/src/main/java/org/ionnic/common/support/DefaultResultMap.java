package org.ionnic.common.support;

import java.io.Serializable;

import org.springframework.ui.ModelMap;

/**
 * @author apple
 *
 */
public class DefaultResultMap implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    private Object data;

    private int status;

    private String statusInfo;

    /**
     * @param status
     * @param statusInfo
     */
    public DefaultResultMap( int status, String statusInfo ) {
        this(null, status, statusInfo);
    }

    /**
     * @param data
     */
    public DefaultResultMap( Object data ) {
        this(data, 0, "OK");
    }

    /**
     * @param object
     * @param status
     * @param statusInfo
     */
    public DefaultResultMap( Object data, int status, String statusInfo ) {
        setStatus(status);
        setStatusInfo(statusInfo);
        setData(data);
    }

    /**
     * @param modelMap
     */
    public void exposeToModel(ModelMap modelMap) {
        modelMap.clear();

        modelMap.put(ActionSupport.DATA, data);
        modelMap.put(ActionSupport.STATUS, status);
        modelMap.put(ActionSupport.STATUS_INFO, statusInfo);
    }

    public void setData( Object data ) {
        this.data = data;
    }

    public void setStatus( int status ) {
        this.status = status;
    }

    public void setStatusInfo( String statusInfo ) {
        this.statusInfo = statusInfo;
    }

}
