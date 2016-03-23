package org.ionnic.common.support.view;

import org.ionnic.common.config.ConfigConstants;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.xml.MarshallingView;

/**
 * @author apple
 *
 */
public class MappingMarshallerView extends MarshallingView implements ConfigConstants {

    private static View main;

    /**
     * @return
     */
    public static View getInstance() {
        if (main == null) {
            main = new MappingMarshallerView();
        }
        return main;
    }
}
