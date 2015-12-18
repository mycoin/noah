package org.ionnic.common.view.external;

import java.io.IOException;

import javax.servlet.ServletException;

/**
 * @author apple
 *
 */
public class DefaultExternalTemplateLoader extends ExternalTemplateLoader {

    private int interval = 3600 * 5;

    private String appKey;

    private String securityKey;

    private String serviceUrl;

    @Override
    public boolean fetch() throws IOException, ServletException {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @return the appKey
     */
    public String getAppKey() {
        return appKey;
    }

    /**
     * @return the interval
     */
    public int getInterval() {
        return interval;
    }

    /**
     * @return the securityKey
     */
    public String getSecurityKey() {
        return securityKey;
    }

    /**
     * @return the serviceUrl
     */
    public String getServiceUrl() {
        return serviceUrl;
    }

    /**
     * @param appKey the appKey to set
     */
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    /**
     * @param interval the interval to set
     */
    public void setInterval(int interval) {
        this.interval = interval;
    }

    /**
     * @param securityKey the securityKey to set
     */
    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }

    /**
     * @param serviceUrl the serviceUrl to set
     */
    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }
}
