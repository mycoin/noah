/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ionnic.common.support.view;

import java.io.StringWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.ViewToolContext;
import org.springframework.beans.BeansException;
import org.springframework.web.servlet.view.velocity.VelocityToolboxView;

/**
 * BetterVelocityView emulates the functionality offered by Velocity's
 * VelocityLayoutServlet to ease page composition from different templates.
 *
 * <p>The {@code url} property should be set to the content template
 * for the view, and the layout template location should be specified as
 * {@code layoutUrl} property. A view can override the configured
 * layout template location by setting the appropriate key (the default
 * is "layout") in the content template.
 *
 * <p>When the view is rendered, the VelocityContext is first merged with
 * the content template (specified by the {@code url} property) and
 * then merged with the layout template to produce the final output.
 *
 * <p>The layout template can include the screen content through a
 * VelocityContext variable (the default is "screen_content").
 * At runtime, this variable will contain the rendered content template.
 *
 */
public final class BetterVelocityView extends VelocityToolboxView {

    /**
     * Overrides the normal rendering process in order to pre-process the Context,
     * merging it with the screen template into a single value (identified by the
     * value of screenContentKey). The layout template is then merged with the
     * modified Context in the super class.
     */
    @Override
    protected final void doRender( Context context, HttpServletResponse response ) throws Exception {
        PageControl page = new PageControl((ViewToolContext) context);

        StringWriter body = getMergeContent(getUrl(), context);
        String layoutPath = page.getLayout();

        if (layoutPath == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("no layoutPath was assigned, response it. url:" + getUrl());
            }
            response.getWriter().write(body.toString());
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("Rendering layout [" + layoutPath + "] for url:" + getUrl());
            }
            page.prepareLayout(body);
            mergeTemplate(getTemplate(layoutPath), context, response);
        }
    }

    /**
     * The resulting context contains any mappings from render, plus screen content.
     * @return
     */
    protected final StringWriter getMergeContent( String templateName, Context velocityContext ) throws Exception {
        StringWriter sw = new StringWriter();

        if (logger.isDebugEnabled()) {
            logger.debug("Rendering template [" + templateName + "]");
        }

        try {
            Template tpl = getTemplate(templateName);
            tpl.merge(velocityContext, sw);
        } catch (Exception e) {
            logger.error("Failed to render template [" + templateName + "]", e);
        }

        return sw;
    }

    @Override
    protected final void initApplicationContext() throws BeansException {
        super.initApplicationContext();

        VelocityEngine velocityEngine = getVelocityEngine();
        velocityEngine.loadDirective(BlockDirective.class.getName());
    }
}
