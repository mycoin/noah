package org.ionnic.app.controller.api;

import org.ionnic.app.model.Template;
import org.ionnic.app.service.TemplateService;
import org.ionnic.common.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author apple
 *
 */
@Controller
@RequestMapping("/api/template")
public class TemplateAction extends ActionSupport {

    @Autowired
    TemplateService templateService;

    @RequestMapping()
    @ResponseBody
    public void get() throws Exception {
        Template tpl = new Template();
        tpl.setName("测试");
        tpl.setContent("测试测试");

        templateService.add(tpl);
    }

    @RequestMapping(value = "/{id}", method = { RequestMethod.GET })
    @ResponseBody
    public Template get(@PathVariable(value = "id") int id) throws Exception {
        return templateService.query(id);
    }

    @RequestMapping(method = { RequestMethod.POST })
    @ResponseBody
    public ModelMap post(Template template) throws Exception {
        ModelMap model = new ModelMap();

        model.addAttribute(STATUS, 0);
        model.addAttribute(STATUS_INFO, "OK");
        model.addAttribute(DATA, templateService.add(template));

        return model;
    }

}
