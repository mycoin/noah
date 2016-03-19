package org.ionnic.common.support.view.external;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.core.io.Resource;

/**
 * @author apple
 */
abstract class SyncExternalTemplate {

    private Resource externalPath;

    private boolean enable = false;

    /**
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public abstract boolean fetch() throws IOException, ServletException;

    /**
     * @return the enable
     */
    public boolean getEnable() {
        return enable;
    }

    /**
     * @return the externalPath
     */
    public Resource getExternalPath() {
        return externalPath;
    }

    /**
     * @param enable the enable to set
     */
    public void setEnable( boolean enable ) {
        this.enable = enable;
    }

    /**
     * @param externalPath the externalPath to set
     */
    public void setExternalPath( Resource externalPath ) {
        this.externalPath = externalPath;
    }

}
