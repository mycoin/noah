package org.ionnic.common.model;

import java.util.Collection;
import java.util.Map;

import org.ionnic.common.util.JsonUtils;
import org.springframework.ui.ModelMap;

public class JSONObject implements JSONMessageType {

    private int status = 0;

    private String statusInfo = "OK";

    private ModelMap data = new ModelMap();

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the statusInfo
     */
    public String getStatusInfo() {
        return statusInfo;
    }

    /**
     * @param statusInfo the statusInfo to set
     */
    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    /**
     * @return the data
     */
    public ModelMap getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(ModelMap data) {
        this.data = data;
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

}
