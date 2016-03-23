package org.ionnic.app;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ionnic.common.support.DefaultWebException;
import org.ionnic.common.support.web.ActionSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("HomeController")
@RequestMapping("/home")
public class HomeController extends ActionSupport {

    @RequestMapping("/index")
    public void index( ModelMap mv ) {
        mv.addAttribute("data", 1);
    }

    @RequestMapping(value = "/ajax", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object> ajax() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put(DATA_NAME, new Date());
        model.put(STATUS_NAME, 1);

        return model;
    }

    @Override
    public boolean checkRequest( HttpServletRequest request, Boolean is ) throws DefaultWebException {
        // TODO Auto-generated method stub
        return true;
    }
}
