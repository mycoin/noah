package org.ionnic.app.service;

import org.ionnic.app.model.Template;

/**
 * @author apple
 *
 */
public interface TemplateService {

    public Template query(int id) throws Exception;

    public int add(Template template) throws Exception;
}
