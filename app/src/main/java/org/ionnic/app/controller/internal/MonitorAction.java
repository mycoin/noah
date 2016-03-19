package org.ionnic.app.controller.internal;

import org.ionnic.common.support.ActionSupport;
import org.ionnic.common.support.view.MappingJacksonJsonView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/~/monitor")
public class MonitorAction extends ActionSupport {

    @RequestMapping
    public ModelAndView execute() {
        ModelAndView mv = new ModelAndView();
        ModelMap model = mv.getModelMap();

        mv.setView(new MappingJacksonJsonView());

        model.put("OK", 1);

        return mv;
    }
}
