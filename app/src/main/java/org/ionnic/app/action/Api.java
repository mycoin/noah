package org.ionnic.app.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.ionnic.app.util.InputModel;
import org.ionnic.common.ActionSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author apple
 *
 */
@Controller
@RequestMapping("/api")
public class Api extends ActionSupport {

    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
    public ModelAndView index(@RequestBody InputModel model, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("json");

        mv.addObject("data", model);
        mv.addObject("keyword", model.getParams().get("keyword"));

        return mv;
    }

    @RequestMapping("/user")
    @ResponseBody
    public HashedMap user(HttpServletRequest request) {
        return new HashedMap();
    }
}