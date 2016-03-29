package org.ionnic.common.config;

import javax.servlet.http.HttpServletRequest;

import org.ionnic.common.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("-")
public class MonitorController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("monitor")
    public String index( Model data, @RequestParam(name = "tags", required = false) String tags ) throws Exception {
        data.addAttribute("ip", WebUtils.getRemoteAddr(request));
        data.addAttribute("env", System.getProperties());
        data.addAttribute("tag", tags);

        return "views/monitor";
    }
}
