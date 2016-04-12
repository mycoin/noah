package net.breakidea.common.support;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import net.breakidea.common.config.ConfigConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author apple
 *
 */
@SuppressWarnings("serial")
public class WebException extends Exception implements ConfigConstants {

    @SuppressWarnings("unused")
    private final Log log = LogFactory.getLog(getClass());

    private String statusText;

    private int statusCode;

    private Throwable innerException = null;

    /**
     * @param status
     * @param statusInfo
     */
    public WebException( int status, String statusInfo ) {
        super(statusInfo);

        this.statusCode = status;
        this.statusText = statusInfo;
    }

    /**
     * @param status
     * @param statusInfo
     */
    public WebException( int status, String statusInfo, Throwable exception ) {
        super(statusInfo);

        this.statusCode = status;
        this.statusText = statusInfo;
        setCause(exception);
    }

    /**
     * @param mv
     * @param object
     */
    public void responseTo( ModelAndView mv, HttpServletResponse response ) {
        if (innerException == null) {
            innerException = new ServletException(statusText);
        }

        mv.addObject(DATA_NAME, innerException);
        mv.addObject(STATUS_NAME, statusCode);
        mv.addObject(STATUS_INFO_NAME, statusText);

        if (response != null) {
            response.setStatus(statusCode);
        }
    }

    /**
     * @param data the data to set
     */
    public void setCause( Throwable data ) {
        this.innerException = data;
    }
}