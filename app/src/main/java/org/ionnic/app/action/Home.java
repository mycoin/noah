package org.ionnic.app.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ionnic.common.ActionSupport;
import org.ionnic.common.support.WebException;
import org.ionnic.common.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author apple
 *
 */
@Controller
@RequestMapping("/home")
public class Home extends ActionSupport {

    /**
     * @param body
     * @param model
     */
    @RequestMapping("/basic")
    public void basic(@RequestBody Map<String, Object> body, Model model) {
        model.addAttribute("method", request.getMethod());
        model.addAttribute("controller", this.getClass().getName());
        if (body == null) {
            model.addAttribute("data", "{=body}");
        } else {
            model.addAttribute("data", body);
        }
    }

    /**
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/error")
    @ResponseBody
    public Map<String, Object> error() throws Exception {
        throw new Exception("OK");
    }

    /**
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/exception", produces = "application/json")
    @ResponseBody
    public Map<String, Object> exception() throws Exception {
        throw new WebException(403, "For<!-- status-ok -->bidden");
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "/index")
    public Object index(HttpServletRequest request, Model data) {
        data.addAttribute("search", "<a href=\"api\">测试</a>");
        data.addAttribute("url", 1);

        return data;
    }

    @RequestMapping(value = "/json")
    @ResponseBody
    public ModelMap json() {
        ModelMap model = new ModelMap();

        Map<String, Object> data = new HashMap<String, Object>();

        model.addAttribute(STATUS, 0);
        model.addAttribute(STATUS_INFO, "OK");
        model.addAttribute(DATA, data);

        data.put("url", request.getRequestURL());
        data.put("ip", WebUtils.getRemoteAddr(request));
        data.put("search", new Date());

        return model;
    }

    @RequestMapping(value = "/log")
    public Object log(@RequestParam String version, @RequestParam long sid, Model model, HttpServletResponse response) {
        response.setContentType("text/javascript; charset=utf-8");

        model.addAttribute("siteId", sid);
        model.addAttribute("version", version);

        return model;
    }

    /**
     * @param body
     * @param search
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/search/{search}", method = RequestMethod.POST)
    @ResponseBody
    public Object search(@RequestBody Map<String, Object> body, @PathVariable() String search) throws Exception {
        ModelMap data = new ModelMap();

        data.addAttribute("biz", body);
        data.addAttribute("search", search);
        data.addAttribute("status", 0);

        return data;
    }

    @RequestMapping("/status")
    public void status(HttpServletResponse response) throws Exception {
        response.addHeader("Content-Type", "text/html; charset=utf-8");
        response.getOutputStream().write("OK<!-- status-ok -->".getBytes());
    }

}
