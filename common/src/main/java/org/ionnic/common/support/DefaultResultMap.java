package org.ionnic.common.support;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author apple
 *
 */
public class DefaultResultMap implements Cloneable, Serializable {

    private Map<String, Object> internalMap = new HashMap<String, Object>();

    private static final long serialVersionUID = 1L;

    /**
     * @param status
     * @param statusInfo
     */
    public DefaultResultMap(int status, String statusInfo) {
        this(null, status, statusInfo);
    }

    /**
     * @param data
     */
    public DefaultResultMap(Object data) {
        this(data, 0, "OK");
    }

    /**
     * @param object
     * @param status
     * @param statusInfo
     */
    public DefaultResultMap(Object data, int status, String statusInfo) {
        setStatus(status);
        setStatusInfo(statusInfo);
        setData(data);
    }

    /**
     * @return
     */
    public Map<String, Object> getModelMap() {
        return internalMap;
    }

    public void setData(Object data) {
        internalMap.put("data", data);
    }

    public void setStatus(int status) {
        internalMap.put("status", status);
    }

    public void setStatusInfo(String statusInfo) {
        internalMap.put("statusInfo", statusInfo);
    }

}
