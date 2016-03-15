package org.ionnic.app.controller.internal;

import org.ionnic.common.support.ActionSupport;
import org.ionnic.common.support.DefaultResultMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/~/monitor")
public class MonitorAction extends ActionSupport {

    @RequestMapping(method = {})
    public DefaultResultMap execute() {
        return new DefaultResultMap(null);
    }
}

