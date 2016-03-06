package org.ionnic.app.controller.api;

import java.util.List;

import org.ionnic.app.model.Document;
import org.ionnic.app.service.DocumentService;
import org.ionnic.common.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author apple
 *
 */
@Controller
@RequestMapping("/api/document")
public class DocumentAction extends ActionSupport {

    @Autowired
    DocumentService documentService;

    @RequestMapping(method = { RequestMethod.GET })
    @ResponseBody
    public List<Document> get() throws Exception {
        return documentService.query(0);
    }

    @RequestMapping(method = { RequestMethod.GET })
    @ResponseBody
    public List<Document> get(@RequestParam("id") int id) throws Exception {
        return documentService.query(id);
    }

    @RequestMapping(method = { RequestMethod.POST })
    @ResponseBody
    public ModelMap post(Document document) throws Exception {
        documentService.save(document);
        return null;
    }

}
