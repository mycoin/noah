package com.breakidea.noah.controller.portal;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.breakidea.noah.common.parameter.UserParameter;
import com.breakidea.noah.common.service.UserService;
import com.breakidea.noah.support.AbstractWebController;
import com.breakidea.noah.web.session.Authz;

@Controller("/portal/endpoint")
public class PortalEndpoint extends AbstractWebController {

    @Autowired
    UserService userService;

    @Autowired
    private Authz authz;

    @Override
    public void handleRequestInternal(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws ServletException {
        HttpSession session = request.getSession(true);

        session.setAttribute("RequestId", new Date().getTime());
        session.setAttribute("RequestName", request.getRequestURI());

        model.addAttribute("authz", authz);
        model.addAttribute("properties", System.getProperties());
        model.addAttribute("env", System.getenv());
        model.addAttribute("userList", userService.queryList(new UserParameter()));
    }

}
