package com.breakidea.noah.controller;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.breakidea.noah.common.parameter.UserParameter;
import com.breakidea.noah.common.service.UserService;
import com.breakidea.noah.support.AbstractWebController;
import com.breakidea.noah.web.session.Authz;

@Controller("/portal/endpoint")
public class EndpointController extends AbstractWebController {

    public static final String REQUEST_ATTRIBUTE = "request";

    public static final String SESSION_ATTRIBUTE = "session";

    public static final String RESPONSE_ATTRIBUTE = "response";

    @Autowired
    UserService userService;

    @Autowired
    private Authz authz;

    @Override
    protected void handleRequestInternal(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        HttpSession session = request.getSession(true);

        session.setAttribute("RequestId", new Date().getTime());
        session.setAttribute("RequestName", request.getRequestURI());

        mv.addObject(REQUEST_ATTRIBUTE, request);
        mv.addObject(RESPONSE_ATTRIBUTE, response);
        mv.addObject(SESSION_ATTRIBUTE, request.getSession(false));
        mv.addObject("authz", authz);
        mv.addObject("properties", System.getProperties());
        mv.addObject("env", System.getenv());
        mv.addObject("userList", userService.queryList(new UserParameter()));
    }
}
