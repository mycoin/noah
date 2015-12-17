package org.ionnic.common.result;

import java.util.LinkedHashMap;

import org.ionnic.common.util.JsonUtils;

public class JSONObject {

    public static final String OK = "OK";

    private int status = 0;

    private String statusInfo = OK;

    private LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();

    /**
     * @param keyName
     * @param value
     */
    public boolean addAttribute(String property, Object value) {
        if (!hasAttribute(property)) {
            setAttribute(property, value);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param property
     * @return
     */
    public Object getAttribute(String property) {
        return data.get(property);
    }

    /**
     * @return the data
     */
    public LinkedHashMap<String, Object> getData() {
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
     * @param property
     * @return
     */
    public boolean hasAttribute(String property) {
        return getAttribute(property) != null;
    }

    /**
     * @param keyName
     * @param value
     */
    public void setAttribute(String property, Object value) {
        data.put(property, value);
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

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
