package org.ionnic.common.web;

import java.util.Map;

public class UriLandingServer {

    private Map<String, Object> landingUrl;

    /**
     * @return the landingUrl
     */
    public Map<String, Object> getLandingUrl() {
        return landingUrl;
    }

    /**
     * @param landingUrl the landingUrl to set
     */
    public void setLandingUrl(Map<String, Object> landingUrl) {
        this.landingUrl = landingUrl;
    }
}
