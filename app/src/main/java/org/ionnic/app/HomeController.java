package org.ionnic.app;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ionnic.common.support.AbstractActionSupport;
import org.ionnic.common.support.DefaultWebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller("HomeController")
@RequestMapping("/home")
public class HomeController extends AbstractActionSupport {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/index")
    public ModelAndView index() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.addObject("param", 1);
        mv.addObject("data", new HashMap<String, Object>());
        return mv;
    }

    @RequestMapping(value = "/ajax", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object> ajax() throws DefaultWebException {
        checkSessionBasedToken(request);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put(DATA_NAME, new Date());
        model.put(STATUS_NAME, 1);

        return model;
    }
}
