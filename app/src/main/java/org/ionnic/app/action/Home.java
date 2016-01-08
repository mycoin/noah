package org.ionnic.app.action;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ionnic.app.bean.Parameter;
import org.ionnic.app.util.CreateImage;
import org.ionnic.common.ActionSupport;
import org.ionnic.common.HttpException;
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
    public void basic(@RequestBody Parameter body, Model model) {
        model.addAttribute("method", request.getMethod());
        model.addAttribute("controller", this.getClass().getName());
        model.addAttribute("data", body);
    }

    /**
     * @param model
     */
    @RequestMapping("/jsp")
    public void jsp(@RequestBody Parameter body, Model mv) {
        mv.addAttribute("method", request.getMethod());
        mv.addAttribute("controller", body);
    }

    /**
     * @param response
     * @param session
     * @throws Exception
     */
    @RequestMapping("/code")
    public void code(HttpServletResponse response, HttpSession session) throws Exception {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/png");

        CreateImage vCode = new CreateImage(75, 20, 4, 4);
        vCode.write(session, response.getOutputStream());
    }

    /**
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/error")
    public Map<String, Object> error() throws Exception {
        throw new HttpException(403, "For<!-- status-ok -->bidden");
    }

    /**
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/exception", produces = "application/json")
    @ResponseBody
    public Map<String, Object> exception() throws Exception {
        throw new HttpException(403, "For<!-- status-ok -->bidden");
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
    public Map<String, Serializable> json(HttpServletRequest request) {
        Map<String, Serializable> result = new HashMap<String, Serializable>();

        result.put("ip", request.getRequestURL());
        result.put("search", new Date());
        return result;
    }

    @RequestMapping(value = "/log")
    public Object log(@RequestParam String version, @RequestParam int sid, Model model, HttpServletResponse response) {
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
