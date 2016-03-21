package org.ionnic.app;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.ionnic.common.config.ConfigConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
public class IndexController implements ConfigConstants{

    @RequestMapping
    public void execute() {

    }

    @RequestMapping(value = "/ajax", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object> ajax() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put(DATA, new Date());
        model.put(STATUS, 1);
        return model;
    }
}
