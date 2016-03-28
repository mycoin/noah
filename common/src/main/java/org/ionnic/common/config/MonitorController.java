package org.ionnic.common.config;

import javax.servlet.http.HttpServletRequest;

import org.ionnic.common.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/-/monitor")
public class MonitorController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/index")
    public String index( Model data ) throws Exception {
        data.addAttribute("ip", WebUtils.getRemoteAddr(request));
        data.addAttribute("start", System.getenv());
        return "monitor";
    }
}
