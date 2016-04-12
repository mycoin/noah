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

package net.breakidea.common.support.view;

import java.io.StringWriter;

import javax.servlet.http.HttpServletResponse;

import net.breakidea.common.ConfigConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.runtime.RuntimeConstants;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
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
public final class BetterVelocityView extends VelocityToolboxView implements ConfigConstants, RuntimeConstants {

    private Log logger = LogFactory.getLog(getClass());

    private static BetterVelocityView instance = null;

    /**
     * Overrides the normal rendering process in order to pre-process the Context,
     * merging it with the screen template into a single value (identified by the
     * value of screenContentKey). The layout template is then merged with the
     * modified Viewport in the super class.
     */
    @Override
    protected void doRender( Context context, HttpServletResponse response ) throws Exception {
        Viewport page = new Viewport(this, context);

        StringWriter body = getMergeContent(getUrl(), context);
        String layoutPath = page.getLayout();

        if (layoutPath == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("No layoutPath was assigned, response it. url:" + getUrl());
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
     * @return
     */
    public static BetterVelocityView getInstance() {
        return instance;
    }

    /**
     * The resulting context contains any mappings from render, plus screen content.
     * @return
     */
    protected StringWriter getMergeContent( String templateName, Context velocityContext ) throws Exception {
        StringWriter out = new StringWriter();

        if (logger.isDebugEnabled()) {
            logger.debug("Rendering screen template [" + templateName + "]");
        }

        try {
            Template tpl = getTemplate(templateName);
            tpl.merge(velocityContext, out);

            return out;
        } catch (Exception e) {
            logger.error("Rendering screen [" + templateName + "] with error", e);
            throw e;
        }
    }

    @Override
    protected void initApplicationContext() throws BeansException {
        VelocityEngine velocityEngine = getVelocityEngine();

        if (velocityEngine == null) {
            velocityEngine = autodetectVelocityEngine();
            velocityEngine.loadDirective(BlockDirective.class.getName());
            instance = this;
            super.initApplicationContext();
        } else {
            throw new FatalBeanException("init velocity engine with error");
        }
    }
}
