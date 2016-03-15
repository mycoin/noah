package org.ionnic.app.controller.internal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.ionnic.common.support.ActionSupport;
import org.ionnic.common.support.WebException;
import org.ionnic.common.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/~/testing")
public class TestingAction extends ActionSupport {
    @Autowired
    public HttpServletRequest request;

    @Autowired
    public HttpSession session;

    @Autowired
    private DataSource dataSource;

    @RequestMapping
    public String index() {
        return "~/testing/overview";
    }

    /**
     * @param body
     * @param model
     */
    @RequestMapping("/basic")
    public void basic(@RequestBody(required = false) Map<String, Object> body, Model model) {
        model.addAttribute("method", request.getMethod());
        model.addAttribute("controller", this.getClass().getName());
        if (body == null) {
            model.addAttribute("data", "{=body}");
        } else {
            model.addAttribute("data", body);
        }
    }

    @RequestMapping("/db")
    @ResponseBody
    public Object db() throws Exception {

        Connection conn = dataSource.getConnection();
        String sql = "INSERT INTO CUSTOMER " + "(CUST_ID, NAME, AGE) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, request.getRemotePort());
        ps.setString(2, request.getHeader("User-Agent"));
        ps.setInt(3, 9);
        ps.executeUpdate();
        ps.close();

        return "status-ok";
    }

    @RequestMapping(value = "/display", method = { RequestMethod.GET, RequestMethod.POST })
    public void display(Model data) throws Exception {
        data.addAttribute(DATA, "a");
        data.addAttribute(STATUS, 0);
        data.addAttribute(STATUS_INFO, "OK");
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
    public void index(HttpServletRequest request, Model data) {
        data.addAttribute("search", "<a href=\"api\">测试</a>");
        data.addAttribute("url", 1);
    }

    @RequestMapping(value = "/api", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
    @ResponseBody
    public Object index(@RequestBody Map<String, Object> param) {
        param.put("status", 0);
        return param;
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

    /**
     * @param version
     * @return
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/log.do")
    public void log(HttpServletResponse response) throws Exception {
        WebUtils.sendResource(response, "public/e.gif");
    }

    @RequestMapping(value = "/log.js")
    public Object log(@RequestParam String version, @RequestParam long sid, Model model, HttpServletResponse response) {
        response.setContentType("text/javascript; charset=utf-8");

        model.addAttribute("sid", sid);
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

    @RequestMapping("/view/1")
    public String View1(HttpServletRequest request) throws Exception {
        request.setAttribute("p", "OK");
        return "~/testing/view";
    }

    @RequestMapping("/view/2")
    public ModelAndView View2() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("p", "OK");
        return new ModelAndView("~/testing/view", map);
    }

    @RequestMapping("/view/3")
    public String View3(Map<String, Object> map) throws Exception {
        map.put("p", "OK");
        return "~/testing/view";
    }

    @RequestMapping("/view/4")
    public String View4(Model model) throws Exception {
        model.addAttribute("p", "OK");
        return "~/testing/view";
    }

    @RequestMapping("/view/5")
    public ModelAndView View5() throws Exception {
        ModelAndView mv = new ModelAndView();

        mv.addObject("p", "OK");
        mv.setViewName("~/testing/view");
        return mv;
    }

}
