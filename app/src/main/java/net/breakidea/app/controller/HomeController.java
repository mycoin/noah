package net.breakidea.app.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.breakidea.common.support.WebException;
import net.breakidea.common.web.AbstractActionSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home")
public class HomeController extends AbstractActionSupport {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/index")
    public String index( HttpServletRequest request, Model context, @RequestParam(name = "target", required = false, defaultValue = "") String target )
            throws Exception {
        context.addAttribute("param", 1);
        context.addAttribute("data", new HashMap<String, Object>());

        if (target.equals("external")) {
            return "external:homeIndex";
        } else {
            return "home/index";
        }
    }

    @RequestMapping(value = "/ajax", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object> ajax() throws WebException {
        checkSessionToken(request);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put(DATA_NAME, new Date());
        model.put(STATUS_NAME, 1);

        return model;
    }
}
