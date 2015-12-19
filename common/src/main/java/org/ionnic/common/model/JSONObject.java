package org.ionnic.common.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import org.ionnic.common.util.JsonUtils;
import org.springframework.ui.ModelMap;

public class JSONObject implements Serializable, Cloneable {

    private static final long serialVersionUID = -4545992275844080215L;

    private static final String DATA_ARRTIBUTE = "data";

    private static final String STATUS_ATTRIBUTE = "status";

    private static final String STATUS_INFO_ATTRIBUTE = "statusInfo";

    private int status = 0;

    private String statusInfo = "OK";

    private ModelMap data = new ModelMap();

    /**
     * Copy all attributes in the supplied {@code Collection} into this
     * {@code Map}, using attribute name generation for each element.
     * @see #addAttribute(Object)
     */
    public JSONObject addAllAttributes(Collection<?> attributeValues) {
        data.addAllAttributes(attributeValues);
        return this;
    }

    /**
     * Copy all attributes in the supplied {@code Map} into this {@code Map}.
     * @see #addAttribute(String, Object)
     */
    public JSONObject addAllAttributes(Map<String, ?> attributes) {
        data.addAllAttributes(attributes);
        return this;
    }

    /**
     * Add the supplied attribute to this {@code Map} using a
     * {@link org.springframework.core.Conventions#getVariableName generated name}.
     * <p><emphasis>Note: Empty {@link Collection Collections} are not added to
     * the model when using this method because we cannot correctly determine
     * the true convention name. View code should check for {@code null} rather
     * than for empty collections as is already done by JSTL tags.</emphasis>
     * @param attributeValue the model attribute value (never {@code null})
     */
    public JSONObject addAttribute(Object attributeValue) {
        data.addAttribute(attributeValue);
        return this;
    }

    /**
     * Add the supplied attribute under the supplied name.
     * @param attributeName the name of the model attribute (never {@code null})
     * @param attributeValue the model attribute value (can be {@code null})
     */
    public JSONObject addAttribute(String attributeName, Object attributeValue) {
        data.addAttribute(attributeName, attributeValue);
        return this;
    }

    /**
     * @param model
     */
    public void exposeContext(Map<String, Object> model) {
        model.put(DATA_ARRTIBUTE, getData());
        model.put(STATUS_ATTRIBUTE, getStatus());
        model.put(STATUS_INFO_ATTRIBUTE, getStatusInfo());
    }

    /**
     * @return the data
     */
    public ModelMap getData() {
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
    public void setData(ModelMap data) {
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

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }

}
