package org.ionnic.app;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.ionnic.common.config.ConfigConstants;
import org.ionnic.common.support.DefaultWebException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("HomeController")
@RequestMapping("/home")
public class HomeController implements ConfigConstants {

    @RequestMapping("/index")
    public void index() {
        throw new DefaultWebException(403, "Forbidden");
    }

    @RequestMapping(value = "/ajax", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object> ajax() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put(DATA_NAME, new Date());
        model.put(STATUS_NAME, 1);

        return model;
    }
}
