package org.ionnic.app.controller.internal;

import org.ionnic.common.support.ActionSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/~/monitor")
public class MonitorAction extends ActionSupport {

    @RequestMapping(method = {})
    public ModelAndView execute() {
        return new ModelAndView("/~/monitor/index");
    }
}
