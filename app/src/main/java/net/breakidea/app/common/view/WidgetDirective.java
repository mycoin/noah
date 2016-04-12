package net.breakidea.app.common.view;

import org.apache.velocity.runtime.directive.Define;

/**
 * @author apple
 *
 */
public class WidgetDirective extends Define {

    private static final String BLOCK_NAME = "widget";

    public WidgetDirective() {
        System.out.println("WidgetDirective");
    }

    @Override
    public String getName() {
        return BLOCK_NAME;
    }
}
