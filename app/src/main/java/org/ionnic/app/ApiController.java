package org.ionnic.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.common.support.view.MappingJacksonJsonView;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

@Controller("ApiController")
public class ApiController extends AbstractController {

    @Override
    protected ModelAndView handleRequestInternal( HttpServletRequest request, HttpServletResponse response ) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home/index");
        mv.addObject("id", 1);
        mv.addObject("OK", 1);

        if (this != null) {
            mv.setView(MappingJacksonJsonView.getInstance());
        }
        return mv;
    }
}
