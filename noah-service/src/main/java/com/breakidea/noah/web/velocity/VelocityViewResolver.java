/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.breakidea.noah.web.velocity;

import org.springframework.core.io.Resource;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;

/**
 * Convenience subclass of
 * {@link org.springframework.web.servlet.view.UrlBasedViewResolver} that
 * supports {@link VelocityView} (i.e. Velocity templates) and custom subclasses
 * of it.
 *
 * <p>
 * The view class for all views generated by this resolver can be specified via
 * the "viewClass" property. See UrlBasedViewResolver's javadoc for details.
 *
 * <p>
 * <b>Note:</b> When chaining ViewResolvers, a VelocityViewResolver will check
 * for the existence of the specified template resources and only return a
 * non-null View object if the template was actually found.
 *
 * @author Juergen Hoeller
 * @since 13.12.2003
 * @see #setViewClass
 * @see #setPrefix
 * @see #setSuffix
 * @see #setRequestContextAttribute
 * @see #setExposeSpringMacroHelpers
 * @see #setDateToolAttribute
 * @see #setNumberToolAttribute
 * @see VelocityView
 */
public class VelocityViewResolver extends AbstractTemplateViewResolver {

    private Resource velocityProperties = null;

    public VelocityViewResolver() {
        setViewClass(requiredViewClass());
    }

    /**
     * Requires {@link VelocityView}.
     */
    @Override
    protected Class<?> requiredViewClass() {
        return VelocityView.class;
    }

    public Resource getVelocityProperties() {
        return velocityProperties;
    }

    public void setVelocityProperties(Resource velocityProperties) {
        this.velocityProperties = velocityProperties;
    }

}
