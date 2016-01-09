package org.ionnic.common;

import javax.servlet.ServletException;

import org.springframework.ui.ModelMap;

/**
 * @author apple
 *
 */
public class HttpException extends ServletException {

    private static final long serialVersionUID = 1L;

    private Object statusInfo;

    private int status;

    private ModelMap data;

    /**
     * @param status
     * @param statusInfo
     */
    public HttpException(int status, String statusInfo) {
        super(statusInfo);

        this.status = status;
        this.statusInfo = statusInfo;

        data = new ModelMap();
    }

    /**
     * @return
     */
    public ModelMap getData() {
        return data;
    }

    @Override
    public String getLocalizedMessage() {
        if (statusInfo == null) {
            return "Unknown";
        } else {
            return statusInfo.toString();
        }
    }

    /**
     * @return
     */
    public int getStatus() {
        return status;
    }

    /**
     * @return
     */
    public Object getStatusInfo() {
        return statusInfo;
    }

    /**
     * @param ex
     */
    public void setException(Exception ex) {
        data.addAttribute("type", ex.getClass().getName());
        data.addAttribute("message", ex.getMessage());
    }
}
