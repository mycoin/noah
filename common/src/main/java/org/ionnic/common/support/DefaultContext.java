package org.ionnic.common.support;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;

public class DefaultContext extends ModelAndView {

    private int status = 0;

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
     * @return the contentType
     */
    public MediaType getContentType() {
        return contentType;
    }

    /**
     * @param contentType the contentType to set
     */
    public void setContentType(MediaType contentType) {
        this.contentType = contentType;
    }

    private String statusInfo = "OK";

    private MediaType contentType;

}
