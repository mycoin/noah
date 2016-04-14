package net.breakidea.app.controller;

import javax.servlet.http.HttpServletRequest;

import net.breakidea.common.web.AbstractActionSupport;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class ApiController extends AbstractActionSupport {

    @RequestMapping("/console")
    public String index( HttpServletRequest request ) throws Exception {
        String message = request.getParameter("message");
        String url = request.getParameter("url");
        String line = request.getParameter("line");

        System.out.println(message + "\n" + url + "@" + line);
        return "blank";
    }
}
