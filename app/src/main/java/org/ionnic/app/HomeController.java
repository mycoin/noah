package org.ionnic.app;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ionnic.common.config.ConfigConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("HomeController")
@RequestMapping("/home")
public class HomeController implements ConfigConstants {

    private static Log log = LogFactory.getLog(HomeController.class);

    @RequestMapping("/index")
    public void index() {

    }

    @RequestMapping(value = "/ajax", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object> ajax() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put(DATA, new Date());
        model.put(STATUS, 1);

        log.info("home/ajax run.");
        return model;
    }
}
