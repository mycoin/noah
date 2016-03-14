package org.ionnic.app.controller.api;

import java.util.List;

import org.ionnic.app.model.Document;
import org.ionnic.app.service.DocumentService;
import org.ionnic.common.support.ActionSupport;
import org.ionnic.common.support.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public ResultMap get() throws Exception {
        List<Document> r = documentService.query();
        return new ResultMap(r);
    }

    @RequestMapping(value = "/{id}", method = { RequestMethod.GET })
    @ResponseBody
    public ResultMap get(@PathVariable("id") String id) throws Exception {
        Document r = documentService.query(id);
        if (r == null) {
            return new ResultMap(null, 503, "NULL RECORD");
        } else {
            return new ResultMap(r);
        }
    }

    @RequestMapping(method = { RequestMethod.POST })
    @ResponseBody
    public ResultMap post(Document document) throws Exception {
        boolean r = documentService.save(document);
        if (r) {
            return new ResultMap(r);
        } else {
            return new ResultMap(null, 503, "POST ERROR");
        }
    }

    @RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
    @ResponseBody
    public ResultMap delete(@PathVariable("id") String id) throws Exception {
        boolean r = documentService.delete(id);
        if (r) {
            return new ResultMap(r);
        } else {
            return new ResultMap(null, 503, "POST ERROR");
        }
    }

}
