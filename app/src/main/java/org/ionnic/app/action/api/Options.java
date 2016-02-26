package org.ionnic.app.action.api;

import org.ionnic.common.ActionSupport;
import org.ionnic.common.support.WebContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author apple
 *
 */
@Controller
@RequestMapping("/api/options")
public class Options extends ActionSupport {

    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
    public Object index(WebContext context) {
        System.out.println(context);
        return context;
    }
}