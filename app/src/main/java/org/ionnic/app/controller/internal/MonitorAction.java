package org.ionnic.app.controller.internal;

import org.ionnic.common.support.ActionSupport;
import org.ionnic.common.support.ResultMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/~/monitor")
public class MonitorAction extends ActionSupport {

    @RequestMapping(method = {})
    @ResponseBody
    public ResultMap execute() {
        return new ResultMap(null, 0, "OK");
    }
}
